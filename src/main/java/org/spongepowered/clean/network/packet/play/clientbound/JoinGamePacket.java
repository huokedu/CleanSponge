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
import org.spongepowered.clean.network.netty.ByteBufUtil;
import org.spongepowered.clean.network.packet.Packet;

public class JoinGamePacket extends Packet {

    public int     entity_id;
    public byte    gamemode;
    public int     dimension;
    public byte    difficulty;
    public byte    max_players;
    public String  level_type;
    public boolean reduced_debug;

    public JoinGamePacket(int id, byte gamemode, int dimension, byte difficulty, byte max_players, String level_type, boolean reduced_debug) {
        this.id = 0x23;
        this.entity_id = id;
        this.gamemode = gamemode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.max_players = max_players;
        this.level_type = level_type;
        this.reduced_debug = reduced_debug;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        buffer.writeInt(this.entity_id);
        buffer.writeByte(this.gamemode);
        buffer.writeInt(this.dimension);
        buffer.writeByte(this.difficulty);
        buffer.writeByte(this.max_players);
        ByteBufUtil.writeString(buffer, this.level_type);
        buffer.writeBoolean(this.reduced_debug);
    }

}
