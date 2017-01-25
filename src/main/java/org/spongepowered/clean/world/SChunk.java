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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
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
import org.spongepowered.api.world.schematic.BlockPalette;
import org.spongepowered.clean.block.SBlockSnapshot;
import org.spongepowered.clean.entity.SEntity;
import org.spongepowered.clean.entity.SEntityType;
import org.spongepowered.clean.world.biome.SBiomeType;
import org.spongepowered.clean.world.palette.GlobalPalette;
import org.spongepowered.clean.world.palette.LocalBlockPalette;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.collect.Lists;

public class SChunk implements Chunk {

    public static final Vector3i CHUNK_SIZE = new Vector3i(16, 256, 16);
    public static final Vector3i BIOME_SIZE = new Vector3i(16, 1, 16);

    private final Vector3i min;
    private final Vector3i max;

    private final SWorld world;
    private final ChunkSection[] blocks = new ChunkSection[16];
    private final byte[] biomes;
    private final List<SEntity> entities = Lists.newArrayList();
    private final List<SEntity> toRemove = Lists.newArrayList();

    // TODO track these through modification
    private byte[] heightmap;
    private byte[] skylightHeights;

    // TODO methods for calculating lighting
    // TODO update lighting through modifications
    // TODO allow marking chunks as requiring relighting and handle during
    // serial update

    private boolean physics;
    private boolean lighting;

    private int viewers;

    public SChunk(SWorld world, int x, int z) {
        this.min = new Vector3i(x * 16, 0, z * 16);
        this.max = new Vector3i(x * 16 + 15, 255, z * 16 + 15);
        this.world = world;
        this.biomes = new byte[256];
        this.heightmap = new byte[256];
        this.skylightHeights = new byte[256];
    }

    public void serialUpdate() {
        for (SEntity entity : this.entities) {
            entity.serialUpdate();
        }
        for (SEntity entity : this.toRemove) {
            this.entities.remove(entity);
        }
        this.toRemove.clear();
    }

    public void parallelUpdate() {
        for (SEntity entity : this.entities) {
            entity.parallelUpdate();
        }
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
        return this.min.getX() <= x && this.max.getX() >= x && this.min.getY() <= y && this.max.getY() >= y && this.min.getZ() <= z
                && this.max.getZ() >= z;
    }

    public void addViewer() {
        this.viewers++;
    }

    public void removeViewer() {
        this.viewers--;
    }

    public int getViewers() {
        return this.viewers;
    }

    protected void checkRange(int x, int y, int z) {
        if (!containsBlock(x, y, z)) {
            throw new ArrayIndexOutOfBoundsException(
                    "Position (" + x + ", " + y + ", " + z + ") was outside of volume min " + this.min + " max " + this.max);
        }
    }

    public ChunkSection[] getSections() {
        return this.blocks;
    }

    public byte getHeight(int x, int z) {
        checkRange(x, 0, z);
        return this.heightmap[(x - this.min.getX()) + (z - this.min.getZ()) * 16];
    }

    public void updateHeight(int x, int y, int z, BlockState block) {
        byte height = getHeight(x, z);
        boolean air = block.getType() == BlockTypes.AIR;
        if (y > height && !air) {
            this.heightmap[(x - this.min.getX()) + (z - this.min.getZ()) * 16] = (byte) y;
        } else if (y == height && air) {
            for (; y >= 0; y--) {
                if (getBlock(x, y, z).getType() != BlockTypes.AIR) {
                    this.heightmap[(x - this.min.getX()) + (z - this.min.getZ()) * 16] = (byte) y;
                    return;
                }
            }
            this.heightmap[(x - this.min.getX()) + (z - this.min.getZ()) * 16] = 0;
        }
    }

    public void updateSkyHeight(int x, int y, int z, BlockState block) {
        // TODO check if a block actually blocks light
        byte height = this.skylightHeights[(x - this.min.getX()) + (z - this.min.getZ()) * 16];
        boolean air = block.getType() == BlockTypes.AIR;
        if (y >= height || (y == height && air)) {
            for (; y >= 0; y--) {
                if (getBlock(x, y, z).getType() != BlockTypes.AIR) {
                    this.skylightHeights[(x - this.min.getX()) + (z - this.min.getZ()) * 16] = (byte) y;
                    return;
                }
            }
            this.skylightHeights[(x - this.min.getX()) + (z - this.min.getZ()) * 16] = 0;
        }
    }

    public void recalcSkyLight() {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int height = this.skylightHeights[x + z * 16] & 0xFF;
                for (int y0 = 0; y0 < 16; y0++) {
                    ChunkSection section = this.blocks[y0];
                    for (int y = 0; y < 16; y++) {
                        if (y0 * 16 + y < height) {
                            section.setSkyLight(x, y, z, (byte) 0);
                        } else {
                            section.setSkyLight(x, y, z, (byte) 15);
                        }
                    }
                }
            }
        }
    }

    @Override
    public BlockState getBlock(int x, int y, int z) {
        checkRange(x, y, z);
        ChunkSection section = this.blocks[y >> 4];
        if (section == null) {
            return BlockTypes.AIR.getDefaultState();
        }
        return section.getBlock(x - this.min.getX(), y & 0xFF, z - this.min.getZ());
    }

    @Override
    public BlockType getBlockType(int x, int y, int z) {
        return getBlock(x, y, z).getType();
    }

    @Override
    public boolean setBlock(int x, int y, int z, BlockState block, Cause cause) {
        checkRange(x, y, z);
        ChunkSection section = this.blocks[y >> 4];
        if (section == null) {
            section = new ChunkSection(y >> 4, true);
            this.blocks[y >> 4] = section;
        }
        section.setBlock(x - this.min.getX(), y & 0xFF, z - this.min.getZ(), block);
        if (section.getAirCount() == 16 * 16 * 16) {
            this.blocks[y >> 4] = null;
        }
        updateHeight(x, y, z, block);
        updateSkyHeight(x, y, z, block);
        return true;
    }

    @Override
    public boolean setBlock(int x, int y, int z, BlockState blockState, BlockChangeFlag flag, Cause cause) {
        checkRange(x, y, z);
        // TODO Auto-generated method stub
        // TODO update physics and lighting if flags set
        return setBlock(x, y, z, blockState, cause);
    }

    public void setPhysics(boolean state) {
        this.physics = state;
    }

    public void setLighting(boolean state) {
        this.lighting = state;
    }

    public byte getBlockLight(int x, int y, int z) {
        ChunkSection section = this.blocks[y >> 4];
        if (section == null) {
            return (byte) (y >= this.skylightHeights[x + z * 16] ? 15 : 0);
        }
        return section.getLight(x - this.min.getX(), y & 0xFF, z - this.min.getZ());
    }

    @Override
    public Location<Chunk> getLocation(Vector3i position) {
        return new Location<Chunk>(this, position);
    }

    @Override
    public Location<Chunk> getLocation(Vector3d position) {
        return new Location<Chunk>(this, position);
    }

    @Override
    public BlockSnapshot createSnapshot(int x, int y, int z) {
        BlockState state = getBlock(x, y, z);
        return new SBlockSnapshot(this.world, new Vector3i(x, y, z), state);
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
    public Optional<Entity> getEntity(UUID uuid) {
        for (SEntity entity : this.entities) {
            if (entity.getUniqueId().equals(uuid)) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Collection<Entity> getEntities() {
        return (Collection) this.entities;
    }

    public Collection<SEntity> getSEntities() {
        return this.entities;
    }

    public void addEntity(SEntity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(SEntity entity) {
        this.toRemove.add(entity);
    }

    @Override
    public Collection<Entity> getEntities(Predicate<Entity> filter) {
        return this.entities.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Entity createEntity(EntityType type, Vector3d position) throws IllegalArgumentException, IllegalStateException {
        SEntityType stype = (SEntityType) type;
        if (!stype.canCreate()) {
            throw new IllegalArgumentException("Cannot create an entity of type " + type.getId());
        }
        Entity entity = stype.createEntity(this.world, position);
        return entity;
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
        return Collections.emptyList();
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
        return this.min;
    }

    @Override
    public Vector3i getBiomeMax() {
        return this.min.add(15, 0, 15);
    }

    @Override
    public Vector3i getBiomeSize() {
        return BIOME_SIZE;
    }

    public byte[] getBiomeArray() {
        return this.biomes;
    }

    @Override
    public boolean containsBiome(int x, int y, int z) {
        return y == 0 && containsBlock(x, 0, z);
    }

    protected void checkBiomeRange(int x, int y, int z) {
        if (!containsBiome(x, y, z)) {
            throw new ArrayIndexOutOfBoundsException(
                    "Position (" + x + ", " + y + ", " + z + ") was outside of biome volume min " + this.min + " max " + this.max.sub(0, 255, 0));
        }
    }

    @Override
    public BiomeType getBiome(int x, int y, int z) {
        checkBiomeRange(x, y, z);
        int id = this.biomes[(x - this.min.getX()) + (z - this.min.getZ()) * 16] & 0xFF;
        BiomeType type = SBiomeType.getBiome(id);
        return type;
    }

    @Override
    public void setBiome(int x, int y, int z, BiomeType biome) {
        checkBiomeRange(x, y, z);
        this.biomes[(x - this.min.getX()) + (z - this.min.getZ()) * 16] = (byte) ((SBiomeType) biome).getBiomeId();
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
        // TODO Auto-generated method stub
        return null;
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
    public Vector3i getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public boolean isPopulated() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean loadChunk(boolean generate) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean unloadChunk() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getInhabittedTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getRegionalDifficultyFactor() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getRegionalDifficultyPercentage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public MutableBiomeVolumeWorker<Chunk> getBiomeWorker() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableBlockVolumeWorker<Chunk> getBlockWorker(Cause cause) {
        // TODO Auto-generated method stub
        return null;
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

    public static class ChunkSection {

        private final int y;
        private BlockPalette palette;
        private long[] data;
        private int bitsPerBlock;
        private long currentMax;
        private int airCount;
        // 4-bits per block
        private long[] blockLighting;
        private long[] skyLighting;

        public ChunkSection(int y, boolean hasSky) {
            this.y = y;
            this.palette = new LocalBlockPalette();
            this.airCount = 4096;
            checkSize(8);
            this.blockLighting = new long[256];
            if (hasSky) {
                this.skyLighting = new long[256];
                for (int i = 0; i < 256; i++) {
                    // initial value is 15
                    this.skyLighting[i] = 0xFFFFFFFFFFFFFFFFL;
                }
            } else {
                this.skyLighting = null;
            }
        }

        public BlockPalette getPalette() {
            return this.palette;
        }

        public int getBitsPerBlock() {
            return this.bitsPerBlock;
        }

        public int getY() {
            return this.y;
        }

        public int getAirCount() {
            return this.airCount;
        }

        public long[] getData() {
            return this.data;
        }

        public BlockState getBlock(int x, int y, int z) {
            return this.palette.get(get(x, y, z)).get();
        }

        public byte getLight(int x, int y, int z) {
            int bit = 4 * (x + z * 16 + y * 256);
            return (byte) ((this.blockLighting[bit / 64] >>> (bit % 64)) & 0xF);
        }

        public void setLight(int x, int y, int z, byte level) {
            level &= 0xF;
            int bit = 4 * (x + z * 16 + y * 256);
            long existing = (this.blockLighting[bit / 64] & ~(0xF << (bit % 64)));
            long newd = ((long) level << (bit % 64));
            this.blockLighting[bit / 64] = existing | newd;
        }

        public long[] getBlockLightData() {
            return this.blockLighting;
        }

        public byte getSkyLight(int x, int y, int z) {
            int bit = 4 * (x + z * 16 + y * 256);
            return (byte) ((this.skyLighting[bit / 64] >>> (bit % 64)) & 0xF);
        }

        public void setSkyLight(int x, int y, int z, byte level) {
            level &= 0xF;
            int bit = 4 * (x + z * 16 + y * 256);
            long existing = (this.skyLighting[bit / 64] & ~(0xF << (bit % 64)));
            long newd = ((long) level << (bit % 64));
            this.skyLighting[bit / 64] = existing | newd;
        }

        public long[] getSkyLightData() {
            return this.skyLighting;
        }

        private int get(int x, int y, int z) {
            int bit = this.bitsPerBlock * (x + z * 16 + y * 256);
            if ((bit / 64) != ((bit + this.bitsPerBlock - 1) / 64)) {
                // id is split
                int id = (int) (this.data[bit / 64] >>> (bit % 64));
                int rem = (64 - (bit % 64));
                id |= ((this.data[bit / 64 + 1] << rem) & this.currentMax);
                return id;
            }
            return (int) ((this.data[bit / 64] >>> (bit % 64)) & this.currentMax);
        }

        public void setBlock(int x, int y, int z, BlockState block) {
            int newId = this.palette.getOrAssign(block);
            BlockType oldBlock = getBlock(x, y, z).getType();
            if (oldBlock == BlockTypes.AIR && block.getType() != BlockTypes.AIR) {
                this.airCount--;
            } else if (oldBlock != BlockTypes.AIR && block.getType() == BlockTypes.AIR) {
                this.airCount++;
            }
            if (this.palette.getHighestId() > this.currentMax) {
                checkSize(this.palette.getHighestId());
            }
            int bit = this.bitsPerBlock * (x + z * 16 + y * 256);
            long existing = (this.data[bit / 64] & ~(this.currentMax << (bit % 64)));
            long newd = ((long) newId << (bit % 64));
            this.data[bit / 64] = existing | newd;
            if ((bit / 64) != ((bit + this.bitsPerBlock - 1) / 64)) {
                // id is split
                int rem = (64 - (bit % 64));
                this.data[bit / 64 + 1] = (this.data[bit / 64 + 1] & ~(this.currentMax >>> rem)) | ((long) newId >>> rem);
            }
        }

        private void checkSize(int size) {
            if (size < 8) {
                // both ensures that the size isn't negative
                // and enforces a min bit count of 4
                size = 8;
            }
            int max = Integer.highestOneBit(size << 1) - 1;
            int bits = Integer.bitCount(max);
            BlockPalette newpalette = this.palette;
            if (bits != this.bitsPerBlock) {
                if (bits > 8) {
                    if (this.bitsPerBlock > 8) {
                        return;
                    }
                    size = GlobalPalette.instance.getHighestId();
                    max = Integer.highestOneBit(size << 1) - 1;
                    bits = Integer.bitCount(max);
                    newpalette = GlobalPalette.instance;
                }
                long[] newData = new long[(4096 * bits) / 64];
                int bit = 0;
                // iterate in this order to match the indexing of
                // x + x * 16 + y * 256
                for (int y = 0; y < 16; y++) {
                    for (int z = 0; z < 16; z++) {
                        for (int x = 0; x < 16; x++) {
                            int newId = 0;
                            if (this.data != null) {
                                newId = get(x, y, z);
                            }
                            if (newpalette != this.palette) {
                                BlockState state = this.palette.get(newId).get();
                                newId = newpalette.getOrAssign(state);
                            }
                            newData[bit / 64] = (newData[bit / 64] & ~(max << (bit % 64))) | ((long) newId << (bit % 64));
                            if ((bit / 64) != ((bit + bits - 1) / 64)) {
                                // id is split
                                int rem = (64 - (bit % 64));
                                newData[bit / 64 + 1] = (newData[bit / 64 + 1] & ~(max >>> rem)) | ((long) newId >>> rem);
                            }
                            bit += bits;
                        }
                    }
                }
                this.data = newData;
                this.bitsPerBlock = bits;
                this.currentMax = max;
                this.palette = newpalette;
            }
        }

    }

}
