/*
 * Copyright (c) 2015-2016 VoxelBox <http://engine.thevoxelbox.com>.
 * All Rights Reserved.
 */
package org.spongepowered.clean.world.gen.base;

import java.util.Random;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.biome.BiomeGenerationSettings;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;
import org.spongepowered.clean.world.SWorld;
import org.spongepowered.clean.world.buffer.SImmutableBiomeVolume;
import org.spongepowered.clean.world.gen.SWorldGenerator;
import org.spongepowered.clean.world.gen.noise.NoiseGenerator;
import org.spongepowered.clean.world.gen.noise.SimplexNoise;

public class OverworldGenerator implements GenerationPopulator {

    private final SWorld world;
    private final SWorldGenerator worldgen;
    private NoiseGenerator noise;
    private Random rand;
    private double[] noisemap = new double[256];

    public OverworldGenerator(SWorld world, SWorldGenerator worldgen) {
        this.world = world;
        this.rand = new Random(world.getProperties().getSeed());
        this.noise = new NoiseGenerator(this.rand, 8, 0.45, 0.008);
        this.worldgen = worldgen;
    }

    @Override
    public void populate(World world, MutableBlockVolume buffer, ImmutableBiomeVolume biomes) {
        SImmutableBiomeVolume sbiomes = (SImmutableBiomeVolume) biomes;
        int x = buffer.getBlockMin().getX();
        int z = buffer.getBlockMin().getZ();
        int cx = x >> 4;
        int cz = z >> 4;
        long seed = (((cx & 0xFFFFFFFFL) << 32) | (cz & 0xFFFFFFFFL)) ^ world.getProperties().getSeed();
        this.rand.setSeed(seed);

        this.noise.getNoise(this.noisemap, x, z, 16, 16);

        for (int x0 = 0; x0 < 16; x0++) {
            for (int z0 = 0; z0 < 16; z0++) {
                double noise = this.noisemap[x0 + z0 * 16];

                BiomeGenerationSettings biome = this.worldgen.getBiomeSettings(biomes.getBiome(x + x0, 0, z + z0));
                double heightMin = biome.getMinHeight() * 0.33;
                double heightMax = biome.getMaxHeight() * 0.33;
                BiomeGenerationSettings xn = this.worldgen.getBiomeSettings(biomes.getBiome(x + x0 - 4, 0, z + z0));
                heightMin += xn.getMinHeight() * 0.166;
                heightMax += xn.getMaxHeight() * 0.166;
                BiomeGenerationSettings xp = this.worldgen.getBiomeSettings(biomes.getBiome(x + x0 + 4, 0, z + z0));
                heightMin += xp.getMinHeight() * 0.166;
                heightMax += xp.getMaxHeight() * 0.166;
                BiomeGenerationSettings zn = this.worldgen.getBiomeSettings(biomes.getBiome(x + x0, 0, z + z0 - 4));
                heightMin += zn.getMinHeight() * 0.166;
                heightMax += zn.getMaxHeight() * 0.166;
                BiomeGenerationSettings zp = this.worldgen.getBiomeSettings(biomes.getBiome(x + x0, 0, z + z0 + 4));
                heightMin += zp.getMinHeight() * 0.166;
                heightMax += zp.getMaxHeight() * 0.166;
                double weight = 0.5; //sbiomes.getBiomeWeight(x + x0, 0, z + z0);
                double var = (heightMax - heightMin);
                double pos = var * weight;
                double window = var / 4;
//                double min = pos - window;
//                double max = pos + window;
//                if(min < 0) {
//                    min = 0;
//                }
//                if(max > var) {
//                    max = var;
//                }
//                window = (max - min) / 2;
//                pos = min + window;
                int height = SimplexNoise.fastfloor(heightMin + pos + noise * window);
                for (int y = 255; y >= 0; y--) {
                    if (y < this.rand.nextInt(5)) {
                        buffer.setBlock(x0 + x, y, z0 + z, BlockTypes.BEDROCK.getDefaultState(), null);
                    } else if (y < height) {
                        buffer.setBlock(x0 + x, y, z0 + z, BlockTypes.STONE.getDefaultState(), null);
                    } else if (y < 15) {
                        buffer.setBlock(x0 + x, y, z0 + z, BlockTypes.WATER.getDefaultState(), null);
                    }
                }
            }
        }
    }

}
