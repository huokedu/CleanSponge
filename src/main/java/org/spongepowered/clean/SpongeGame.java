package org.spongepowered.clean;

import java.nio.file.Path;

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

public class SpongeGame implements Game {

    public static final SpongeGame game = new SpongeGame();

    private GameState state = GameState.CONSTRUCTION;

    private SpongeGame() {

    }

    @Override
    public GameState getState() {
        return this.state;
    }

    public void updateState(GameState state) {
        this.state = state;
    }

    @Override
    public Platform getPlatform() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isServerAvailable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Server getServer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PluginManager getPluginManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EventManager getEventManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AssetManager getAssetManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameRegistry getRegistry() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameDictionary getGameDictionary() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceManager getServiceManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Scheduler getScheduler() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataManager getDataManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PropertyRegistry getPropertyRegistry() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CommandManager getCommandManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TeleportHelper getTeleportHelper() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ConfigManager getConfigManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Path getGameDirectory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Path getSavesDirectory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChannelRegistrar getChannelRegistrar() {
        // TODO Auto-generated method stub
        return null;
    }

}
