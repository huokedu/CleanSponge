package org.spongepowered.clean.init;

import org.spongepowered.api.Sponge;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.SServer;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.scheduler.condition.TaskCondition;

public class WorldInitTask extends Task {

    @Override
    protected void execute() {

        ((SServer) Sponge.getServer()).findAllWorlds();
        SGame.getLogger().info("Located " + Sponge.getServer().getUnloadedWorlds().size() + " worlds");
        TaskCondition condition = ((SServer) Sponge.getServer()).loadStartupWorlds();
        CoreScheduler.addNormalTask(new NetworkInitTask(), condition);
    }

}
