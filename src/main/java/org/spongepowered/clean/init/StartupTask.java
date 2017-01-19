package org.spongepowered.clean.init;

import java.io.IOException;

import org.spongepowered.api.GameState;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.clean.Constants;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.SServer;
import org.spongepowered.clean.config.ServerProperties;
import org.spongepowered.clean.registry.RegistryModules;
import org.spongepowered.clean.registry.SGameRegistry;
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
        SGame.getLogger().info("Loading sponge cleanroom server version {}", Constants.SERVER_VERSION);
        SGame.getLogger().info("Implementing sponge api version {}", Constants.API_VERSION);
        SGame.getLogger().info("Supporting minecraft version {}", Constants.MC_VERSION);
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

        RegistryModules.registerModules();

        ((SGameRegistry) Sponge.getRegistry()).performDefaultRegistrations();

        System.out.println("Air: " + BlockTypes.AIR);

        ((SServer) Sponge.getServer()).findAllWorlds();
        SGame.getLogger().info("Located " + Sponge.getServer().getUnloadedWorlds().size() + " worlds");
        // Load startup worlds
        // TODO actually locate startup worlds
        Sponge.getServer().loadWorld("world");

        SGame.game.updateState(GameState.SERVER_STARTED);
        CoreScheduler.addHighTask(new GameTickTask());
    }

}
