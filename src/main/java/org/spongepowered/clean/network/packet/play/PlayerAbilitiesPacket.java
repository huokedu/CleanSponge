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
package org.spongepowered.clean.network.packet.play;

import io.netty.buffer.ByteBuf;
import org.spongepowered.clean.network.packet.Packet;

public class PlayerAbilitiesPacket extends Packet {

    public static final int INVULNERABLE_MASK  = 0x01;
    public static final int FLYING_MASK        = 0x02;
    public static final int ALLOW_FLYING_MASK  = 0x04;
    public static final int CREATIVE_MODE_MASK = 0x08;

    public byte             flags;
    public float            fly_speed;
    public float            fov_modifier;

    public PlayerAbilitiesPacket(byte flags, float fly_speed, float fov_mod) {
        this.id = 0x2B;
        this.flags = flags;
        this.fly_speed = fly_speed;
        this.fov_modifier = fov_mod;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        buffer.writeByte(this.flags);
        buffer.writeFloat(this.fly_speed);
        buffer.writeFloat(this.fov_modifier);
    }

}
