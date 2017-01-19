package org.spongepowered.clean.init;

import java.io.IOException;

import org.spongepowered.clean.Constants;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.scheduler.condition.TasksCompleteCondition;

import com.google.common.base.Throwables;

public class StartupTask extends Task {

    /* @formatter:off
     * Full startup picture: (newlines represent gates which depend on prior tasks)
     * 
     * StartupTask
     * 
     * ServerPropertiesLoad
     * SpongeConfigLoad
     * WorldConfigLoads (x # Worlds)
     * Registry initialization (x main registries)
     * 
     * Plugin loading (x # plugins)
     * 
     * LoadWorld (x # startup worlds)
     * 
     * NetworkStart
     * 
     * GameTickStart
     * 
     * @formatter:on
     */

    @Override
    public void execute() {
        SGame.getLogger().info("Loading sponge cleanroom server version {}", Constants.SERVER_VERSION);
        SGame.getLogger().info("Implementing sponge api version {}", Constants.API_VERSION);
        SGame.getLogger().info("Supporting minecraft version {}", Constants.MC_VERSION);
        SGame.game.setGameObject();

        try {
            SGame.game.initializeFiles();
        } catch (IOException e) {
            // TODO trigger crash reporter
            Throwables.propagate(e);
        }

        TasksCompleteCondition condition = new TasksCompleteCondition();
        ServerPropertiesLoadTask propsLoad = new ServerPropertiesLoadTask();
        condition.addTask(propsLoad);
        CoreScheduler.addNormalTask(propsLoad);
        SpongeConfigLoadTask configLoad = new SpongeConfigLoadTask();
        condition.addTask(configLoad);
        CoreScheduler.addNormalTask(configLoad);

        PluginInitTask pluginInit = new PluginInitTask();
        CoreScheduler.addNormalTask(pluginInit, condition);

    }

}
