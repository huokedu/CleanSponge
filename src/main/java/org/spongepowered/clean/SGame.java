package org.spongepowered.clean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.GameDictionary;
import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.GameState;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
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
import org.spongepowered.clean.asset.SAssetManager;
import org.spongepowered.clean.block.SPropertyRegistry;
import org.spongepowered.clean.command.SCommandManager;
import org.spongepowered.clean.data.SDataManager;
import org.spongepowered.clean.event.SEventManager;
import org.spongepowered.clean.network.NetworkManager;
import org.spongepowered.clean.plugin.SPluginManager;
import org.spongepowered.clean.registry.SGameDictionary;
import org.spongepowered.clean.registry.SGameRegistry;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.game.SScheduler;
import org.spongepowered.clean.server.SPlatform;
import org.spongepowered.clean.service.SServiceManager;
import org.spongepowered.clean.world.DimensionManager;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SGame implements Game {

    public static final SGame game = new SGame();

    public static Logger getLogger() {
        return game.logger;
    }

    private GameState state = GameState.CONSTRUCTION;
    private Platform platform = new SPlatform();
    private Server server = new SServer();
    private PluginManager pluginManager = new SPluginManager();
    private EventManager eventManager = new SEventManager();
    private AssetManager assetManager = new SAssetManager();
    private GameRegistry gameRegistry = new SGameRegistry();
    private GameDictionary gameDictionary = new SGameDictionary();
    private ServiceManager serviceManager = new SServiceManager();
    private Scheduler scheduler = new SScheduler();
    private DataManager dataManager = new SDataManager();
    private PropertyRegistry propertyRegistry = new SPropertyRegistry();
    private CommandManager commandManager = new SCommandManager();
    private DimensionManager dimManager = new DimensionManager();
    private NetworkManager network = new NetworkManager();

    private final Logger logger = LogManager.getLogger("Sponge");

    private Path rootDir;
    private Path pluginsDir;
    private Path worldsDir;
    private Path configDir;

    private SGame() {

    }

    public void initializeFiles() throws IOException {
        this.rootDir = Paths.get("");
        this.pluginsDir = this.rootDir.resolve("mods");
        if (!Files.exists(this.pluginsDir)) {
            Files.createDirectories(this.pluginsDir);
        }
        this.worldsDir = this.rootDir.resolve("worlds");
        if (!Files.exists(this.worldsDir)) {
            Files.createDirectories(this.worldsDir);
        }
        this.configDir = this.rootDir.resolve("config");
        if (!Files.exists(this.configDir)) {
            Files.createDirectories(this.configDir);
        }
    }

    public Path getWorldsDir() {
        return this.worldsDir;
    }

    public Path getPluginsDir() {
        return this.pluginsDir;
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
        return this.platform;
    }

    @Override
    public boolean isServerAvailable() {
        return true;
    }

    @Override
    public Server getServer() {
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
        return this.rootDir;
    }

    @Override
    public Path getSavesDirectory() {
        return this.worldsDir;
    }

    @Override
    public ChannelRegistrar getChannelRegistrar() {
        // TODO Auto-generated method stub
        return null;
    }

    public DimensionManager getDimensionManager() {
        return this.dimManager;
    }

    public NetworkManager getNetworkManager() {
        return this.network;
    }

    public void setGameObject() {
        try {
            Field field = Sponge.class.getDeclaredField("game");
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(null, this);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            CoreScheduler.emergencyShutdown(e);
        }
    }

}
