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
package org.spongepowered.clean.network.packet.play.serverbound;

import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.util.ByteBufUtil;

import com.flowpowered.math.vector.Vector3i;

import io.netty.buffer.ByteBuf;

public class PlayerDiggingPacket extends Packet {

    public static enum Status {
        START_DIGGING,
        CANCEL_DIGGING,
        FINISH_DIGGING,
        DROP_ITEM_STACK,
        DROP_ITEM,
        SHOOT_ARROW,
        SWAP_ITEM_IN_HAND,
    }

    public static enum Face {
        NEG_Y,
        POS_Y,
        NEG_Z,
        POS_Z,
        NEG_X,
        POS_X,
        SPECIAL,
    }

    public Status status;
    public Vector3i position;
    public Face face;

    public PlayerDiggingPacket() {
        this.id = 0x13;
    }

    @Override
    public void read(ByteBuf buffer) {
        this.status = Status.values()[ByteBufUtil.readVarInt(buffer)];
        this.position = ByteBufUtil.readPosition(buffer);
        int next = buffer.readByte() & 0xFF;
        if (next == 255) {
            this.face = Face.SPECIAL;
        } else {
            this.face = Face.values()[next];
        }
    }

    @Override
    public void write(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

}
