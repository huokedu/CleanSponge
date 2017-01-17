package org.spongepowered.clean.world.tasks;

import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.world.SpongeWorld;

public class WorldTickTask extends Task {

    private final SpongeWorld world;

    public WorldTickTask(SpongeWorld world) {
        this.world = world;
    }

    @Override
    public void execute() {
        System.out.println("World tick");
        this.world.update();
    }

}
