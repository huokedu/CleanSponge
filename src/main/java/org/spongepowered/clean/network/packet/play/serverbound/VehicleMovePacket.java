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

import com.google.common.base.MoreObjects;
import io.netty.buffer.ByteBuf;
import org.spongepowered.clean.network.packet.Packet;

public class VehicleMovePacket extends Packet {

    public double x, y, z;
    public float yaw, pitch;

    public VehicleMovePacket() {
        this.id = 0x10;
    }

    @Override
    public void read(ByteBuf buffer) {
        this.x = buffer.readDouble();
        this.y = buffer.readDouble();
        this.z = buffer.readDouble();
        this.yaw = buffer.readFloat();
        this.pitch = buffer.readFloat();
    }

    @Override
    public void write(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", Integer.toHexString(this.id))
                .add("x", this.x)
                .add("y", this.y)
                .add("z", this.z)
                .add("yaw", this.yaw)
                .add("pitch", this.pitch)
                .toString();
    }

}
