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
package org.spongepowered.clean.init;

import java.io.File;
import java.util.List;

import org.spongepowered.api.Sponge;
import org.spongepowered.clean.plugin.SPluginManager;
import org.spongepowered.clean.plugin.tasks.ClasspathLoadTask;
import org.spongepowered.clean.plugin.tasks.PluginLoadTask;
import org.spongepowered.clean.registry.RegistryModules;
import org.spongepowered.clean.registry.SGameRegistry;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.scheduler.condition.TasksCompleteCondition;

public class PluginInitTask extends Task {

    @Override
    protected void execute() {
        RegistryModules.registerModules();
        ((SGameRegistry) Sponge.getRegistry()).performDefaultRegistrations();
        RegistryModules.registerLateModules();
        ((SGameRegistry) Sponge.getRegistry()).performDefaultRegistrations();

        SPluginManager plugins = (SPluginManager) Sponge.getPluginManager();

        TasksCompleteCondition condition = new TasksCompleteCondition();
        ClasspathLoadTask cpload = new ClasspathLoadTask();
        condition.addTask(cpload);
        CoreScheduler.addHighTask(cpload);
        List<File> possible = plugins.discoverPlugins();
        for (File jar : possible) {
            PluginLoadTask task = new PluginLoadTask(jar);
            condition.addTask(task);
            CoreScheduler.addNormalTask(task);
        }

        CoreScheduler.addNormalTask(new WorldInitTask(), condition);
    }

}
