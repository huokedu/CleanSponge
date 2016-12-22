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
package org.spongepowered.clean;

import org.spongepowered.clean.launch.SpongeClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

public class Main {

    public static SpongeClassLoader classloader;

    public static void main(String[] args) throws Throwable {
        URLClassLoader ucl = (URLClassLoader) Main.class.getClassLoader();
        classloader = new SpongeClassLoader(ucl.getURLs(), ucl);
        Thread.currentThread().setContextClassLoader(classloader);
        
        classloader.addClassloaderExclusion("org.spongepowered.clean.launch.");
        classloader.addClassloaderExclusion("org.spongepowered.clean.Main");

        classloader.addClassloaderExclusion("java.");
        classloader.addClassloaderExclusion("javax.");
        classloader.addClassloaderExclusion("sun.");
        classloader.addClassloaderExclusion("joptsimple.");
        classloader.addClassloaderExclusion("org.apache.logging.");
        classloader.addClassloaderExclusion("org.objectweb.asm.");

        classloader.addTransformerExclusion("com.google.");
        classloader.addTransformerExclusion("org.apache.");
        classloader.addTransformerExclusion("io.netty.");
        
        classloader.addClassTransformer("org.spongepowered.clean.launch.SpongeTransformer");
        
        try {
            Class<?> bootstrap = Class.forName("org.spongepowered.clean.SpongeBootstrap", true, classloader);
            Method start = bootstrap.getMethod("main", String[].class);
            start.invoke(null, (Object) args);
        } catch(InvocationTargetException e) {
            throw e.getCause();
        } catch (Exception e) {
            System.err.println("Failed to launch");
            e.printStackTrace();
        }
    }

}
