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
package org.spongepowered.clean.network.packet.play.clientbound;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.util.ByteBufUtil;
import org.spongepowered.clean.world.SChunk;
import org.spongepowered.clean.world.SChunk.ChunkSection;
import org.spongepowered.clean.world.palette.LocalBlockPalette;

public class ChunkDataPacket extends Packet {

    public SChunk chunk;
    public boolean full;
    public int mask;

    public ChunkDataPacket(SChunk chunk, boolean full, int mask) {
        this.id = 0x20;
        this.chunk = chunk;
        this.full = full;
        this.mask = mask;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        buffer.writeInt(this.chunk.getBlockMin().getX() >> 4);
        buffer.writeInt(this.chunk.getBlockMin().getZ() >> 4);
        buffer.writeBoolean(this.full);
        if (this.full) {
            this.mask = 0;
            for (int i = 0; i < 16; i++) {
                if (this.chunk.getSections()[i] != null) {
                    this.mask &= (1 << i);
                }
            }
        } else {
            for (int i = 0; i < 16; i++) {
                if (this.chunk.getSections()[i] == null) {
                    this.mask &= ~(1 << i);
                }
            }
        }
        ByteBufUtil.writeVarInt(buffer, this.mask);
        ByteBuf chunkdata = Unpooled.buffer();
        for (int i = 0; i < 16; i++) {
            if (((this.mask >> i) & 1) != 0) {
                LocalBlockPalette palette = new LocalBlockPalette();
                ChunkSection section = this.chunk.getSections()[i];
                for (int x = 0; x < 16; x++) {
                    for (int y = 0; y < 16; y++) {
                        for (int z = 0; z < 16; z++) {
                            palette.getOrAssign(section.getBlock(x, y, z));
                        }
                    }
                }
                int bitsPerBlock = Integer.highestOneBit(palette.getHighestId());
                if (bitsPerBlock < 4) {
                    bitsPerBlock = 4;
                } else if (bitsPerBlock > 8) {
                    bitsPerBlock = -1;
                }
            }
        }
    }

}
