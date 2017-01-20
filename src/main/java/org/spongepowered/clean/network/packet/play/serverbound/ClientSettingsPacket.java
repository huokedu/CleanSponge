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

import io.netty.buffer.ByteBuf;
import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.util.ByteBufUtil;

public class ClientSettingsPacket extends Packet {

    public static enum ChatMode {
        ENABLED,
        COMMANDS_ONLY,
        HIDDEN,
    }

    public static enum MainHand {
        LEFT,
        RIGHT,
    }

    // TODO translate to pojo
    public String locale;
    public byte viewDistance;
    public ChatMode chatmode;
    public boolean chatcolors;
    public byte skinparts;
    public MainHand mainhand;

    public ClientSettingsPacket() {
        this.id = 0x04;
    }

    @Override
    public void read(ByteBuf buffer) {
        this.locale = ByteBufUtil.readString(buffer);
        this.viewDistance = buffer.readByte();
        this.chatmode = ChatMode.values()[ByteBufUtil.readVarInt(buffer)];
        this.chatcolors = buffer.readBoolean();
        this.skinparts = buffer.readByte();
        this.mainhand = MainHand.values()[ByteBufUtil.readVarInt(buffer)];
    }

    @Override
    public void write(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

}
