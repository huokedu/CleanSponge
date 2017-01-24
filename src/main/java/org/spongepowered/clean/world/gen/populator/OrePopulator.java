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
package org.spongepowered.clean.world.gen.populator;

import java.util.Random;
import java.util.function.Predicate;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.util.weighted.VariableAmount;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.world.gen.PopulatorType;
import org.spongepowered.api.world.gen.PopulatorTypes;
import org.spongepowered.api.world.gen.populator.Ore;

public class OrePopulator implements Ore {

    private BlockState ore;
    private VariableAmount size;
    private VariableAmount count;
    private VariableAmount height;
    private Predicate<BlockState> placement;

    public OrePopulator(OreBuilder builder) {
        this.ore = builder.ore;
        this.count = builder.count;
        this.size = builder.size;
        this.height = builder.height;
        this.placement = builder.placement;
    }

    @Override
    public PopulatorType getType() {
        return PopulatorTypes.ORE;
    }

    @Override
    public void populate(World world, Extent volume, Random random) {
        int count = this.count.getFlooredAmount(random);
        for (int i = 0; i < count; i++) {
            int x = volume.getBlockMin().getX() + random.nextInt(volume.getBlockSize().getX());
            int y = this.height.getFlooredAmount(random) + volume.getBlockMin().getY();
            int z = volume.getBlockMin().getZ() + random.nextInt(volume.getBlockSize().getZ());
            if (y > volume.getBlockMax().getY()) {
                y = volume.getBlockMax().getY();
            }
            int size = this.size.getFlooredAmount(random);
            BlockState cur = volume.getBlock(x, y, z);
            if (this.placement.test(cur)) {
                volume.setBlock(x, y, z, this.ore, null);
            }
//            for(int j = 0; j < size; j++) {
//                // TODO figure out indexing
//            }
        }
    }

    @Override
    public BlockState getOreBlock() {
        return this.ore;
    }

    @Override
    public void setOreBlock(BlockState block) {
        this.ore = block;
    }

    @Override
    public VariableAmount getDepositSize() {
        return this.size;
    }

    @Override
    public void setDepositSize(VariableAmount size) {
        this.size = size;
    }

    @Override
    public VariableAmount getDepositsPerChunk() {
        return this.count;
    }

    @Override
    public void setDepositsPerChunk(VariableAmount count) {
        this.count = count;
    }

    @Override
    public VariableAmount getHeight() {
        return this.height;
    }

    @Override
    public void setHeight(VariableAmount height) {
        this.height = height;
    }

    @Override
    public Predicate<BlockState> getPlacementCondition() {
        return this.placement;
    }

    @Override
    public void setPlacementCondition(Predicate<BlockState> condition) {
        this.placement = condition;
    }

    public static class OreBuilder implements Ore.Builder {

        BlockState ore;
        VariableAmount size;
        VariableAmount count;
        VariableAmount height;
        Predicate<BlockState> placement;

        @Override
        public Builder ore(BlockState block) {
            this.ore = block;
            return this;
        }

        @Override
        public Builder size(VariableAmount size) {
            this.size = size;
            return this;
        }

        @Override
        public Builder perChunk(VariableAmount count) {
            this.count = count;
            return this;
        }

        @Override
        public Builder height(VariableAmount height) {
            this.height = height;
            return this;
        }

        @Override
        public Builder placementCondition(Predicate<BlockState> condition) {
            this.placement = condition;
            return this;
        }

        @Override
        public Builder from(Ore value) {
            this.count = value.getDepositsPerChunk();
            this.size = value.getDepositSize();
            this.height = value.getHeight();
            this.placement = value.getPlacementCondition();
            this.ore = value.getOreBlock();
            return this;
        }

        @Override
        public Builder reset() {
            this.count = VariableAmount.fixed(16);
            this.size = VariableAmount.baseWithRandomAddition(2, 6);
            this.placement = (s) -> s.getType() == BlockTypes.AIR;
            this.height = VariableAmount.baseWithRandomAddition(0, 64);
            this.ore = null;
            return this;
        }

        @Override
        public Ore build() throws IllegalStateException {
            return new OrePopulator(this);
        }

    }

}
