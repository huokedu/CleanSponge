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
package org.spongepowered.clean.block.palette;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.clean.block.state.SpongeBlockState;

public class GlobalBlockPalette implements BlockPalette {

    public static final GlobalBlockPalette instance  = new GlobalBlockPalette();

    private SpongeBlockState[]             idToState = new SpongeBlockState[65535];
    private int[]                          stateToId = new int[65535];

    private int                            next_id   = 0;

    private GlobalBlockPalette() {

    }

    public void register(int id, int data, SpongeBlockState spongeBlockState) {
        int index = (id << 4) | (data & 0xF);
        int internal_id = this.next_id++;
        spongeBlockState.setInternalId(internal_id);
        this.stateToId[internal_id] = index;
        this.idToState[index] = spongeBlockState;
    }

    @Override
    public int getId(BlockState state) {
        return this.stateToId[((SpongeBlockState) state).getInternalId()];
    }

    @Override
    public BlockState getState(int id) {
        return this.idToState[id];
    }

}
