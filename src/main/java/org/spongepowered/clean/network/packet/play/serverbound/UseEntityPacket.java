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
import com.google.common.base.MoreObjects;
import io.netty.buffer.ByteBuf;

public class UseEntityPacket extends Packet {

    public static enum Type {
        ATTACK,
        INTERACT,
        INTERACT_AT;
    }

    // TODO hand pojo
    public int target;
    public Type type;
    public float x, y, z;
    public int hand;

    public UseEntityPacket() {
        this.id = 0x0A;
    }

    @Override
    public void read(ByteBuf buffer) {
        this.target = ByteBufUtil.readVarInt(buffer);
        this.type = Type.values()[ByteBufUtil.readVarInt(buffer)];
        if (this.type == Type.INTERACT_AT) {
            this.x = buffer.readFloat();
            this.y = buffer.readFloat();
            this.z = buffer.readFloat();
        }
        if (this.type != Type.ATTACK) {
            this.hand = ByteBufUtil.readVarInt(buffer);
        }
    }

    @Override
    public void write(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", Integer.toHexString(this.id))
                .add("target", this.target)
                .add("type", this.type.name())
                .add("x", this.x)
                .add("y", this.y)
                .add("z", this.z)
                .add("hand", this.hand)
                .toString();
    }

}
