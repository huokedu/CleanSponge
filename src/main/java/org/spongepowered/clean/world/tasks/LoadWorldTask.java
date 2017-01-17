package org.spongepowered.clean.world.tasks;

import org.spongepowered.api.world.storage.WorldProperties;
import org.spongepowered.clean.scheduler.Task;

public class LoadWorldTask extends Task {

    private WorldProperties properties;

    public LoadWorldTask(WorldProperties props) {
        this.properties = props;
    }

    @Override
    protected void execute() {

    }

}
