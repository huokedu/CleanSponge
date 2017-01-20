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
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.clean.entity.SEntityType;
import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.util.ByteBufUtil;

import java.util.UUID;

public class SpawnMobPacket extends Packet {

    public int entityid;
    public UUID uid;
    public EntityType type;
    public double x, y, z;
    public float pitch, yaw, head_pitch;
    public double vx, vy, vz;
    // TODO metadata

    public SpawnMobPacket(int id, UUID uid, EntityType type, double x, double y, double z, float pitch, float yaw, float head_pitch, double vx,
            double vy, double vz) {
        this.id = 0x03;
        this.entityid = id;
        this.uid = uid;
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.head_pitch = head_pitch;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        ByteBufUtil.writeVarInt(buffer, this.entityid);
        buffer.writeLong(this.uid.getMostSignificantBits());
        buffer.writeLong(this.uid.getLeastSignificantBits());
        buffer.writeByte(((SEntityType) this.type).getEntityId());
        buffer.writeDouble(this.x);
        buffer.writeDouble(this.y);
        buffer.writeDouble(this.z);
        buffer.writeByte((byte) Math.floor((this.pitch / 2 * Math.PI) * 256));
        buffer.writeByte((byte) Math.floor((this.yaw / 2 * Math.PI) * 256));
        buffer.writeByte((byte) Math.floor((this.head_pitch / 2 * Math.PI) * 256));
        buffer.writeShort((short) Math.floor(this.vx * 8000));
        buffer.writeShort((short) Math.floor(this.vy * 8000));
        buffer.writeShort((short) Math.floor(this.vz * 8000));
        buffer.writeByte(0xFF);
    }

}
