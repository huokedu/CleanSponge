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
package org.spongepowered.clean.world.gen.noise;

import java.util.Random;

public class NoiseGenerator {

    private SimplexNoise noise;
    private int octaves;
    private double persistence;
    private double frequency;
    
    public NoiseGenerator(Random rand, int octaves, double persistence, double frequency) {
        this.noise = new SimplexNoise(rand);
        this.octaves = octaves;
        this.persistence = persistence;
        this.frequency = frequency;
    }

    public double getNoise(int x, int z) {
        double total = 0;
        double freq = this.frequency;
        double amplitude = 1;
        double max = 0;
        for (int i = 0; i < this.octaves; i++) {
            total += this.noise.noise(x * freq, z * freq) * amplitude;
            max += amplitude;
            amplitude *= this.persistence;
            freq *= 2;
        }
        return total / max;
    }

    public double getNoise(int x, int y, int z) {
        double total = 0;
        double freq = this.frequency;
        double amplitude = 1;
        double max = 0;
        for (int i = 0; i < this.octaves; i++) {
            total += this.noise.noise(x * freq, y * freq, z * freq) * amplitude;
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
