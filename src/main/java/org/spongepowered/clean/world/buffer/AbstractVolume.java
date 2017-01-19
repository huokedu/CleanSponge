package org.spongepowered.clean.world.buffer;

import com.flowpowered.math.vector.Vector3i;

public class AbstractVolume {

    protected final Vector3i min;
    protected final Vector3i max;
    protected final Vector3i size;

    public AbstractVolume(Vector3i min, Vector3i size) {
        this.min = min;
        this.size = size;
        this.max = this.min.add(size).sub(1, 1, 1);
    }

    public Vector3i getBlockMin() {
        return this.min;
    }

    public Vector3i getBlockMax() {
        return this.max;
    }

    public Vector3i getBlockSize() {
        return this.size;
    }

    public boolean contains(int x, int y, int z) {
        return this.min.getX() <= x && this.max.getX() >= x && this.min.getY() <= y && this.max.getY() >= y && this.min.getZ() <= z
                && this.max.getZ() >= z;
    }

    protected void checkRange(int x, int y, int z) {
        if (!contains(x, y, z)) {
            throw new ArrayIndexOutOfBoundsException(
                    "Position (" + x + ", " + y + ", " + z + ") was outside of volume min " + this.min + " max " + this.max);
        }
    }

    protected int getIndex(int x, int y, int z) {
        return (x - this.min.getX()) + (y - this.min.getY()) * this.size.getX() + (z - this.min.getZ()) * this.size.getX() * this.size.getZ();
    }
}
