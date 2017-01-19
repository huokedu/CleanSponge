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

        SPluginManager plugins = (SPluginManager) Sponge.getPluginManager();

        TasksCompleteCondition condition = new TasksCompleteCondition();
        ClasspathLoadTask cpload = new ClasspathLoadTask();
        condition.addTask(cpload);
        CoreScheduler.addHighTask(cpload);
        List<File> possible = plugins.discoverPlugins();
        // TODO discover plugins and kick off a task per plugin jar to load it
        // or reject it
        for (File jar : possible) {
            PluginLoadTask task = new PluginLoadTask(jar);
            condition.addTask(task);
            CoreScheduler.addNormalTask(task);
        }

        CoreScheduler.addNormalTask(new WorldInitTask(), condition);
    }

}
