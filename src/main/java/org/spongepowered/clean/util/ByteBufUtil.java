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
package org.spongepowered.clean.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.util.Direction;
import org.spongepowered.clean.SGame;

import com.flowpowered.math.vector.Vector3i;
import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

public class ByteBufUtil {

    public static int readVarInt(ByteBuf buf) {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = buf.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    public static void writeVarInt(ByteBuf buf, int value) {
        while ((value & -128) != 0) {
            buf.writeByte(value & 127 | 128);
            value >>>= 7;
        }
        buf.writeByte(value);
    }

    public static long readVarLong(ByteBuf buf) {
        int numRead = 0;
        long result = 0;
        byte read;
        do {
            read = buf.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 10) {
                throw new RuntimeException("VarLong is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    public static void writeVarLong(ByteBuf buf, long value) {
        while ((value & -128L) != 0L) {
            buf.writeByte((int) (value & 127L) | 128);
            value >>>= 7;
        }
        buf.writeByte((int) value);
    }

    public static String readString(ByteBuf buf) {
        int length = readVarInt(buf);
        byte[] data = new byte[length];
        buf.readBytes(data, 0, length);
        return new String(data, Charsets.UTF_8);
    }

    public static void writeString(ByteBuf buf, String str) {
        byte[] data = str.getBytes(Charsets.UTF_8);
        writeVarInt(buf, data.length);
        buf.writeBytes(data);
    }

    public static Vector3i readPosition(ByteBuf buffer) {
        long val = buffer.readLong();
        int x = (int) (val >> 38);
        int y = (int) ((val >> 26) & 0xFFF);
        int z = (int) (val & 0x3FFFFFF);
        return new Vector3i(x, y, z);
    }

    public static void writePosition(ByteBuf buffer, Vector3i location) {
        int x = location.getX();
        int y = location.getY();
        int z = location.getZ();
        buffer.writeLong(((x & 0x3FFFFFF) << 38) | ((y & 0xFFF) << 26) | (z & 0x3FFFFFF));
    }

    public static void writePosition(ByteBuf buffer, int x, int y, int z) {
        buffer.writeLong(((x & 0x3FFFFFF) << 38) | ((y & 0xFFF) << 26) | (z & 0x3FFFFFF));
    }

    public static Direction readDirection(ByteBuf buffer) {
        byte dir = buffer.readByte();
        if (dir == 0) {
            return Direction.SOUTH;
        } else if (dir == 1) {
            return Direction.WEST;
        } else if (dir == 2) {
            return Direction.NORTH;
        } else if (dir == 3) {
            return Direction.EAST;
        }
        return Direction.NONE;
    }

    public static void writeDirection(ByteBuf buffer, Direction direction) {
        // TODO should be more robust and find the closest cardinal direction
        // would be nice to have an api method for that though
        switch (direction) {
            case NORTH:
                buffer.writeByte(2);
                break;
            case EAST:
                buffer.writeByte(3);
                break;
            case WEST:
                buffer.writeByte(1);
                break;
            case SOUTH:
            default:
                buffer.writeByte(0);
                break;
        }
    }

    public static DataContainer readNBT(ByteBuf buffer) {
        DataInputStream in = new DataInputStream(new ByteBufInputStream(buffer));
        try {
            return NbtIO.read(in);
        } catch (IOException e) {
            SGame.getLogger().error("Error reading nbt from network stream");
            e.printStackTrace();
        }
        return null;
    }

    public static void writeNBT(ByteBuf buffer, DataView data) {
        DataOutputStream out = new DataOutputStream(new ByteBufOutputStream(buffer));
        try {
            NbtIO.write(data, out);
        } catch (IOException e) {
            SGame.getLogger().error("Error writing nbt to network stream");
            e.printStackTrace();
        }
    }
}
