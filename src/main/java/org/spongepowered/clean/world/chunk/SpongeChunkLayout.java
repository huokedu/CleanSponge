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

import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.world.storage.ChunkLayout;

public class SpongeChunkLayout implements ChunkLayout {

    private final Vector3i min;
    private final Vector3i max;
    private final Vector3i size;

    public SpongeChunkLayout(Vector3i min, Vector3i max) {
        this.min = min;
        this.max = max;
        this.size = this.max.sub(this.min).add(1, 1, 1);
    }

    @Override
    public Vector3i getChunkSize() {
        return SpongeChunk.CHUNK_SIZE;
    }

    @Override
    public Vector3i getSpaceMax() {
        return this.max;
    }

    @Override
    public Vector3i getSpaceMin() {
        return this.min;
    }

    @Override
    public Vector3i getSpaceSize() {
        return this.size;
    }

    @Override
    public Vector3i getSpaceOrigin() {
        return Vector3i.ZERO;
    }

    @Override
    public boolean isInChunk(int x, int y, int z) {
        if (x >= 0 && x < SpongeChunk.CHUNK_SIZE.getX() && y >= 0 && y < SpongeChunk.CHUNK_SIZE.getY() && z >= 0
                && z < SpongeChunk.CHUNK_SIZE.getZ()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isInChunk(int wx, int wy, int wz, int cx, int cy, int cz) {
        int wcx = cx * SpongeChunk.CHUNK_SIZE.getX();
        int wcy = cy * SpongeChunk.CHUNK_SIZE.getY();
        int wcz = cz * SpongeChunk.CHUNK_SIZE.getZ();
        if (wx >= wcx && wx < wcx + SpongeChunk.CHUNK_SIZE.getX() && wy >= wcy && wy < wcy + SpongeChunk.CHUNK_SIZE.getY() && wz >= wcz
                && wz < wcz + SpongeChunk.CHUNK_SIZE.getZ()) {
            return true;
        }
        return false;
    }

    @Override
    public Vector3i forceToChunk(int x, int y, int z) {
        return new Vector3i(x >> 4, 0, z >> 4);
    }

    @Override
    public Vector3i forceToWorld(int x, int y, int z) {
        return new Vector3i(x << 4, 0, z << 4);
    }

}
