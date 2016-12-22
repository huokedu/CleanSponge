/*
 * This file is part of SpongeClean, licensed under the MIT License (MIT).
 *
 * Copyright (c) The VoxelBox <http://thevoxelbox.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.clean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.GameState;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.SpongeEventFactory;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.NamedCause;
import org.spongepowered.clean.asset.SpongeAssetManager;
import org.spongepowered.clean.block.state.SpongePropertyRegistry;
import org.spongepowered.clean.command.SpongeCommandManager;
import org.spongepowered.clean.config.SpongeConfigurationManager;
import org.spongepowered.clean.data.SpongeDataManager;
import org.spongepowered.clean.event.SpongeEventManager;
import org.spongepowered.clean.network.NetworkConnection;
import org.spongepowered.clean.network.SpongeNetworkManager;
import org.spongepowered.clean.network.channel.SpongeChannelRegistrar;
import org.spongepowered.clean.plugin.PluginLoader;
import org.spongepowered.clean.plugin.SpongePluginManager;
import org.spongepowered.clean.registry.SpongeGameDictionary;
import org.spongepowered.clean.registry.SpongeGameRegistry;
import org.spongepowered.clean.scheduler.GlobalScheduler;
import org.spongepowered.clean.service.SpongeServiceManager;
import org.spongepowered.clean.world.SpongeTeleportHelper;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SpongeBootstrap {

    public static SpongeGame           game;
    public static Logger               logger;
    public static SpongeNetworkManager networkManager;

    public static Path                 game_dir;
    public static Path                 config_dir;
    public static Path                 plugins_dir;
    public static Path                 saves_dir;

    public static Cause                sponge_cause;

    public static boolean              running;

    public static void main(String[] args) {
        running = true;
        logger = LogManager.getLogger("Sponge");
        logger.info("Launching CleanSponge version " + SpongeConstants.VERSION);

        game_dir = Paths.get("");
        plugins_dir = game_dir.resolve("plugins");
        saves_dir = game_dir.resolve("worlds");
        config_dir = game_dir.resolve("config");

        game.configManager = new SpongeConfigurationManager();
        game.configManager.loadServerConfig();

        game = new SpongeGame();

        try {
            Field gameField = Sponge.class.getDeclaredField("game");
            gameField.setAccessible(true);
            gameField.set(null, game);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        game.server = new SpongeServer();

        game.eventManager = new SpongeEventManager();
        game.assetManager = new SpongeAssetManager();
        game.gameRegistry = new SpongeGameRegistry();
        game.gameDictionary = new SpongeGameDictionary();
        game.serviceManager = new SpongeServiceManager();
        game.scheduler = new GlobalScheduler();
        game.dataManager = new SpongeDataManager();
        game.propertyRegistry = new SpongePropertyRegistry();
        game.commandManager = new SpongeCommandManager();
        game.teleportHelper = new SpongeTeleportHelper();
        game.channelRegistrar = new SpongeChannelRegistrar();
        game.pluginManager = new SpongePluginManager();
        game.platform = new SpongePlatform();

        sponge_cause = Cause.of(NamedCause.source(game.pluginManager.getSpongePluginContainer()));

        logger.info("Loading plugins from classpath");
        PluginLoader.loadFromClasspath(Main.classloader);

        game.state = GameState.CONSTRUCTION;
        Sponge.getEventManager().post(SpongeEventFactory.createGameConstructionEvent(sponge_cause, GameState.CONSTRUCTION));
        game.state = GameState.PRE_INITIALIZATION;
        Sponge.getEventManager().post(SpongeEventFactory.createGamePreInitializationEvent(sponge_cause, GameState.PRE_INITIALIZATION));
        game.state = GameState.INITIALIZATION;
        Sponge.getEventManager().post(SpongeEventFactory.createGameInitializationEvent(sponge_cause, GameState.INITIALIZATION));
        game.state = GameState.POST_INITIALIZATION;
        Sponge.getEventManager().post(SpongeEventFactory.createGamePostInitializationEvent(sponge_cause, GameState.POST_INITIALIZATION));
        game.state = GameState.LOAD_COMPLETE;
        Sponge.getEventManager().post(SpongeEventFactory.createGameLoadCompleteEvent(sponge_cause, GameState.LOAD_COMPLETE));
        game.state = GameState.SERVER_ABOUT_TO_START;
        Sponge.getEventManager().post(SpongeEventFactory.createGameAboutToStartServerEvent(sponge_cause, GameState.SERVER_ABOUT_TO_START));

        game.server.init();

        game.state = GameState.SERVER_STARTING;
        Sponge.getEventManager().post(SpongeEventFactory.createGameStartingServerEvent(sponge_cause, GameState.SERVER_STARTING));

        game.server.loadStartupWorlds();

        game.state = GameState.SERVER_STARTED;
        Sponge.getEventManager().post(SpongeEventFactory.createGameStartedServerEvent(sponge_cause, GameState.SERVER_STARTED));

        networkManager = new SpongeNetworkManager();
        networkManager.startListening(25565);

        while (running) {

            game.server.tick();
            game.scheduler.syncTick();

            for (NetworkConnection conn : networkManager.getActiveConnections()) {
                conn.update();
            }
            // TODO proper timing control
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Shutting down...");
        game.server.shutdown();

        game.state = GameState.SERVER_STOPPING;
        Sponge.getEventManager().post(SpongeEventFactory.createGameStoppingServerEvent(sponge_cause, GameState.SERVER_STOPPING));

        game.server.waitForWorldsToUnload();

        game.state = GameState.SERVER_STOPPED;
        Sponge.getEventManager().post(SpongeEventFactory.createGameStoppedServerEvent(sponge_cause, GameState.SERVER_STOPPED));
        game.state = GameState.GAME_STOPPING;
        Sponge.getEventManager().post(SpongeEventFactory.createGameStoppingEvent(sponge_cause, GameState.GAME_STOPPING));
        game.state = GameState.GAME_STOPPED;
        Sponge.getEventManager().post(SpongeEventFactory.createGameStoppedEvent(sponge_cause, GameState.GAME_STOPPED));

        logger.info("Terminating.");
    }

    public static void shutdown() {
        running = false;
    }

}
