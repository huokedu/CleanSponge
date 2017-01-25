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

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

public class FlatGenerator implements GenerationPopulator {

    public FlatGenerator() {

    }

    private void fillLayer(MutableBlockVolume buffer, int y, BlockState block) {
        int minx = buffer.getBlockMin().getX();
        int maxx = buffer.getBlockMax().getX();
        int minz = buffer.getBlockMin().getZ();
        int maxz = buffer.getBlockMax().getZ();
        for (int x = minx; x <= maxx; x++) {
            for (int z = minz; z <= maxz; z++) {
                buffer.setBlock(x, y, z, block, null);
            }
        }
    }

    @Override
    public void populate(World world, MutableBlockVolume buffer, ImmutableBiomeVolume biomes) {
        int minx = buffer.getBlockMin().getX();
        int maxx = buffer.getBlockMax().getX();
        int minz = buffer.getBlockMin().getZ();
        int maxz = buffer.getBlockMax().getZ();
        fillLayer(buffer, 0, BlockTypes.BEDROCK.getDefaultState());
        fillLayer(buffer, 1, BlockTypes.DIRT.getDefaultState());
        fillLayer(buffer, 2, BlockTypes.DIRT.getDefaultState());
        fillLayer(buffer, 3, BlockTypes.DIRT.getDefaultState());
        fillLayer(buffer, 4, BlockTypes.GRASS.getDefaultState());

    }

}
