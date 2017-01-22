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
        return (x - this.min.getX()) + (y - this.min.getY()) * this.size.getX() + (z - this.min.getZ()) * this.size.getX() * this.size.getY();
    }
}
