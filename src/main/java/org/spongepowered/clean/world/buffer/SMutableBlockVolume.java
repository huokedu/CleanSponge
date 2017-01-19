package org.spongepowered.clean.world.buffer;

import java.util.Arrays;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.util.DiscreteTransform3;
import org.spongepowered.api.world.extent.ImmutableBlockVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.extent.StorageType;
import org.spongepowered.api.world.extent.UnmodifiableBlockVolume;
import org.spongepowered.api.world.extent.worker.MutableBlockVolumeWorker;
import org.spongepowered.api.world.schematic.BlockPalette;

import com.flowpowered.math.vector.Vector3i;

public class SMutableBlockVolume implements MutableBlockVolume {

    private final BlockPalette palette;
    private final Vector3i min;
    private final Vector3i max;
    private final Vector3i size;

    private final char[] data;

    public SMutableBlockVolume(BlockPalette palette, Vector3i min, Vector3i size) {
        this.palette = palette;
        this.min = min;
        this.size = size;
        this.max = this.min.add(size).sub(1, 1, 1);
        this.data = new char[this.size.getX() * this.size.getY() * this.size.getZ()];
        Arrays.fill(this.data, (char) (this.palette.getOrAssign(BlockTypes.AIR.getDefaultState()) & 0xFFFF));
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
        return this.size;
    }

    @Override
    public boolean containsBlock(int x, int y, int z) {
        return this.min.getX() <= x && this.max.getX() >= x && this.min.getY() <= y && this.max.getY() >= y && this.min.getZ() <= z
                && this.max.getZ() >= z;
    }

    private void checkRange(int x, int y, int z) {
        if (!containsBlock(x, y, z)) {
            throw new ArrayIndexOutOfBoundsException(
                    "Block position (" + x + ", " + y + ", " + z + ") was outside of volume min " + this.min + " max " + this.max);
        }
    }

    private int getIndex(int x, int y, int z) {
        return (x - this.min.getX()) + (y - this.min.getY()) * this.size.getX() + (z - this.min.getZ()) * this.size.getX() * this.size.getZ();
    }

    @Override
    public BlockState getBlock(int x, int y, int z) {
        checkRange(x, y, z);
        return this.palette.get(this.data[getIndex(x, y, z)]).get();
    }

    @Override
    public BlockType getBlockType(int x, int y, int z) {
        checkRange(x, y, z);
        return getBlock(x, y, z).getType();
    }

    @Override
    public boolean setBlock(int x, int y, int z, BlockState block, Cause cause) {
        checkRange(x, y, z);
        int id = this.palette.getOrAssign(block);
        this.data[x + y * this.size.getX() + z * this.size.getX() * this.size.getZ()] = (char) (id & 0xFFFF);
        return true;
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
    public MutableBlockVolumeWorker<? extends MutableBlockVolume> getBlockWorker(Cause cause) {
        // TODO Auto-generated method stub
        return null;
    }

}
