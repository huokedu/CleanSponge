/*
 * Copyright (c) 2015-2016 VoxelBox <http://engine.thevoxelbox.com>.
 * All Rights Reserved.
 */
package org.spongepowered.clean.world.gen.base;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;
import org.spongepowered.clean.world.SWorld;
import org.spongepowered.clean.world.gen.noise.NoiseGenerator;
import org.spongepowered.clean.world.gen.noise.SimplexNoise;

import java.util.Random;

public class OverworldGenerator implements GenerationPopulator {

    private final SWorld world;
    private NoiseGenerator noise;
    private Random rand;
    private double[] noisemap = new double[256];

    public OverworldGenerator(SWorld world) {
        this.world = world;
        this.rand = new Random(world.getProperties().getSeed());
        this.noise = new NoiseGenerator(this.rand, 4, 0.1);
    }

    @Override
    public void populate(World world, MutableBlockVolume buffer, ImmutableBiomeVolume biomes) {
        int x0 = buffer.getBlockMin().getX();
        int z0 = buffer.getBlockMin().getZ();
        int cx = x0 >> 4;
        int cz = z0 >> 4;
        long seed = (((cx & 0xFFFFFFFFL) << 32) | (cz & 0xFFFFFFFFL)) ^ world.getProperties().getSeed();
        this.rand.setSeed(seed);

        this.noise.getNoise(this.noisemap, x0, z0, 16, 16);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                double noise = this.noisemap[x + z * 16];
                int height = SimplexNoise.fastfloor(5 + noise * 20);
                for (int y = 255; y >= 0; y--) {
                    if (y < this.rand.nextInt(5)) {
                        buffer.setBlock(x0 + x, y, z0 + z, BlockTypes.BEDROCK.getDefaultState(), null);
                    } else if (y < height) {
                        buffer.setBlock(x0 + x, y, z0 + z, BlockTypes.STONE.getDefaultState(), null);
                    } else if (y < 10) {
                        buffer.setBlock(x0 + x, y, z0 + z, BlockTypes.WATER.getDefaultState(), null);
                    }
                }
            }
        }
    }

}
