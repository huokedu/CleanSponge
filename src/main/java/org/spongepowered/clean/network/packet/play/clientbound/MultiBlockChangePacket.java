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

import java.util.List;

import org.spongepowered.clean.block.SBlockState;
import org.spongepowered.clean.block.SBlockType;
import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.util.ByteBufUtil;

import com.google.common.collect.Lists;

import io.netty.buffer.ByteBuf;

public class MultiBlockChangePacket extends Packet {

    public int chunkx, chunkz;
    public final List<Record> records = Lists.newArrayList();

    public MultiBlockChangePacket(int cx, int cz, Record... records) {
        this.id = 0x10;
        this.chunkx = cx;
        this.chunkz = cz;
        for (Record r : records) {
            this.records.add(r);
        }
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        buffer.writeInt(this.chunkx);
        buffer.writeInt(this.chunkz);
        ByteBufUtil.writeVarInt(buffer, this.records.size());
        for (Record r : this.records) {
            int xz = ((r.x - this.chunkx) << 4) | (r.z - this.chunkz);
            buffer.writeByte(xz);
            buffer.writeByte(r.y);
            int id = ((SBlockType) r.block.getType()).getBlockId();
            id = (id << 4) | r.block.getMetaId();
            ByteBufUtil.writeVarInt(buffer, id);
        }
    }

    public static class Record {

        public int x, y, z;
        public SBlockState block;

        public Record(int x, int y, int z, SBlockState block) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.block = block;
        }

    }

}
