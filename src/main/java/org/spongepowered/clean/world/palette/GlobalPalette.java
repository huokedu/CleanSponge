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
package org.spongepowered.clean.world.palette;

import java.util.Collection;
import java.util.Optional;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.world.schematic.BlockPalette;
import org.spongepowered.api.world.schematic.BlockPaletteType;
import org.spongepowered.api.world.schematic.BlockPaletteTypes;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

public class GlobalPalette implements BlockPalette {

    public static final GlobalPalette instance = new GlobalPalette();

    private final Int2ObjectOpenHashMap<BlockState> idsToState = new Int2ObjectOpenHashMap<>();
    private final Object2IntOpenHashMap<BlockState> stateToId = new Object2IntOpenHashMap<>();
    private int highest = 0;

    public GlobalPalette() {
        this.stateToId.defaultReturnValue(0);
    }

    public void set(int id, BlockState state) {
        this.idsToState.put(id, state);
        this.stateToId.put(state, id);
    }

    @Override
    public BlockPaletteType getType() {
        return BlockPaletteTypes.GLOBAL;
    }

    @Override
    public int getHighestId() {
        return this.highest;
    }

    @Override
    public Optional<Integer> get(BlockState state) {
        return Optional.ofNullable(this.stateToId.getInt(state));
    }

    @Override
    public int getOrAssign(BlockState state) {
        return this.stateToId.getInt(state);
    }

    @Override
    public Optional<BlockState> get(int id) {
        return Optional.ofNullable(this.idsToState.get(id));
    }

    @Override
    public boolean remove(BlockState state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<BlockState> getEntries() {
        return this.stateToId.keySet();
    }

}
