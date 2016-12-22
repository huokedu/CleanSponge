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
package org.spongepowered.clean.plugin;

import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.Sets;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.api.Sponge;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PluginLoader {

    private static final String JAVA_HOME = StandardSystemProperty.JAVA_HOME.value();

    public static void loadFromClasspath(URLClassLoader loader) {
        Set<URI> sources = Sets.newHashSet();

        for (URL url : loader.getURLs()) {
            if (!url.getProtocol().equals("file")) {
                continue;
            }
            if (url.getPath().startsWith(JAVA_HOME)) {
                continue;
            }
            URI source;
            try {
                source = url.toURI();
            } catch (URISyntaxException e) {
                continue;
            }
            if (sources.add(source)) {
                try {
                    scanFile(new File(source));
                } catch (IOException e) {
                }
            }
        }
    }

    private static void scanFile(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                scanDirectory(file);
            } else if (file.getName().endsWith(".jar")) {
                scanZip(file);
            }
        }
    }

    private static void scanDirectory(File dir) throws IOException {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scanDirectory(file);
            } else if (file.getName().endsWith(".class")) {
                try (InputStream in = new FileInputStream(file)) {
                    findPlugins(in);
                }
            }
        }
    }

    private static void scanZip(File file) {
        try {
            try (ZipFile zip = new ZipFile(file)) {
                Enumeration<? extends ZipEntry> entries = zip.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                        continue;
                    }

                    try (InputStream in = zip.getInputStream(entry)) {
                        findPlugins(in);
                    }
                }
            }
        } catch (IOException e) {
        }
    }

    @SuppressWarnings("unchecked")
    private static void findPlugins(InputStream in) throws IOException {
        ClassReader reader = new ClassReader(in);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
        if (classNode.visibleAnnotations != null) {
            for (AnnotationNode node : (List<AnnotationNode>) classNode.visibleAnnotations) {
                if (node.desc.equals("Lorg/spongepowered/api/plugin/Plugin;")) {
                    ((SpongePluginManager) Sponge.getPluginManager()).loadPlugin(classNode.name.replace('/', '.'));
                    break;
                }
            }
        }
    }

}
