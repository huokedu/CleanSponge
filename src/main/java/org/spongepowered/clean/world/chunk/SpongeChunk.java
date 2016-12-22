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
package org.spongepowered.clean.world.chunk;

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
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntitySnapshot;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.util.AABB;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.util.DiscreteTransform3;
import org.spongepowered.api.world.BlockChangeFlag;
import org.spongepowered.api.world.Chunk;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.biome.BiomeType;
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
import org.spongepowered.clean.block.tileentity.SpongeTileEntity;
import org.spongepowered.clean.world.CoordinateOutOfBoundsException;
import org.spongepowered.clean.world.SpongeWorld;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SpongeChunk implements Chunk {

    public static final Vector3i                           CHUNK_SIZE = new Vector3i(16, 256, 16);

    private final SpongeWorld                              world;
    private final ChunkDataBuffer                          data;
    private final Long2ObjectOpenHashMap<SpongeTileEntity> tiles      = new Long2ObjectOpenHashMap<>();
    private final List<SpongeTileEntity>                   tilesList  = Lists.newArrayList();
    private final Vector3i                                 min;
    private final Vector3i                                 max;

    public SpongeChunk(SpongeWorld world, int x, int z, ChunkDataBuffer data) {
        this.world = world;
        this.min = new Vector3i(x * 16, 0, z * 16);
        this.max = this.min.add(CHUNK_SIZE).sub(1, 1, 1);
        this.data = data;
    }

    @Override
    public Location<Chunk> getLocation(Vector3i position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Location<Chunk> getLocation(Vector3d position) {
        throw new UnsupportedOperationException();
    }

    public boolean isValid(int x, int y, int z) {
        if (x >= this.min.getX() && x <= this.max.getX() && y >= this.min.getY() && y <= this.max.getY() && z >= this.min.getZ()
                && z <= this.max.getZ()) {
            return true;
        }
        return false;
    }

    @Override
    public BlockState getBlock(int x, int y, int z) {
        if (!isValid(x, y, z)) {
            throw new CoordinateOutOfBoundsException(this.min, this.max, x, y, z);
        }
        return this.data.get(x - this.min.getX(), y - this.min.getY(), z - this.min.getZ());
    }

    @Override
    public BlockType getBlockType(int x, int y, int z) {
        return getBlock(x, y, z).getType();
    }

    @Override
    public boolean setBlock(int x, int y, int z, BlockState blockState, BlockChangeFlag flag, Cause cause) {
        // TODO block updates
        return setBlock(x, y, z, blockState, cause);
    }

    @Override
    public boolean setBlock(int x, int y, int z, BlockState block, Cause cause) {
        if (!isValid(x, y, z)) {
            throw new CoordinateOutOfBoundsException(this.min, this.max, x, y, z);
        }
        this.data.set(x - this.min.getX(), y - this.min.getY(), z - this.min.getZ(), block);
        return true;
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
        throw new UnsupportedOperationException();
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
    public Optional<Entity> getEntity(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Entity> getEntities() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Entity> getEntities(Predicate<Entity> filter) {
        throw new UnsupportedOperationException();
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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Collection<TileEntity> getTileEntities() {
        return (Collection) this.tilesList;
    }

    @Override
    public Collection<TileEntity> getTileEntities(Predicate<TileEntity> filter) {
        return getTileEntities().stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Optional<TileEntity> getTileEntity(int x, int y, int z) {
        x -= this.min.getX();
        y -= this.min.getY();
        z -= this.min.getZ();
        return Optional.ofNullable(this.tiles.get(x + z * 16 + y * 256));
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
        return this.min;
    }

    @Override
    public Vector3i getBlockMax() {
        return this.max;
    }

    @Override
    public Vector3i getBlockSize() {
        return CHUNK_SIZE;
    }

    @Override
    public boolean containsBlock(int x, int y, int z) {
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
    public Vector3i getPosition() {
        return new Vector3i(this.min.getX() >> 4, 0, this.min.getZ() >> 4);
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public boolean isPopulated() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean loadChunk(boolean generate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean unloadChunk() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInhabittedTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getRegionalDifficultyFactor() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getRegionalDifficultyPercentage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MutableBiomeVolumeWorker<Chunk> getBiomeWorker() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MutableBlockVolumeWorker<Chunk> getBlockWorker(Cause cause) {
        throw new UnsupportedOperationException();
    }

}
