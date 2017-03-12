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
package org.spongepowered.clean.world.gen.base;

import java.util.Random;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.biome.BiomeTypes;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;
import org.spongepowered.clean.util.Maths;
import org.spongepowered.clean.world.SWorld;
import org.spongepowered.clean.world.gen.SWorldGenerator;
import org.spongepowered.clean.world.gen.noise.NoiseGenerator;
import org.spongepowered.clean.world.gen.noise.SimplexNoise;

public class OverworldGenerator implements GenerationPopulator {

    private final SWorld world;
    private final SWorldGenerator worldgen;
    private NoiseGenerator noise1;
    private Random rand;
    private MutableBiomeVolume mbiomes;

    public OverworldGenerator(SWorld world, SWorldGenerator worldgen) {
        this.world = world;
        this.rand = new Random(world.getProperties().getSeed());
        this.noise1 = new NoiseGenerator(this.rand, 8, 0.45, 0.002);
        this.worldgen = worldgen;
    }

    public void setBiomes(MutableBiomeVolume biomes) {
        this.mbiomes = biomes;
    }

    @Override
    public void populate(World world, MutableBlockVolume buffer, ImmutableBiomeVolume biomes) {
        int x = buffer.getBlockMin().getX();
        int z = buffer.getBlockMin().getZ();
        int cx = x >> 4;
        int cz = z >> 4;
        long seed = (((cx & 0xFFFFFFFFL) << 32) | (cz & 0xFFFFFFFFL)) ^ world.getProperties().getSeed();
        this.rand.setSeed(seed);

        for (int x0 = 0; x0 < 16; x0++) {
            for (int z0 = 0; z0 < 16; z0++) {
                double heightnoise = this.noise1.getNoise(x + x0 + 915375, z + z0 + 915375);
                int maxheight = SimplexNoise.fastfloor(heightnoise * 96 + 64);
                BiomeType biome = BiomeTypes.PLAINS;
                if (maxheight < 64) {
                    biome = BiomeTypes.OCEAN;
                } else if (maxheight < 68) {
                    biome = BiomeTypes.BEACH;
                } else if (maxheight < 90) {
                    biome = BiomeTypes.PLAINS;
                } else {
                    biome = BiomeTypes.EXTREME_HILLS;
                }
                this.mbiomes.setBiome(x0 + x, 0, z0 + z, biome);
                for (int y = 255; y >= 0; y--) {
                    if (y == 0 || y < this.rand.nextInt(5)) {
                        buffer.setBlock(x0 + x, y, z0 + z, BlockTypes.BEDROCK.getDefaultState(), null);
                    } else if (y <= maxheight) {
                        buffer.setBlock(x0 + x, y, z0 + z, BlockTypes.STONE.getDefaultState(), null);
                    }
                }
            }
        }
    }

}
