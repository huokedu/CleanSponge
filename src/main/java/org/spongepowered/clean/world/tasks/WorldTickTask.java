package org.spongepowered.clean.world.tasks;

import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.world.SWorld;

public class WorldTickTask extends Task {

    private final SWorld world;

    public WorldTickTask(SWorld world) {
        this.world = world;
    }

    @Override
    public void execute() {
        this.world.update();
        this.world.getMutex().release();
    }

}
