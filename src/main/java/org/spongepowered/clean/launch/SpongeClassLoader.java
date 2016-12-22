/*
 * This file is part of SpongeClean, licensed under the MIT License (MIT).
 *
 * Copyright (c) The VoxelBox <http://thevoxelbox.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.clean.launch;

import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class SpongeClassLoader extends URLClassLoader {

    private ClassLoader                  parent;
    private List<URL>                    urls;

    private final List<ClassTransformer> transformers          = new ArrayList<ClassTransformer>();
    private final List<String>           classloaderExclusions = new ArrayList<String>();
    private final List<String>           transformerExclusions = new ArrayList<String>();

    private final Set<String>            invalid               = new HashSet<String>(1000);
    private final Map<String, Class<?>>  classes               = new ConcurrentHashMap<String, Class<?>>();

    private final ThreadLocal<byte[]>    loadCache             = new ThreadLocal<byte[]>();

    public SpongeClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, null);
        this.parent = parent;
        this.urls = new ArrayList<>();
        for (URL u : urls)
            this.urls.add(u);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
        this.urls.add(url);
    }

    @Override
    public Class<?> findClass(final String name) throws ClassNotFoundException {
        if (this.invalid.contains(name)) {
            throw new ClassNotFoundException(name);
        }

        for (String ex : this.classloaderExclusions) {
            if (name.startsWith(ex)) {
                return this.parent.loadClass(name);
            }
        }

        Class<?> clz = this.classes.get(name);
        if (clz != null) {
            return clz;
        }

        for (String ex : this.transformerExclusions) {
            if (name.startsWith(ex)) {
                try {
                    clz = super.findClass(name);
                    this.classes.put(name, clz);
                    return clz;
                } catch (ClassNotFoundException e) {
                    this.invalid.add(name);
                    throw e;
                }
            }
        }

        try {
            byte[] classBytes = loadClassBytes(name);
            for (ClassTransformer transformer : this.transformers) {
                classBytes = transformer.transform(name, classBytes);
            }

            final int lastDot = name.lastIndexOf('.');
            final String packageName = lastDot == -1 ? "" : name.substring(0, lastDot);
            final String fileName = name.replace('.', '/').concat(".class");
            URLConnection urlConnection = getResource(fileName).openConnection();

            CodeSigner[] signers = null;

            if (lastDot > -1) {
                if (urlConnection instanceof JarURLConnection) {
                    final JarURLConnection jarURLConnection = (JarURLConnection) urlConnection;
                    final JarFile jarFile = jarURLConnection.getJarFile();

                    if (jarFile != null && jarFile.getManifest() != null) {
                        final Manifest manifest = jarFile.getManifest();
                        final JarEntry entry = jarFile.getJarEntry(fileName);

                        Package pkg = getPackage(packageName);
                        signers = entry.getCodeSigners();
                        if (pkg == null) {
                            pkg = definePackage(packageName, manifest, jarURLConnection.getJarFileURL());
                        }
                    }
                } else {
                    Package pkg = getPackage(packageName);
                    if (pkg == null) {
                        pkg = definePackage(packageName, null, null, null, null, null, null, null);
                    }
                }
            }
            CodeSource codeSource = new CodeSource(urlConnection.getURL(), signers);
            clz = defineClass(name, classBytes, 0, classBytes.length, codeSource);
            this.classes.put(name, clz);
            return clz;
        } catch (Throwable e) {
            this.invalid.add(name);
            throw new ClassNotFoundException(name, e);
        }
    }

    public void addClassloaderExclusion(String ex) {
        if (!this.classloaderExclusions.contains(ex)) {
            this.classloaderExclusions.add(ex);
        }
    }

    public void addTransformerExclusion(String ex) {
        if (!this.transformerExclusions.contains(ex)) {
            this.transformerExclusions.add(ex);
        }
    }

    public void addClassTransformer(String clz) {
        try {
            ClassTransformer transformer = (ClassTransformer) loadClass(clz).newInstance();
            this.transformers.add(transformer);
        } catch (Exception e) {
            System.err.println("Error loading class transformer: " + clz);
            e.printStackTrace();
        }
    }

    private byte[] loadClassBytes(final String name) throws IOException {
        // TODO: do we need to cache these resources?
        String path = name.replace('.', '/') + ".class";
        try (InputStream resource = findResource(path).openStream()) {
            byte[] buffer = this.loadCache.get();
            if (buffer == null) {
                buffer = new byte[4096];
                this.loadCache.set(buffer);
            }

            int read;
            int length = 0;
            while ((read = resource.read(buffer, length, buffer.length - length)) != -1) {
                length += read;
                if (length > buffer.length - 1) {
                    buffer = Arrays.copyOf(buffer, buffer.length + 4096);
                }
            }

            byte[] classBytes = new byte[length];
            System.arraycopy(buffer, 0, classBytes, 0, length);
            return classBytes;
        }
    }

}
