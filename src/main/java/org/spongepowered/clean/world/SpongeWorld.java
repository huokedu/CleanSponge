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
package org.spongepowered.clean.world;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.ScheduledBlockUpdate;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.sound.SoundCategory;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntitySnapshot;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.service.context.Context;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.chat.ChatType;
import org.spongepowered.api.text.title.Title;
import org.spongepowered.api.util.AABB;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.util.DiscreteTransform3;
import org.spongepowered.api.world.BlockChangeFlag;
import org.spongepowered.api.world.Chunk;
import org.spongepowered.api.world.Dimension;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.PortalAgent;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.WorldBorder;
import org.spongepowered.api.world.WorldBorder.ChunkPreGenerate;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.explosion.Explosion;
import org.spongepowered.api.world.extent.ArchetypeVolume;
import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.ImmutableBlockVolume;
import org.spongepowered.api.world.extent.MutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.extent.StorageType;
import org.spongepowered.api.world.extent.UnmodifiableBiomeVolume;
import org.spongepowered.api.world.extent.UnmodifiableBlockVolume;
import org.spongepowered.api.world.extent.worker.MutableBiomeVolumeWorker;
import org.spongepowered.api.world.extent.worker.MutableBlockVolumeWorker;
import org.spongepowered.api.world.gen.WorldGenerator;
import org.spongepowered.api.world.storage.WorldProperties;
import org.spongepowered.api.world.storage.WorldStorage;
import org.spongepowered.api.world.weather.Weather;
import org.spongepowered.clean.SpongeConstants;
import org.spongepowered.clean.entity.SpongeEntity;
import org.spongepowered.clean.entity.player.SpongePlayer;
import org.spongepowered.clean.network.NetworkConnection.ConnectionState;
import org.spongepowered.clean.network.packet.play.JoinGamePacket;
import org.spongepowered.clean.network.packet.play.PluginMessagePacket;
import org.spongepowered.clean.world.chunk.SpongeChunk;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SpongeWorld implements World {

    private static long chunkKey(int x, int z) {
        return ((x >> 4) & 0xFFFFFFFFL) << 32 | ((z >> 4) & 0xFFFFFFFFL);
    }

    private final WorldProperties                     properties;
    private boolean                                   loaded         = true;

    private final Long2ObjectOpenHashMap<SpongeChunk> chunks         = new Long2ObjectOpenHashMap<>();

    private List<SpongeEntity>                        entities       = Lists.newArrayList();
    private List<SpongePlayer>                  joiningPlayers = Lists.newArrayList();

    private int                                       next_entity_id = 0;

    public SpongeWorld(WorldProperties props) {
        this.properties = props;
    }

    public void tick() {
        synchronized (this.joiningPlayers) {
            if (!this.joiningPlayers.isEmpty()) {
                for (SpongePlayer player : this.joiningPlayers) {
                    this.entities.add(player);
                    player.getNetConnection().updateConnState(ConnectionState.PLAYING);
                    // TODO read player data file
                    player.setLocation(getSpawnLocation());
                    player.setEntityId(this.next_entity_id++);
                    JoinGamePacket join = new JoinGamePacket(player.getEntityId(), (byte) 0, 0, (byte) 0, (byte) 0,
                            this.properties.getGeneratorType().getId(), false);
                    player.getNetConnection().sendPacket(join);
                    player.getNetConnection().sendPacket(PluginMessagePacket.createBrandPacket(SpongeConstants.SERVER_BRAND));
                }
                this.joiningPlayers.clear();
            }
        }
    }

    @Override
    public Location<World> getLocation(Vector3i position) {
        return new Location<World>(this, position);
    }

    @Override
    public Location<World> getLocation(Vector3d position) {
        return new Location<World>(this, position);
    }

    @Override
    public boolean setBlock(int x, int y, int z, BlockState blockState, BlockChangeFlag flag, Cause cause) {
        // TODO world max bounds
        SpongeChunk chunk = this.chunks.get(chunkKey(x, z));
        if (chunk == null) {
            return false;
        }
        return chunk.setBlock(x, y, z, blockState, flag, cause);
    }

    @Override
    public BlockSnapshot createSnapshot(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean restoreSnapshot(BlockSnapshot snapshot, boolean force, BlockChangeFlag flag, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean restoreSnapshot(int x, int y, int z, BlockSnapshot snapshot, boolean force, BlockChangeFlag flag, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<ScheduledBlockUpdate> getScheduledUpdates(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ScheduledBlockUpdate addScheduledUpdate(int x, int y, int z, int priority, int ticks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeScheduledUpdate(int x, int y, int z, ScheduledBlockUpdate update) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLoaded() {
        return this.loaded;
    }

    @Override
    public Extent getExtentView(Vector3i newMin, Vector3i newMax) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<UUID> getCreator(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<UUID> getNotifier(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCreator(int x, int y, int z, UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNotifier(int x, int y, int z, UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<AABB> getBlockSelectionBox(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<AABB> getIntersectingBlockCollisionBoxes(AABB box) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<AABB> getIntersectingCollisionBoxes(Entity owner, AABB box) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArchetypeVolume createArchetypeVolume(Vector3i min, Vector3i max, Vector3i origin) {
        throw new UnsupportedOperationException();
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Collection<Entity> getEntities() {
        return (Collection) this.entities;
    }

    @Override
    public Collection<Entity> getEntities(Predicate<Entity> filter) {
        return getEntities().stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Entity createEntity(EntityType type, Vector3d position) throws IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Entity> createEntity(DataContainer entityContainer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Entity> createEntity(DataContainer entityContainer, Vector3d position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Entity> restoreSnapshot(EntitySnapshot snapshot, Vector3d position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean spawnEntity(Entity entity, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean spawnEntities(Iterable<? extends Entity> entities, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entity> getIntersectingEntities(AABB box, Predicate<Entity> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<EntityHit> getIntersectingEntities(Vector3d start, Vector3d end, Predicate<EntityHit> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<EntityHit> getIntersectingEntities(Vector3d start, Vector3d direction, double distance, Predicate<EntityHit> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<TileEntity> getTileEntities() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<TileEntity> getTileEntities(Predicate<TileEntity> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<TileEntity> getTileEntity(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setBlock(int x, int y, int z, BlockState block, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MutableBlockVolume getBlockView(Vector3i newMin, Vector3i newMax) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MutableBlockVolume getBlockView(DiscreteTransform3 transform) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector3i getBlockMin() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector3i getBlockMax() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector3i getBlockSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsBlock(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BlockState getBlock(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BlockType getBlockType(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UnmodifiableBlockVolume getUnmodifiableBlockView() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MutableBlockVolume getBlockCopy(StorageType type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImmutableBlockVolume getImmutableBlockCopy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hitBlock(int x, int y, int z, Direction side, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean interactBlock(int x, int y, int z, Direction side, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean interactBlockWith(int x, int y, int z, ItemStack itemStack, Direction side, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean placeBlock(int x, int y, int z, BlockState block, Direction side, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean digBlock(int x, int y, int z, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean digBlockWith(int x, int y, int z, ItemStack itemStack, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getBlockDigTimeWith(int x, int y, int z, ItemStack itemStack, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBiome(int x, int y, int z, BiomeType biome) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MutableBiomeVolume getBiomeView(Vector3i newMin, Vector3i newMax) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MutableBiomeVolume getBiomeView(DiscreteTransform3 transform) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector3i getBiomeMin() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector3i getBiomeMax() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector3i getBiomeSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsBiome(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BiomeType getBiome(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UnmodifiableBiomeVolume getUnmodifiableBiomeView() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MutableBiomeVolume getBiomeCopy(StorageType type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImmutableBiomeVolume getImmutableBiomeCopy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> Optional<E> get(int x, int y, int z, Key<? extends BaseValue<E>> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> get(int x, int y, int z, Class<T> manipulatorClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> getOrCreate(int x, int y, int z, Class<T> manipulatorClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E, V extends BaseValue<E>> Optional<V> getValue(int x, int y, int z, Key<V> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean supports(int x, int y, int z, Key<?> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean supports(int x, int y, int z, Class<? extends DataManipulator<?, ?>> manipulatorClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Key<?>> getKeys(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<ImmutableValue<?>> getValues(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> DataTransactionResult offer(int x, int y, int z, Key<? extends BaseValue<E>> key, E value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> DataTransactionResult offer(int x, int y, int z, Key<? extends BaseValue<E>> key, E value, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult offer(int x, int y, int z, DataManipulator<?, ?> manipulator, MergeFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult offer(int x, int y, int z, DataManipulator<?, ?> manipulator, MergeFunction function, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult remove(int x, int y, int z, Class<? extends DataManipulator<?, ?>> manipulatorClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult remove(int x, int y, int z, Key<?> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult undo(int x, int y, int z, DataTransactionResult result) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult copyFrom(int xTo, int yTo, int zTo, DataHolder from) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult copyFrom(int xTo, int yTo, int zTo, DataHolder from, MergeFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult copyFrom(int xTo, int yTo, int zTo, int xFrom, int yFrom, int zFrom, MergeFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<DataManipulator<?, ?>> getManipulators(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean validateRawData(int x, int y, int z, DataView container) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRawData(int x, int y, int z, DataView container) throws InvalidDataException {
        throw new UnsupportedOperationException();
    }

    @Override
    public UUID getUniqueId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getProperty(int x, int y, int z, Class<T> propertyClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getProperty(int x, int y, int z, Direction direction, Class<T> propertyClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Property<?, ?>> getProperties(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Direction> getFacesWithProperty(int x, int y, int z, Class<? extends Property<?, ?>> propertyClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Weather getWeather() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getRemainingDuration() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getRunningDuration() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWeather(Weather weather) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWeather(Weather weather, long duration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void spawnParticles(ParticleEffect particleEffect, Vector3d position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void spawnParticles(ParticleEffect particleEffect, Vector3d position, int radius) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume, double pitch) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume, double pitch, double minVolume) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendTitle(Title title) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendBookView(BookView bookView) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendBlockChange(int x, int y, int z, BlockState state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetBlockChange(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendMessage(Text message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MessageChannel getMessageChannel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMessageChannel(MessageChannel channel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendMessage(ChatType type, Text message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Player> getPlayers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Chunk> getChunk(int cx, int cy, int cz) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Chunk> loadChunk(int cx, int cy, int cz, boolean shouldGenerate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean unloadChunk(Chunk chunk) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Chunk> getLoadedChunks() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Entity> getEntity(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WorldBorder getWorldBorder() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ChunkPreGenerate newChunkPreGenerate(Vector3d center, double diameter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Dimension getDimension() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WorldGenerator getWorldGenerator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WorldProperties getProperties() {
        return this.properties;
    }

    @Override
    public Path getDirectory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WorldStorage getWorldStorage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void triggerExplosion(Explosion explosion, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PortalAgent getPortalAgent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MutableBiomeVolumeWorker<World> getBiomeWorker() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MutableBlockVolumeWorker<World> getBlockWorker(Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean save() throws IOException {
        throw new UnsupportedOperationException();
    }

    public void triggerUnload() {
        this.loaded = false;
    }

    public void addJoiningPlayer(SpongePlayer playerEntity) {
        synchronized (this.joiningPlayers) {
            this.joiningPlayers.add(playerEntity);
        }
    }

}
