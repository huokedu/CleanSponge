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

import java.util.Arrays;

import org.spongepowered.api.util.DiscreteTransform3;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBiomeVolume;
import org.spongepowered.api.world.extent.StorageType;
import org.spongepowered.api.world.extent.UnmodifiableBiomeVolume;
import org.spongepowered.api.world.extent.worker.BiomeVolumeWorker;
import org.spongepowered.clean.world.biome.SBiomeType;

import com.flowpowered.math.vector.Vector3i;

public class SImmutableBiomeVolume extends AbstractVolume implements ImmutableBiomeVolume {

    private final byte[] data;
    private final float[] weights;

    public SImmutableBiomeVolume(Vector3i min, Vector3i size) {
        super(min, size);
        this.data = new byte[size.getX() * size.getY() * size.getZ()];
        this.weights = null;
    }

    public SImmutableBiomeVolume(Vector3i min, Vector3i size, byte[] data) {
        super(min, size);
        this.data = Arrays.copyOf(data, data.length);
        this.weights = null;
    }

    public SImmutableBiomeVolume(Vector3i min, Vector3i size, byte[] data, float[] weights) {
        super(min, size);
        this.data = Arrays.copyOf(data, data.length);
        this.weights = Arrays.copyOf(weights, weights.length);
    }

    @Override
    public Vector3i getBiomeMin() {
        return this.min;
    }

    @Override
    public Vector3i getBiomeMax() {
        return this.max;
    }

    @Override
    public Vector3i getBiomeSize() {
        return this.size;
    }

    @Override
    public boolean containsBiome(int x, int y, int z) {
        return contains(x, y, z);
    }

    @Override
    public BiomeType getBiome(int x, int y, int z) {
        checkRange(x, y, z);
        int id = this.data[getIndex(x, y, z)] & 0xFF;
        BiomeType type = SBiomeType.getBiome(id);
        return type;
    }

    public float getBiomeWeight(int x, int y, int z) {
        checkRange(x, y, z);
        return this.weights[getIndex(x, y, z)];
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
        return this;
    }

    @Override
    public ImmutableBiomeVolume getBiomeView(Vector3i newMin, Vector3i newMax) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableBiomeVolume getBiomeView(DiscreteTransform3 transform) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BiomeVolumeWorker<? extends ImmutableBiomeVolume> getBiomeWorker() {
        // TODO Auto-generated method stub
        return null;
    }

}
