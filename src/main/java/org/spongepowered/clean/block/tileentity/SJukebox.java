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
package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.Jukebox;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.clean.world.SWorld;

public class SJukebox extends AbstractTileEntity implements Jukebox {

    public SJukebox(SWorld world, int x, int y, int z) {
        super(world, x, y, z);
        // TODO Auto-generated constructor stub
    }

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.JUKEBOX;
    }

    @Override
    public void playRecord() {
        // TODO Auto-generated method stub

    }

    @Override
    public void ejectRecord() {
        // TODO Auto-generated method stub

    }

    @Override
    public void insertRecord(ItemStack record) {
        // TODO Auto-generated method stub

    }

}
