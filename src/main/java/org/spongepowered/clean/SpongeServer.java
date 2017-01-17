package org.spongepowered.clean;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.spongepowered.api.Server;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.profile.GameProfileManager;
import org.spongepowered.api.resourcepack.ResourcePack;
import org.spongepowered.api.scoreboard.Scoreboard;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.world.ChunkTicketManager;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.storage.ChunkLayout;
import org.spongepowered.api.world.storage.WorldProperties;
import org.spongepowered.clean.world.SpongeWorld;

public class SpongeServer implements Server {

    public static final SpongeServer insn = new SpongeServer();

    private Map<String, World> worlds = new ConcurrentHashMap<>();

    private SpongeServer() {

    }

    @Override
    public Collection<World> getWorlds() {
        return this.worlds.values();
    }

    @Override
    public Collection<Player> getOnlinePlayers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getMaxPlayers() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Optional<Player> getPlayer(UUID uniqueId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<WorldProperties> getUnloadedWorlds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<WorldProperties> getAllWorldProperties() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<World> getWorld(UUID uniqueId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<World> getWorld(String worldName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<WorldProperties> getDefaultWorld() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDefaultWorldName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<World> loadWorld(String worldName) {
        // TODO find world properties from disk
        SpongeWorld world = new SpongeWorld(worldName);
        this.worlds.put(worldName, world);
        return Optional.of(world);
    }

    @Override
    public Optional<World> loadWorld(UUID uniqueId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<World> loadWorld(WorldProperties properties) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<WorldProperties> getWorldProperties(String worldName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<WorldProperties> getWorldProperties(UUID uniqueId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean unloadWorld(World world) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public WorldProperties createWorldProperties(String folderName, WorldArchetype archetype) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompletableFuture<Optional<WorldProperties>> copyWorld(WorldProperties worldProperties, String copyName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<WorldProperties> renameWorld(WorldProperties worldProperties, String newName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompletableFuture<Boolean> deleteWorld(WorldProperties worldProperties) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean saveWorldProperties(WorldProperties properties) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<Scoreboard> getServerScoreboard() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChunkLayout getChunkLayout() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getRunningTimeTicks() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public MessageChannel getBroadcastChannel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBroadcastChannel(MessageChannel channel) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<InetSocketAddress> getBoundAddress() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasWhitelist() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setHasWhitelist(boolean enabled) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean getOnlineMode() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Text getMotd() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void shutdown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void shutdown(Text kickMessage) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ConsoleSource getConsole() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChunkTicketManager getChunkTicketManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameProfileManager getGameProfileManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getTicksPerSecond() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Optional<ResourcePack> getDefaultResourcePack() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getPlayerIdleTimeout() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setPlayerIdleTimeout(int timeout) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isMainThread() {
        // TODO Auto-generated method stub
        return false;
    }

}
