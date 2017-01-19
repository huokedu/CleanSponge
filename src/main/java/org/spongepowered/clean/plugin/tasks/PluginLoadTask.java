package org.spongepowered.clean.plugin.tasks;

import java.io.File;

import org.spongepowered.clean.scheduler.Task;

public class PluginLoadTask extends Task {

    private final File jar;

    public PluginLoadTask(File jar) {
        this.jar = jar;
    }

    @Override
    protected void execute() {
        // TODO Auto-generated method stub

    }

}
