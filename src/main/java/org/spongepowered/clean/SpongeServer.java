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

import com.flowpowered.math.vector.Vector3i;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import org.spongepowered.clean.command.SpongeConsoleSource;
import org.spongepowered.clean.config.SpongeConfigurationManager;
import org.spongepowered.clean.world.SpongeWorld;
import org.spongepowered.clean.world.SpongeWorldProperties;
import org.spongepowered.clean.world.WorldThread;
import org.spongepowered.clean.world.chunk.SpongeChunkLayout;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SpongeServer implements Server {

    // TODO is it better to cache by name and linear search for uuid or the other way around?
    private final Map<String, WorldThread>     worldThreads          = Maps.newHashMap();
    private final Map<String, WorldProperties> unloaded              = Maps.newHashMap();
    private final Map<String, SpongeWorld>     worlds                = Maps.newHashMap();
    private final Map<String, WorldProperties> worldProperties       = Maps.newHashMap();
    private final List<WorldThread>            unloading             = Lists.newArrayList();

    private ImmutableList<World>               cachedWorlds          = null;
    private ImmutableList<WorldProperties>     cachedWorldProperties = null;
    private ImmutableList<WorldProperties>     cachedUnloaded        = null;

    private ChunkLayout                        chunkLayout;
    private int                                runningTime           = 0;

    private MessageChannel                     broadcastChannel;

    public SpongeServer() {

    }

    public void init() {
        int max = SpongeConfigurationManager.serverProperties.max_world_size;
        this.chunkLayout = new SpongeChunkLayout(new Vector3i(-max, 0, -max), new Vector3i(max, 256, max));

        this.broadcastChannel = MessageChannel.TO_ALL;
    }

    public void loadStartupWorlds() {
        // TODO find unloaded worlds
        // TODO perform any world migrations

        WorldProperties defaultWorldProps = this.unloaded.get(SpongeConfigurationManager.serverProperties.level_name);
        if (defaultWorldProps == null) {
            defaultWorldProps = new SpongeWorldProperties(SpongeConfigurationManager.serverProperties.level_name);
        }
        loadWorld(defaultWorldProps);
    }

    public void tick() {
        for (WorldThread thr : this.unloading) {
            if (thr.isUnloaded()) {
                SpongeWorld world = thr.getWorld();
                this.unloaded.put(world.getName(), world.getProperties());
                this.cachedWorldProperties = null;
                this.cachedUnloaded = null;
            }
        }
        this.runningTime++;
    }

    public void waitForWorldsToUnload() {
        while (true) {
            for (Iterator<WorldThread> it = this.unloading.iterator(); it.hasNext();) {
                WorldThread thr = it.next();
                if (thr.isUnloaded()) {
                    SpongeWorld world = thr.getWorld();
                    this.unloaded.put(world.getName(), world.getProperties());
                    this.cachedWorldProperties = null;
                    this.cachedUnloaded = null;
                    it.remove();
                }
            }
            if (this.unloading.isEmpty()) {
                break;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Collection<Player> getOnlinePlayers() {
        return (Collection) SpongeBootstrap.networkManager.getConnectedPlayers();
    }

    @Override
    public int getMaxPlayers() {
        return SpongeConfigurationManager.serverProperties.max_players;
    }

    @Override
    public Optional<Player> getPlayer(UUID uniqueId) {
        for (Player player : getOnlinePlayers()) {
            if (player.getUniqueId().equals(uniqueId)) {
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        for (Player player : getOnlinePlayers()) {
            if (player.getName().equals(name)) {
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<World> getWorlds() {
        if (this.cachedWorlds == null) {
            this.cachedWorlds = ImmutableList.copyOf(this.worlds.values());
        }
        return this.cachedWorlds;
    }

    @Override
    public Collection<WorldProperties> getUnloadedWorlds() {
        if (this.cachedUnloaded == null) {
            this.cachedUnloaded = ImmutableList.copyOf(this.unloaded.values());
        }
        return this.cachedUnloaded;
    }

    @Override
    public Collection<WorldProperties> getAllWorldProperties() {
        if (this.cachedWorldProperties == null) {
            this.cachedWorldProperties = ImmutableList.copyOf(this.worldProperties.values());
        }
        return this.cachedWorldProperties;
    }

    @Override
    public Optional<World> getWorld(UUID uniqueId) {
        for (SpongeWorld world : this.worlds.values()) {
            if (world.getUniqueId().equals(uniqueId)) {
                return Optional.of(world);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<World> getWorld(String worldName) {
        return Optional.ofNullable(this.worlds.get(worldName));
    }

    @Override
    public Optional<WorldProperties> getDefaultWorld() {
        return getWorldProperties(getDefaultWorldName());
    }

    @Override
    public String getDefaultWorldName() {
        return SpongeConfigurationManager.serverProperties.level_name;
    }

    @Override
    public Optional<World> loadWorld(String worldName) {
        WorldProperties props = this.unloaded.get(worldName);
        if (props == null) {
            return Optional.empty();
        }
        return loadWorld(props);
    }

    @Override
    public Optional<World> loadWorld(UUID uniqueId) {
        for (WorldProperties props : this.unloaded.values()) {
            if (props.getUniqueId().equals(uniqueId)) {
                return loadWorld(props);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<World> loadWorld(WorldProperties properties) {
        if (!this.worldProperties.containsKey(properties.getWorldName())) {
            this.worldProperties.put(properties.getWorldName(), properties);
        }
        SpongeWorld world = new SpongeWorld(properties);
        WorldThread thread = new WorldThread(world);
        new Thread(thread).start();
        this.worlds.put(properties.getWorldName(), world);
        this.worldThreads.put(properties.getWorldName(), thread);
        this.unloaded.remove(properties);

        this.cachedWorlds = null;
        this.cachedWorldProperties = null;
        this.cachedUnloaded = null;
        return Optional.of(world);
    }

    @Override
    public Optional<WorldProperties> getWorldProperties(String worldName) {
        return Optional.ofNullable(this.worldProperties.get(worldName));
    }

    @Override
    public Optional<WorldProperties> getWorldProperties(UUID uniqueId) {
        for (WorldProperties props : this.worldProperties.values()) {
            if (props.getUniqueId().equals(uniqueId)) {
                return Optional.of(props);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean unloadWorld(World world) {
        WorldThread thr = this.worldThreads.get(world.getName());
        if (thr == null) {
            return false;
        }
        thr.unload();
        this.unloading.add(thr);
        this.worlds.remove(world.getName());
        this.worldThreads.remove(world.getName());
        return true;
    }

    @Override
    public WorldProperties createWorldProperties(String folderName, WorldArchetype archetype) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public CompletableFuture<Optional<WorldProperties>> copyWorld(WorldProperties worldProperties, String copyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<WorldProperties> renameWorld(WorldProperties worldProperties, String newName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CompletableFuture<Boolean> deleteWorld(WorldProperties worldProperties) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveWorldProperties(WorldProperties properties) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Scoreboard> getServerScoreboard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ChunkLayout getChunkLayout() {
        return this.chunkLayout;
    }

    @Override
    public int getRunningTimeTicks() {
        return this.runningTime;
    }

    @Override
    public MessageChannel getBroadcastChannel() {
        return this.broadcastChannel;
    }

    @Override
    public void setBroadcastChannel(MessageChannel channel) {
        this.broadcastChannel = channel;
    }

    @Override
    public Optional<InetSocketAddress> getBoundAddress() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasWhitelist() {
        return SpongeConfigurationManager.serverProperties.whitelist;
    }

    @Override
    public void setHasWhitelist(boolean enabled) {
        SpongeConfigurationManager.serverProperties.whitelist = enabled;
        // TODO call into network manager to update related things
    }

    @Override
    public boolean getOnlineMode() {
        return SpongeConfigurationManager.serverProperties.online_mode;
    }

    @Override
    public Text getMotd() {
        // TODO should use a TextSerializer
        return Text.of(SpongeConfigurationManager.serverProperties.motd);
    }

    @Override
    public void shutdown() {
        for (WorldThread thr : this.worldThreads.values()) {
            thr.unload();
        }
        this.unloading.addAll(this.worldThreads.values());
        this.worldThreads.clear();
        this.worlds.clear();

        SpongeBootstrap.running = false;

    }

    @Override
    public void shutdown(Text kickMessage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ConsoleSource getConsole() {
        return SpongeConsoleSource.CONSOLE;
    }

    @Override
    public ChunkTicketManager getChunkTicketManager() {
        // TODO return global manager that performs synchronization
        throw new UnsupportedOperationException("Chunk tickets are handled per world.");
    }

    @Override
    public GameProfileManager getGameProfileManager() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getTicksPerSecond() {
        // TODO calculate average tps of all worlds
        throw new UnsupportedOperationException("Ticks per second may vary per world.");
    }

    @Override
    public Optional<ResourcePack> getDefaultResourcePack() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPlayerIdleTimeout() {
        return SpongeConfigurationManager.serverProperties.player_idle_timeout;
    }

    @Override
    public void setPlayerIdleTimeout(int timeout) {
        SpongeConfigurationManager.serverProperties.player_idle_timeout = timeout;
    }

    @Override
    public boolean isMainThread() {
        throw new UnsupportedOperationException("Each world has its own thread.");
    }

    public SpongeWorld getSpawnWorld() {
        return this.worlds.get(getDefaultWorldName());
    }

}
