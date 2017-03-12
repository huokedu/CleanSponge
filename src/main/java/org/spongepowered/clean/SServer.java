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

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableMap;
import org.spongepowered.api.GameState;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.SpongeEventFactory;
import org.spongepowered.api.profile.GameProfileManager;
import org.spongepowered.api.resourcepack.ResourcePack;
import org.spongepowered.api.scoreboard.Scoreboard;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.AbstractMutableMessageChannel;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.channel.impl.SimpleMutableMessageChannel;
import org.spongepowered.api.world.ChunkTicketManager;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.WorldArchetypes;
import org.spongepowered.api.world.storage.ChunkLayout;
import org.spongepowered.api.world.storage.WorldProperties;
import org.spongepowered.clean.config.ServerProperties;
import org.spongepowered.clean.entity.player.SPlayer;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.condition.TaskCondition;
import org.spongepowered.clean.scheduler.condition.TasksCompleteCondition;
import org.spongepowered.clean.world.SWorld;
import org.spongepowered.clean.world.SWorldProperties;
import org.spongepowered.clean.world.storage.SaveHandler;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SServer implements Server {

    private ServerProperties properties;

    private ImmutableMap<String, Player> online_players = ImmutableMap.of();
    private ImmutableMap<UUID, Player> online_players_uid = ImmutableMap.of();
    private MessageChannel broadcastChannel = new SimpleMutableMessageChannel();

    private ImmutableMap<String, World> worlds = ImmutableMap.of();
    private ImmutableMap<String, WorldProperties> unloaded_worlds = ImmutableMap.of();
    private ImmutableMap<String, WorldProperties> all_worlds = ImmutableMap.of();

    public SServer() {

    }

    public void findAllWorlds() {
        ImmutableMap.Builder<String, WorldProperties> builder = ImmutableMap.builder();

        File worlds = SGame.game.getWorldsDir().toFile();
        for (File world : worlds.listFiles()) {
            if (world.isDirectory()) {
                File data = new File(world, "level.dat");
                if (data.exists()) {
                    SaveHandler save = new SaveHandler(world);
                    try {
                        WorldProperties props = save.loadProperties();
                        // TODO check for duplicates
                        builder.put(props.getWorldName(), props);
                    } catch (IOException e) {
                        SGame.getLogger().error("Error loading world properties for world: " + world.getAbsolutePath());
                        e.printStackTrace();
                    }
                }
            }
        }

        this.unloaded_worlds = builder.build();
    }

    public TaskCondition loadStartupWorlds() {
        // TODO load startup worlds from sponge configs as well as the default
        // world from the server.properties

        try {
            createWorldProperties("world", WorldArchetypes.OVERWORLD);
            loadWorld("world");
        } catch (IOException e) {
            CoreScheduler.emergencyShutdown(e);
        }

        return new TasksCompleteCondition();
    }

    public void addPlayer(SPlayer player) {
        synchronized (this.online_players) {
            if (this.broadcastChannel instanceof AbstractMutableMessageChannel) {
                ((AbstractMutableMessageChannel) this.broadcastChannel).addMember(player);
            }
            this.online_players = ImmutableMap.<String, Player>builder().putAll(this.online_players).put(player.getName(), player).build();
            this.online_players_uid = ImmutableMap.<UUID, Player>builder().putAll(this.online_players_uid).put(player.getUniqueId(), player).build();
        }
    }

    public ServerProperties getServerProperties() {
        return this.properties;
    }

    public void setServerProperties(ServerProperties props) {
        this.properties = props;
    }

    @Override
    public Collection<World> getWorlds() {
        return this.worlds.values();
    }

    @Override
    public Collection<Player> getOnlinePlayers() {
        return this.online_players.values();
    }

    @Override
    public int getMaxPlayers() {
        return this.properties.max_players;
    }

    @Override
    public Optional<Player> getPlayer(UUID uniqueId) {
        checkNotNull(uniqueId, "uniqueId");
        return Optional.ofNullable(this.online_players_uid.get(uniqueId));
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        checkNotNull(name, "name");
        return Optional.ofNullable(this.online_players.get(name));
    }

    @Override
    public Collection<WorldProperties> getUnloadedWorlds() {
        return this.unloaded_worlds.values();
    }

    @Override
    public Collection<WorldProperties> getAllWorldProperties() {
        return this.all_worlds.values();
    }

    @Override
    public Optional<World> getWorld(UUID uniqueId) {
        checkNotNull(uniqueId, "uniqueId");
        for (World world : this.worlds.values()) {
            if (uniqueId.equals(world.getUniqueId())) {
                return Optional.of(world);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<World> getWorld(String worldName) {
        checkNotNull(worldName, "worldName");
        return Optional.ofNullable(this.worlds.get(worldName));
    }

    @Override
    public Optional<WorldProperties> getDefaultWorld() {
        return Optional.ofNullable(this.all_worlds.get(getDefaultWorldName()));
    }

    @Override
    public String getDefaultWorldName() {
        return this.properties.level_name;
    }

    @Override
    public Optional<World> loadWorld(String worldName) {
        World world = this.worlds.get(worldName);
        if (world != null) {
            return Optional.of(world);
        }
        WorldProperties props = this.unloaded_worlds.get(worldName);
        if (props == null) {
            return Optional.empty();
        }
        ImmutableMap.Builder<String, WorldProperties> builder = ImmutableMap.builder();
        for (Map.Entry<String, WorldProperties> e : this.unloaded_worlds.entrySet()) {
            if (e.getKey().equals(worldName)) {
                continue;
            }
            builder.put(e);
        }
        this.unloaded_worlds = builder.build();
        world = new SWorld(worldName, (SWorldProperties) props);
        // TODO load world spawn if needed and other load operations
        this.worlds = ImmutableMap.<String, World>builder().putAll(this.worlds).put(worldName, world).build();
        Sponge.getEventManager().post(SpongeEventFactory.createLoadWorldEvent(SGame.game.getImplementationCause(), world));
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
        // TODO check for duplicates
        WorldProperties props = new SWorldProperties(folderName, archetype);
        ImmutableMap.Builder<String, WorldProperties> builder = ImmutableMap.builder();
        builder.putAll(this.unloaded_worlds);
        builder.put(folderName, props);
        this.unloaded_worlds = builder.build();
        return props;
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
        return this.broadcastChannel;
    }

    @Override
    public void setBroadcastChannel(MessageChannel channel) {
        this.broadcastChannel = channel;
    }

    @Override
    public Optional<InetSocketAddress> getBoundAddress() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasWhitelist() {
        return this.properties.whitelist;
    }

    @Override
    public void setHasWhitelist(boolean enabled) {
        this.properties.whitelist = enabled;
        // TODO mark properties as dirty
    }

    @Override
    public boolean getOnlineMode() {
        return this.properties.online_mode;
    }

    @Override
    public Text getMotd() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void shutdown() {
        shutdown(Text.of("Stopping server..."));
    }

    @Override
    public void shutdown(Text kickMessage) {
        getBroadcastChannel().send(kickMessage);
        SGame.game.updateState(GameState.SERVER_STOPPING);

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
        return this.properties.player_idle_timeout;
    }

    @Override
    public void setPlayerIdleTimeout(int timeout) {
        this.properties.player_idle_timeout = timeout;
        // TODO mark properties as dirty and write out
    }

    @Override
    public boolean isMainThread() {
        throw new IllegalStateException("There is no main thread in this server model.");
    }

}
