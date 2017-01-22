/*
 * Copyright (c) 2015-2016 VoxelBox <http://engine.thevoxelbox.com>.
 * All Rights Reserved.
 */
package org.spongepowered.clean.world.biome;

import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.extent.MutableBiomeVolume;
import org.spongepowered.api.world.gen.BiomeGenerator;

public class SingleBiomeGenerator implements BiomeGenerator {

    private final BiomeType biome;

    public SingleBiomeGenerator(BiomeType type) {
        this.biome = type;
    }

    @Override
    public void generateBiomes(MutableBiomeVolume buffer) {
        for (int x = buffer.getBiomeMin().getX(); x <= buffer.getBiomeMax().getX(); x++) {
            for (int z = buffer.getBiomeMin().getZ(); z <= buffer.getBiomeMax().getZ(); z++) {
                buffer.setBiome(x, 0, z, this.biome);
            }
        }
    }

}
