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
package org.spongepowered.clean.nbt;

import com.google.common.collect.Lists;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class NbtIO {

    public static NbtCompound read(File file) throws IOException {
        try (DataInputStream input = new DataInputStream(new GZIPInputStream(new FileInputStream(file)))) {
            byte type = input.readByte();
            if (type != 10) {
                throw new IllegalStateException("Root tag is not compound tag");
            }
            String name = input.readUTF();
            return read(name, input);
        }
    }

    private static NbtList readList(String name, DataInputStream input) throws IOException {
        byte componentType = input.readByte();
        int length = input.readInt();
        List<NbtBase> listTags = Lists.newArrayList();
        for (int i = 0; i < length; i++) {
            if (componentType == 1) {
                listTags.add(new NbtByte("", input.readByte()));
            } else if (componentType == 2) {
                listTags.add(new NbtShort("", input.readShort()));
            } else if (componentType == 3) {
                listTags.add(new NbtInt("", input.readInt()));
            } else if (componentType == 4) {
                listTags.add(new NbtLong("", input.readLong()));
            } else if (componentType == 5) {
                listTags.add(new NbtFloat("", input.readFloat()));
            } else if (componentType == 6) {
                listTags.add(new NbtDouble("", input.readDouble()));
            } else if (componentType == 7) {
                int arraylength = input.readInt();
                byte[] data = new byte[arraylength];
                input.read(data, 0, arraylength);
                listTags.add(new NbtByteArray("", data));
            } else if (componentType == 8) {
                listTags.add(new NbtString("", input.readUTF()));
            } else if (componentType == 9) {
                listTags.add(readList("", input));
            } else if (componentType == 10) {
                listTags.add(read("", input));
            } else if (componentType == 11) {
                int arraylength = input.readInt();
                int[] data = new int[arraylength];
                for (int o = 0; o < length; o++) {
                    data[o] = input.readInt();
                }
                listTags.add(new NbtIntArray("", data));
            }
        }
        return new NbtList(name, listTags);
    }

    private static NbtCompound read(String tagname, DataInputStream input) throws IOException {
        NbtCompound current = new NbtCompound(tagname);
        while (true) {
            byte type = input.readByte();
            if (type == 0) {
                break;
            }
            String name = input.readUTF();
            if (type == 1) {
                current.add(name, new NbtByte(name, input.readByte()));
            } else if (type == 2) {
                current.add(name, new NbtShort(name, input.readShort()));
            } else if (type == 3) {
                current.add(name, new NbtInt(name, input.readInt()));
            } else if (type == 4) {
                current.add(name, new NbtLong(name, input.readLong()));
            } else if (type == 5) {
                current.add(name, new NbtFloat(name, input.readFloat()));
            } else if (type == 6) {
                current.add(name, new NbtDouble(name, input.readDouble()));
            } else if (type == 7) {
                int length = input.readInt();
                byte[] data = new byte[length];
                input.read(data, 0, length);
                current.add(name, new NbtByteArray(name, data));
            } else if (type == 8) {
                current.add(name, new NbtString(name, input.readUTF()));
            } else if (type == 9) {
                current.add(name, readList(name, input));
            } else if (type == 10) {
                current.add(name, read(name, input));
            } else if (type == 11) {
                int length = input.readInt();
                int[] data = new int[length];
                for (int i = 0; i < length; i++) {
                    data[i] = input.readInt();
                }
                current.add(name, new NbtIntArray(name, data));
            }
        }
        return current;
    }

}
