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
import org.spongepowered.api.Sponge;
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
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.title.Title;
import org.spongepowered.api.util.AABB;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.util.DiscreteTransform3;
import org.spongepowered.api.world.BlockChangeFlag;
import org.spongepowered.api.world.Chunk;
import org.spongepowered.api.world.ChunkPreGenerate;
import org.spongepowered.api.world.Dimension;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.PortalAgent;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.WorldBorder;
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
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.entity.SEntity;
import org.spongepowered.clean.entity.player.SPlayer;
import org.spongepowered.clean.scheduler.condition.ResourceMutex;
import org.spongepowered.clean.world.gen.SWorldGenerator;
import org.spongepowered.clean.world.storage.SaveHandler;
import org.spongepowered.clean.world.tasks.WorldTickTask;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

public class SWorld implements World {

    private final WorldTickTask tick_task = new WorldTickTask(this);
    private final ResourceMutex world_mutex = new ResourceMutex();

    private final String name;
    private final SWorldProperties properties;

    private final SaveHandler saveHandler;
    private final Long2ObjectOpenHashMap<SChunk> chunks = new Long2ObjectOpenHashMap<>();
    private final List<SChunk> chunkList = Lists.newArrayList();
    private final List<SChunk> toAdd = Lists.newArrayList();
    private final SWorldGenerator generator;
    private final SDimension dimension;

    private final List<SEntity> allEntities = Lists.newArrayList();
    private final List<SPlayer> joiningPlayers = Collections.<SPlayer>synchronizedList(Lists.newArrayList());

    private int entityid = 0;

    public SWorld(String name, SWorldProperties props) {
        this.name = name;
        this.properties = props;
        this.generator = (SWorldGenerator) this.properties.getGeneratorType().createGenerator(this);
        this.saveHandler = props.getSaveHandler();
        this.dimension = SGame.game.getDimensionManager().getDimension(this.properties.getDimensionType());
        // TODO setup generator based on worldtype
    }

    public void addJoiningPlayer(SPlayer player) {
        this.joiningPlayers.add(player);
    }

    @Override
    public String getName() {
        return this.name;
    }

    public WorldTickTask getTickTask() {
        return this.tick_task;
    }

    public ResourceMutex getMutex() {
        return this.world_mutex;
    }

    public void init() {
        if (this.properties.doesKeepSpawnLoaded() || this.properties.doesGenerateSpawnOnLoad()) {
            
        }
    }

    public void serialUpdate() {
        for (Iterator<SPlayer> it = this.joiningPlayers.iterator(); it.hasNext();) {
            SPlayer player = it.next();
            it.remove();
            this.allEntities.add(player);
            SChunk chunk = getOrLoadChunk(player.getChunkX(), player.getChunkZ());
            chunk.addEntity(player);
            player.setCurrentChunk(chunk);
            player.setEntityId(this.entityid++);
            player.getNetConnection().joinGame();
            SGame.game.server.addPlayer(player);
            Sponge.getServer().getBroadcastChannel().send(Text.of(TextColors.YELLOW, player.getName() + " has joined the game."));
        }

        for (SChunk c : this.toAdd) {
            this.chunkList.add(c);
        }
        this.toAdd.clear();
    }

    public SChunk getOrLoadChunk(int x, int z) {
        SChunk chunk = getLoadedChunk(x, z);
        if (chunk != null) {
            return chunk;
        }
        chunk = this.saveHandler.loadChunk(x, z);
        long key = ((x & 0xFFFFFFFFL) << 32) | (z & 0xFFFFFFFFL);
        if (chunk != null) {
            this.chunks.put(key, chunk);
            this.toAdd.add(chunk);
            return chunk;
        }
        chunk = this.generator.generateChunk(x, z);
        this.chunks.put(key, chunk);
        this.toAdd.add(chunk);
        return chunk;
    }

    public SChunk getLoadedChunk(int x, int z) {
        long key = ((x & 0xFFFFFFFFL) << 32) | (z & 0xFFFFFFFFL);
        return this.chunks.get(key);
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
    public BlockSnapshot createSnapshot(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean restoreSnapshot(BlockSnapshot snapshot, boolean force, BlockChangeFlag flag, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean restoreSnapshot(int x, int y, int z, BlockSnapshot snapshot, boolean force, BlockChangeFlag flag, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<ScheduledBlockUpdate> getScheduledUpdates(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ScheduledBlockUpdate addScheduledUpdate(int x, int y, int z, int priority, int ticks) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeScheduledUpdate(int x, int y, int z, ScheduledBlockUpdate update) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isLoaded() {
        return true;
    }

    @Override
    public Extent getExtentView(Vector3i newMin, Vector3i newMax) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<UUID> getCreator(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<UUID> getNotifier(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCreator(int x, int y, int z, UUID uuid) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setNotifier(int x, int y, int z, UUID uuid) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<AABB> getBlockSelectionBox(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<AABB> getIntersectingBlockCollisionBoxes(AABB box) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<AABB> getIntersectingCollisionBoxes(Entity owner, AABB box) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArchetypeVolume createArchetypeVolume(Vector3i min, Vector3i max, Vector3i origin) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection<Entity> getEntities() {
        return (Collection) this.allEntities;
    }

    public Collection<SEntity> getSEntities() {
        return this.allEntities;
    }

    @Override
    public Collection<Entity> getEntities(Predicate<Entity> filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity createEntity(EntityType type, Vector3d position) throws IllegalArgumentException, IllegalStateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity createEntityNaturally(EntityType type, Vector3d position) throws IllegalArgumentException, IllegalStateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Entity> createEntity(DataContainer entityContainer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Entity> createEntity(DataContainer entityContainer, Vector3d position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Entity> restoreSnapshot(EntitySnapshot snapshot, Vector3d position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean spawnEntity(Entity entity, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean spawnEntities(Iterable<? extends Entity> entities, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Entity> getIntersectingEntities(AABB box, Predicate<Entity> filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<EntityHit> getIntersectingEntities(Vector3d start, Vector3d end, Predicate<EntityHit> filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<EntityHit> getIntersectingEntities(Vector3d start, Vector3d direction, double distance, Predicate<EntityHit> filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<TileEntity> getTileEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<TileEntity> getTileEntities(Predicate<TileEntity> filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<TileEntity> getTileEntity(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean setBlock(int x, int y, int z, BlockState block, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public MutableBlockVolume getBlockView(Vector3i newMin, Vector3i newMax) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableBlockVolume getBlockView(DiscreteTransform3 transform) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector3i getBlockMin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector3i getBlockMax() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector3i getBlockSize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean containsBlock(int x, int y, int z) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BlockState getBlock(int x, int y, int z) {
        SChunk chunk = getOrLoadChunk(x >> 4, z >> 4);
        return chunk.getBlock(x, y, z);
    }

    @Override
    public BlockType getBlockType(int x, int y, int z) {
        return getBlock(x, y, z).getType();
    }

    @Override
    public boolean setBlock(int x, int y, int z, BlockState blockState, BlockChangeFlag flag, Cause cause) {
        SChunk chunk = getOrLoadChunk(x >> 4, z >> 4);
        return chunk.setBlock(x, y, z, blockState, flag, cause);
    }

    @Override
    public UnmodifiableBlockVolume getUnmodifiableBlockView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableBlockVolume getBlockCopy(StorageType type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableBlockVolume getImmutableBlockCopy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hitBlock(int x, int y, int z, Direction side, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean interactBlock(int x, int y, int z, Direction side, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean interactBlockWith(int x, int y, int z, ItemStack itemStack, Direction side, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean placeBlock(int x, int y, int z, BlockState block, Direction side, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean digBlock(int x, int y, int z, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean digBlockWith(int x, int y, int z, ItemStack itemStack, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getBlockDigTimeWith(int x, int y, int z, ItemStack itemStack, Cause cause) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public MutableBiomeVolume getBiomeView(Vector3i newMin, Vector3i newMax) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableBiomeVolume getBiomeView(DiscreteTransform3 transform) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector3i getBiomeMin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector3i getBiomeMax() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector3i getBiomeSize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean containsBiome(int x, int y, int z) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BiomeType getBiome(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBiome(int x, int y, int z, BiomeType biome) {
        // TODO Auto-generated method stub

    }

    @Override
    public UnmodifiableBiomeVolume getUnmodifiableBiomeView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableBiomeVolume getBiomeCopy(StorageType type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableBiomeVolume getImmutableBiomeCopy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> Optional<E> get(int x, int y, int z, Key<? extends BaseValue<E>> key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> get(int x, int y, int z, Class<T> manipulatorClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> getOrCreate(int x, int y, int z, Class<T> manipulatorClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E, V extends BaseValue<E>> Optional<V> getValue(int x, int y, int z, Key<V> key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(int x, int y, int z, Key<?> key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean supports(int x, int y, int z, Class<? extends DataManipulator<?, ?>> manipulatorClass) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Key<?>> getKeys(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<ImmutableValue<?>> getValues(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> DataTransactionResult offer(int x, int y, int z, Key<? extends BaseValue<E>> key, E value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> DataTransactionResult offer(int x, int y, int z, Key<? extends BaseValue<E>> key, E value, Cause cause) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult offer(int x, int y, int z, DataManipulator<?, ?> manipulator, MergeFunction function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult offer(int x, int y, int z, DataManipulator<?, ?> manipulator, MergeFunction function, Cause cause) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult remove(int x, int y, int z, Class<? extends DataManipulator<?, ?>> manipulatorClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult remove(int x, int y, int z, Key<?> key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult undo(int x, int y, int z, DataTransactionResult result) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult copyFrom(int xTo, int yTo, int zTo, DataHolder from) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult copyFrom(int xTo, int yTo, int zTo, DataHolder from, MergeFunction function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult copyFrom(int xTo, int yTo, int zTo, int xFrom, int yFrom, int zFrom, MergeFunction function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<DataManipulator<?, ?>> getManipulators(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validateRawData(int x, int y, int z, DataView container) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setRawData(int x, int y, int z, DataView container) throws InvalidDataException {
        // TODO Auto-generated method stub

    }

    @Override
    public UUID getUniqueId() {
        return this.properties.getUniqueId();
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getProperty(int x, int y, int z, Class<T> propertyClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getProperty(int x, int y, int z, Direction direction, Class<T> propertyClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Property<?, ?>> getProperties(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Direction> getFacesWithProperty(int x, int y, int z, Class<? extends Property<?, ?>> propertyClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Weather getWeather() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getRemainingDuration() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getRunningDuration() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWeather(Weather weather) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setWeather(Weather weather, long duration) {
        // TODO Auto-generated method stub

    }

    @Override
    public void spawnParticles(ParticleEffect particleEffect, Vector3d position) {
        // TODO Auto-generated method stub

    }

    @Override
    public void spawnParticles(ParticleEffect particleEffect, Vector3d position, int radius) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume, double pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume, double pitch, double minVolume) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendTitle(Title title) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendBookView(BookView bookView) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendBlockChange(int x, int y, int z, BlockState state) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetBlockChange(int x, int y, int z) {
        // TODO Auto-generated method stub

    }

    @Override
    public Context getContext() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendMessage(Text message) {
        // TODO Auto-generated method stub

    }

    @Override
    public MessageChannel getMessageChannel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setMessageChannel(MessageChannel channel) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendMessage(ChatType type, Text message) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<Player> getPlayers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Chunk> getChunk(int cx, int cy, int cz) {
        return Optional.ofNullable(getLoadedChunk(cx, cz));
    }

    @Override
    public Optional<Chunk> loadChunk(int cx, int cy, int cz, boolean shouldGenerate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean unloadChunk(Chunk chunk) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Iterable<Chunk> getLoadedChunks() {
        return (Collection) this.chunks.values();
    }

    public List<SChunk> getSChunks() {
        return this.chunkList;
    }

    @Override
    public Optional<Entity> getEntity(UUID uuid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WorldBorder getWorldBorder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChunkPreGenerate.Builder newChunkPreGenerate(Vector3d center, double diameter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    @Override
    public WorldGenerator getWorldGenerator() {
        return this.generator;
    }

    @Override
    public WorldProperties getProperties() {
        return this.properties;
    }

    @Override
    public Path getDirectory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WorldStorage getWorldStorage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void triggerExplosion(Explosion explosion, Cause cause) {
        // TODO Auto-generated method stub

    }

    @Override
    public PortalAgent getPortalAgent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableBiomeVolumeWorker<World> getBiomeWorker() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableBlockVolumeWorker<World> getBlockWorker(Cause cause) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save() throws IOException {
        // TODO Auto-generated method stub
        return false;
    }

}
