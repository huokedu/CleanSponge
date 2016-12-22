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
package org.spongepowered.clean.block;

import org.spongepowered.clean.block.palette.GlobalBlockPalette;
import org.spongepowered.clean.block.state.BlockEnumProperty;
import org.spongepowered.clean.block.state.BlockEnumPropertyType;
import org.spongepowered.clean.block.state.BlockProperty;
import org.spongepowered.clean.block.state.SpongeBlockState;

public class BlockStone extends SpongeBlockType {

    public static enum StoneType {
        STONE,
        GRANITE,
        GRANITE_SMOOTH,
        DIORITE,
        DIORITE_SMOOTH,
        ANDESITE,
        ANDESITE_SMOOTH
    }

    public static final BlockEnumPropertyType<StoneType> TYPE = new BlockEnumPropertyType<>();

    public BlockStone() {
        super("minecraft:stone", "Stone");
    }

    @Override
    public void registerStates() {
        GlobalBlockPalette.instance.register(1, 0,
                this.defaultState = new SpongeBlockState(this, new BlockProperty<?>[] { new BlockEnumProperty<StoneType>(TYPE, StoneType.STONE) }));
        GlobalBlockPalette.instance.register(1, 1,
                new SpongeBlockState(this, new BlockProperty<?>[] { new BlockEnumProperty<StoneType>(TYPE, StoneType.GRANITE) }));
        GlobalBlockPalette.instance.register(1, 2,
                new SpongeBlockState(this, new BlockProperty<?>[] { new BlockEnumProperty<StoneType>(TYPE, StoneType.GRANITE_SMOOTH) }));
        GlobalBlockPalette.instance.register(1, 3,
                new SpongeBlockState(this, new BlockProperty<?>[] { new BlockEnumProperty<StoneType>(TYPE, StoneType.DIORITE) }));
        GlobalBlockPalette.instance.register(1, 4,
                new SpongeBlockState(this, new BlockProperty<?>[] { new BlockEnumProperty<StoneType>(TYPE, StoneType.DIORITE_SMOOTH) }));
        GlobalBlockPalette.instance.register(1, 5,
                new SpongeBlockState(this, new BlockProperty<?>[] { new BlockEnumProperty<StoneType>(TYPE, StoneType.ANDESITE) }));
        GlobalBlockPalette.instance.register(1, 6,
                new SpongeBlockState(this, new BlockProperty<?>[] { new BlockEnumProperty<StoneType>(TYPE, StoneType.ANDESITE_SMOOTH) }));
    }

}
