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

import java.util.UUID;

public class LoginSuccessPacket extends Packet {

    public String uuid;
    public String username;

    public LoginSuccessPacket(UUID uuid, String username) {
        this(uuid.toString(), username);
    }

    public LoginSuccessPacket(String uuid, String username) {
        this.id = 0x02;
        this.uuid = uuid;
        this.username = username;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        ByteBufUtil.writeString(buffer, this.uuid);
        ByteBufUtil.writeString(buffer, this.username);
    }

}
