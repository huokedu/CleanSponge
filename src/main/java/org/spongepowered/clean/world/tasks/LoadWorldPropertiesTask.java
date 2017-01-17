package org.spongepowered.clean.world.tasks;

import java.io.File;

import org.spongepowered.clean.scheduler.Task;

public class LoadWorldPropertiesTask extends Task {

    private File propertiesFile;

    public LoadWorldPropertiesTask(File f) {
        this.propertiesFile = f;
    }

    @Override
    protected void execute() {

    }

}
