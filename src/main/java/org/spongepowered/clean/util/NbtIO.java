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

import static org.spongepowered.api.data.DataQuery.of;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.DataView.SafetyMode;
import org.spongepowered.api.data.MemoryDataContainer;

import com.google.common.collect.Lists;

public class NbtIO {

    public static DataContainer read(File file) throws IOException {
        try (DataInputStream input = new DataInputStream(new GZIPInputStream(new FileInputStream(file)))) {
            byte type = input.readByte();
            if (type != 10) {
                throw new IllegalStateException("Root tag is not compound tag");
            }
            input.readUTF(); // drop the first name?
            return read(input);
        }
    }

    public static DataContainer read(DataInputStream input) throws IOException {
        DataContainer current = new MemoryDataContainer(SafetyMode.NO_DATA_CLONED);
        while (true) {
            byte type = input.readByte();
            if (type == 0) {
                break;
            }
            String name = input.readUTF();
            if (type == 1) {
                current.set(of(name), input.readByte());
            } else if (type == 2) {
                current.set(of(name), input.readShort());
            } else if (type == 3) {
                current.set(of(name), input.readInt());
            } else if (type == 4) {
                current.set(of(name), input.readLong());
            } else if (type == 5) {
                current.set(of(name), input.readFloat());
            } else if (type == 6) {
                current.set(of(name), input.readDouble());
            } else if (type == 7) {
                int length = input.readInt();
                byte[] data = new byte[length];
                input.read(data, 0, length);
                current.set(of(name), data);
            } else if (type == 8) {
                current.set(of(name), input.readUTF());
            } else if (type == 9) {
                current.set(of(name), readList(input));
            } else if (type == 10) {
                current.set(of(name), read(input));
            } else if (type == 11) {
                int length = input.readInt();
                int[] data = new int[length];
                for (int i = 0; i < length; i++) {
                    data[i] = input.readInt();
                }
                current.set(of(name), data);
            }
        }
        return current;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static List<?> readList(DataInputStream input) throws IOException {
        byte componentType = input.readByte();
        int length = input.readInt();
        List listData = Lists.newArrayList();
        for (int i = 0; i < length; i++) {
            if (componentType == 1) {
                listData.add(input.readByte());
            } else if (componentType == 2) {
                listData.add(input.readShort());
            } else if (componentType == 3) {
                listData.add(input.readInt());
            } else if (componentType == 4) {
                listData.add(input.readLong());
            } else if (componentType == 5) {
                listData.add(input.readFloat());
            } else if (componentType == 6) {
                listData.add(input.readDouble());
            } else if (componentType == 7) {
                int arraylength = input.readInt();
                byte[] data = new byte[arraylength];
                input.read(data, 0, arraylength);
                listData.add(data);
            } else if (componentType == 8) {
                listData.add(input.readUTF());
            } else if (componentType == 9) {
                listData.add(readList(input));
            } else if (componentType == 10) {
                listData.add(read(input));
            } else if (componentType == 11) {
                int arraylength = input.readInt();
                int[] data = new int[arraylength];
                for (int o = 0; o < length; o++) {
                    data[o] = input.readInt();
                }
                listData.add(data);
            }
        }
        return listData;
    }

    public static void write(DataView data, File file) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file))) {
            out.writeShort(10);
            out.writeUTF("_");
            write(data, out);
        }
    }

    public static void write(DataView data, DataOutputStream out) throws IOException {
        for (DataQuery q : data.getKeys(false)) {
            Object o = data.get(q).get();
            if (o instanceof Boolean) {
                out.writeByte(1);
                out.writeUTF(q.asString("."));
                out.writeByte(((Boolean) o).booleanValue() ? 1 : 0);
            } else if (o instanceof Byte) {
                out.writeByte(1);
                out.writeUTF(q.asString("."));
                out.writeByte(((Byte) o).byteValue());
            } else if (o instanceof Short) {
                out.writeByte(2);
                out.writeUTF(q.asString("."));
                out.writeShort(((Short) o).shortValue());
            } else if (o instanceof Integer) {
                out.writeByte(3);
                out.writeUTF(q.asString("."));
                out.writeInt(((Integer) o).intValue());
            } else if (o instanceof Long) {
                out.writeByte(4);
                out.writeUTF(q.asString("."));
                out.writeLong(((Long) o).longValue());
            } else if (o instanceof Float) {
                out.writeByte(5);
                out.writeUTF(q.asString("."));
                out.writeFloat(((Float) o).floatValue());
            } else if (o instanceof Double) {
                out.writeByte(6);
                out.writeUTF(q.asString("."));
                out.writeDouble(((Double) o).doubleValue());
            } else if (o instanceof byte[]) {
                out.writeByte(7);
                out.writeUTF(q.asString("."));
                byte[] d = (byte[]) o;
                out.writeShort(d.length);
                out.write(d, 0, d.length);
            } else if (o instanceof String) {
                out.writeByte(8);
                out.writeUTF(q.asString("."));
                out.writeUTF(((String) o));
            } else if (o instanceof List) {
                out.writeByte(9);
                out.writeUTF(q.asString("."));
                writeList((List<?>) o, out);
            } else if (o instanceof DataView) {
                out.writeByte(10);
                out.writeUTF(q.asString("."));
                write((DataView) o, out);
            } else if (o instanceof int[]) {
                out.writeByte(11);
                out.writeUTF(q.asString("."));
                int[] d = (int[]) o;
                out.writeShort(d.length);
                for (int i = 0; i < d.length; i++) {
                    out.writeInt(d[i]);
                }
            } else if (o instanceof DataSerializable) {
                DataContainer ser = ((DataSerializable) o).toContainer();
                out.writeByte(10);
                out.writeUTF(q.asString("."));
                write(ser, out);
            } else {
                throw new IllegalArgumentException("Unsupported type in data container: " + o.getClass().getName());
            }
        }
        out.writeByte(0);
    }

    private static void writeList(List<?> list, DataOutputStream out) throws IOException {
        if (list.size() == 0) {
            out.writeByte(0);
            out.writeInt(0);
            return;
        }
        Object o = list.get(0);
        byte type = 0;
        if (o instanceof Boolean || o instanceof Byte) {
            type = 1;
        } else if (o instanceof Short) {
            type = 2;
        } else if (o instanceof Integer) {
            type = 3;
        } else if (o instanceof Long) {
            type = 4;
        } else if (o instanceof Float) {
            type = 5;
        } else if (o instanceof Double) {
            type = 6;
        } else if (o instanceof byte[]) {
            type = 7;
        } else if (o instanceof String) {
            type = 8;
        } else if (o instanceof List) {
            type = 9;
        } else if (o instanceof DataView || o instanceof DataSerializable) {
            type = 10;
        } else if (o instanceof int[]) {
            type = 11;
        } else {
            throw new IllegalArgumentException("Unsupported type in data container: " + o.getClass().getName());
        }
        out.writeByte(type);
        out.writeInt(list.size());
        for (int i = 0; i < list.size(); i++) {
            if (o instanceof Boolean) {
                out.writeByte(((Boolean) o).booleanValue() ? 1 : 0);
            } else if (o instanceof Byte) {
                out.writeByte(((Byte) o).byteValue());
            } else if (o instanceof Short) {
                out.writeShort(((Short) o).shortValue());
            } else if (o instanceof Integer) {
                out.writeInt(((Integer) o).intValue());
            } else if (o instanceof Long) {
                out.writeLong(((Long) o).longValue());
            } else if (o instanceof Float) {
                out.writeFloat(((Float) o).floatValue());
            } else if (o instanceof Double) {
                out.writeDouble(((Double) o).doubleValue());
            } else if (o instanceof byte[]) {
                byte[] d = (byte[]) o;
                out.writeShort(d.length);
                out.write(d, 0, d.length);
            } else if (o instanceof String) {
                out.writeUTF(((String) o));
            } else if (o instanceof List) {
                writeList((List<?>) o, out);
            } else if (o instanceof DataView) {
                write((DataView) o, out);
            } else if (o instanceof int[]) {
                int[] d = (int[]) o;
                out.writeShort(d.length);
                for (int j = 0; j < d.length; j++) {
                    out.writeInt(d[j]);
                }
            } else if (o instanceof DataSerializable) {
                DataContainer ser = ((DataSerializable) o).toContainer();
                write(ser, out);
            }
        }
    }

}
