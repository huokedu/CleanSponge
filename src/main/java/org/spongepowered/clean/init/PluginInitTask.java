package org.spongepowered.clean.init;

import org.spongepowered.api.Sponge;
import org.spongepowered.clean.registry.RegistryModules;
import org.spongepowered.clean.registry.SGameRegistry;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;

public class PluginInitTask extends Task {

    @Override
    protected void execute() {
        RegistryModules.registerModules();

        ((SGameRegistry) Sponge.getRegistry()).performDefaultRegistrations();

        // TODO discover plugins and kick off a task per plugin jar to load it
        // or reject it
        
        CoreScheduler.addNormalTask(new WorldInitTask());
    }

}
