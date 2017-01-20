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

import org.spongepowered.clean.block.SBlockState;
import org.spongepowered.clean.block.SBlockType;
import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.util.ByteBufUtil;

import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;

public class BlockChangePacket extends Packet {

    public Vector3i position;
    public SBlockState newBlock;

    public BlockChangePacket(Vector3i pos, SBlockState block) {
        this.id = 0x07;
        this.position = pos;
        this.newBlock = block;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        ByteBufUtil.writePosition(buffer, this.position);
        int id = ((SBlockType) this.newBlock.getType()).getBlockId();
        id = (id << 4) | this.newBlock.getMetaId();
        ByteBufUtil.writeVarInt(buffer, id);
    }

}