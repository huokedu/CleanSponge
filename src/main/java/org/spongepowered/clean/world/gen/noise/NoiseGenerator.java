/*
 * Copyright (c) 2015-2016 VoxelBox <http://engine.thevoxelbox.com>.
 * All Rights Reserved.
 */
package org.spongepowered.clean.world.gen.noise;

import java.util.Random;

public class NoiseGenerator {

    private SimplexNoise noise;
    private int octaves;
    private double persistence;

    public NoiseGenerator(Random rand, int octaves, double persistence) {
        this.noise = new SimplexNoise(rand);
        this.octaves = octaves;
        this.persistence = persistence;
    }

    public double getNoise(int x, int z) {
        double total = 0;
        double freq = 0.01;
        double amplitude = 1;
        double max = 0;
        for (int i = 0; i < this.octaves; i++) {
            total += (this.noise.noise(x * freq, z * freq) + 1) * amplitude * 0.5;
            max += amplitude;
            amplitude *= this.persistence;
            freq *= 2;
        }
        return total / max;
    }

    public void getNoise(double[] result, int x0, int z0, int xSize, int zSize) {
        for (int x = 0; x < xSize; x++) {
            for (int z = 0; z < zSize; z++) {
                result[x + z * 16] = getNoise(x0 + x, z0 + z);
            }
        }
    }

}
