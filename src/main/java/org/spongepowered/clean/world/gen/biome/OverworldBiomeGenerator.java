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
package org.spongepowered.clean.world.gen.biome;

import java.util.Random;

import org.spongepowered.api.world.biome.BiomeTypes;
import org.spongepowered.api.world.extent.MutableBiomeVolume;
import org.spongepowered.api.world.gen.BiomeGenerator;
import org.spongepowered.clean.world.SWorld;
import org.spongepowered.clean.world.buffer.SMutableBiomeVolume;
import org.spongepowered.clean.world.gen.noise.NoiseGenerator;

public class OverworldBiomeGenerator implements BiomeGenerator {

    private final SWorld world;

    private final NoiseGenerator heightNoise;
    private final NoiseGenerator percipNoise;

    // Performs a consistent scramble of the seed so that noise maps can differ
    // for the same location
    private static long scramble(long seed) {
        long s = 0xAAAAAAAAAAAAAAAAL;
        return (s ^ seed) ^ (seed >>> 20) ^ (seed >>> 14) ^ (seed >>> 10);
    }

    public OverworldBiomeGenerator(SWorld world) {
        this.world = world;
        this.heightNoise = new NoiseGenerator(new Random(world.getProperties().getSeed()), 4, 0.4, 0.003);
        this.percipNoise = new NoiseGenerator(new Random(scramble(world.getProperties().getSeed())), 4, 0.5, 0.02);
    }

    @Override
    public void generateBiomes(MutableBiomeVolume buffer) {
        SMutableBiomeVolume sbuffer = (SMutableBiomeVolume) buffer;
        int minx = buffer.getBiomeMin().getX();
        int minz = buffer.getBiomeMin().getZ();
        int maxx = buffer.getBiomeMax().getX();
        int maxz = buffer.getBiomeMax().getZ();
        for (int x = minx; x <= maxx; x++) {
            for (int z = minz; z <= maxz; z++) {
                double height = this.heightNoise.getNoise(x, z);
                if (height < -0.5) {
                    // ocean
                    buffer.setBiome(x, 0, z, BiomeTypes.OCEAN);
                    sbuffer.setBiomeWeight(x, 0, z, (float) ((height + 1) * 2));
                } else if (height < 0.4) {
                    // midlands
                    buffer.setBiome(x, 0, z, BiomeTypes.PLAINS);
                    sbuffer.setBiomeWeight(x, 0, z, (float) ((height + 0.5) / 0.9));
                } else {
                    // mountains
                    buffer.setBiome(x, 0, z, BiomeTypes.EXTREME_HILLS);
                    sbuffer.setBiomeWeight(x, 0, z, (float) ((height - 0.4) / 0.6));
                }
            }
        }

    }

}
