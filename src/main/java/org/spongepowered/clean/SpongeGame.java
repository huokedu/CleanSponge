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

import org.spongepowered.api.Game;
import org.spongepowered.api.GameDictionary;
import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.GameState;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Server;
import org.spongepowered.api.asset.AssetManager;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.config.ConfigManager;
import org.spongepowered.api.data.DataManager;
import org.spongepowered.api.data.property.PropertyRegistry;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.network.ChannelRegistrar;
import org.spongepowered.api.plugin.PluginManager;
import org.spongepowered.api.scheduler.Scheduler;
import org.spongepowered.api.service.ServiceManager;
import org.spongepowered.api.world.TeleportHelper;
import org.spongepowered.clean.asset.SpongeAssetManager;
import org.spongepowered.clean.block.state.SpongePropertyRegistry;
import org.spongepowered.clean.command.SpongeCommandManager;
import org.spongepowered.clean.config.SpongeConfigurationManager;
import org.spongepowered.clean.data.SpongeDataManager;
import org.spongepowered.clean.event.SpongeEventManager;
import org.spongepowered.clean.network.channel.SpongeChannelRegistrar;
import org.spongepowered.clean.plugin.SpongePluginManager;
import org.spongepowered.clean.registry.SpongeGameDictionary;
import org.spongepowered.clean.registry.SpongeGameRegistry;
import org.spongepowered.clean.scheduler.GlobalScheduler;
import org.spongepowered.clean.service.SpongeServiceManager;
import org.spongepowered.clean.world.SpongeTeleportHelper;

import java.nio.file.Path;

public class SpongeGame implements Game {

    public SpongeServer               server;
    public SpongePluginManager        pluginManager;
    public SpongeEventManager         eventManager;
    public SpongeAssetManager         assetManager;
    public SpongeGameRegistry         gameRegistry;
    public SpongeGameDictionary       gameDictionary;
    public SpongeServiceManager       serviceManager;
    public GlobalScheduler            scheduler;
    public SpongeDataManager          dataManager;
    public SpongePropertyRegistry     propertyRegistry;
    public SpongeCommandManager       commandManager;
    public SpongeTeleportHelper       teleportHelper;
    public SpongeConfigurationManager configManager;
    public SpongeChannelRegistrar     channelRegistrar;
    public SpongePlatform             platform;

    public GameState                  state;

    public SpongeGame() {

    }

    @Override
    public Platform getPlatform() {
        return this.platform;
    }

    @Override
    public boolean isServerAvailable() {
        return this.server != null;
    }

    @Override
    public Server getServer() {
        if (!isServerAvailable()) {
            throw new IllegalStateException("Server not available");
        }
        return this.server;
    }

    @Override
    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    @Override
    public EventManager getEventManager() {
        return this.eventManager;
    }

    @Override
    public AssetManager getAssetManager() {
        return this.assetManager;
    }

    @Override
    public GameRegistry getRegistry() {
        return this.gameRegistry;
    }

    @Override
    public GameDictionary getGameDictionary() {
        return this.gameDictionary;
    }

    @Override
    public ServiceManager getServiceManager() {
        return this.serviceManager;
    }

    @Override
    public Scheduler getScheduler() {
        return this.scheduler;
    }

    @Override
    public DataManager getDataManager() {
        return this.dataManager;
    }

    @Override
    public PropertyRegistry getPropertyRegistry() {
        return this.propertyRegistry;
    }

    @Override
    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    @Override
    public TeleportHelper getTeleportHelper() {
        return this.teleportHelper;
    }

    @Override
    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    @Override
    public Path getGameDirectory() {
        return SpongeBootstrap.game_dir;
    }

    @Override
    public Path getSavesDirectory() {
        return SpongeBootstrap.saves_dir;
    }

    @Override
    public GameState getState() {
        return this.state;
    }

    @Override
    public ChannelRegistrar getChannelRegistrar() {
        return this.channelRegistrar;
    }

}
