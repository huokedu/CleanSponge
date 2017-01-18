package org.spongepowered.clean.init;

import java.io.IOException;

import org.spongepowered.api.GameState;
import org.spongepowered.api.Sponge;
import org.spongepowered.clean.Constants;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.SServer;
import org.spongepowered.clean.config.ServerProperties;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;

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
        System.out.printf("Loading sponge cleanroom server version %s\n", Constants.SERVER_VERSION);
        System.out.printf("Implementing sponge api version %s\n", Constants.API_VERSION);
        System.out.printf("Supporting minecraft version %s\n", Constants.MC_VERSION);
        SGame.game.setGameObject();
        // Load config
        // TODO actually load these properties from disk
        ServerProperties properties = new ServerProperties();
        ((SServer) Sponge.getServer()).setServerProperties(properties);

        try {
            SGame.game.initializeFiles();
        } catch (IOException e) {
            // TODO trigger crash reporter
            Throwables.propagate(e);
        }

        // Load startup worlds
        // TODO actually locate startup worlds
        Sponge.getServer().loadWorld("world");

        SGame.game.updateState(GameState.SERVER_STARTED);
        CoreScheduler.addHighTask(new GameTickTask());
    }

}
