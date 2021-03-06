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
package org.spongepowered.clean.network.packet.login;

import io.netty.buffer.ByteBuf;

import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.util.ByteBufUtil;

import java.security.PublicKey;

public class EncryptionRequestPacket extends Packet {

    public String server_id;
    public byte[] public_key;
    public byte[] verify_token;

    public EncryptionRequestPacket(String server, PublicKey public_key, byte[] verify_token) {
        this(server, public_key.getEncoded(), verify_token);
    }

    public EncryptionRequestPacket(String server, byte[] public_key, byte[] verify_token) {
        this.id = 0x01;
        this.server_id = server;
        this.public_key = public_key;
        this.verify_token = verify_token;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        ByteBufUtil.writeString(buffer, this.server_id);
        ByteBufUtil.writeVarInt(buffer, this.public_key.length);
        buffer.writeBytes(this.public_key);
        ByteBufUtil.writeVarInt(buffer, this.verify_token.length);
        buffer.writeBytes(this.verify_token);
    }

}
