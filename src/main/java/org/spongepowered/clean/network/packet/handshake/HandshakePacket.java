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
package org.spongepowered.clean.network.packet.handshake;

import io.netty.buffer.ByteBuf;

import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.util.ByteBufUtil;

public class HandshakePacket extends Packet {

    public static enum NextState {
        STATUS,
        LOGIN
    }

    public int       protocol_version;
    public String    server_address;
    public int       server_port;
    public NextState next_state;

    public HandshakePacket() {
        this.id = 0x00;
    }

    @Override
    public void read(ByteBuf buffer) {
        this.protocol_version = ByteBufUtil.readVarInt(buffer);
        this.server_address = ByteBufUtil.readString(buffer);
        this.server_port = buffer.readUnsignedShort();
        int state = ByteBufUtil.readVarInt(buffer);
        if (state == 1) {
            this.next_state = NextState.STATUS;
        } else if (state == 2) {
            this.next_state = NextState.LOGIN;
        }
    }

    @Override
    public void write(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

}
