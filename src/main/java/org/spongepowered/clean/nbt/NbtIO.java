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

import static org.spongepowered.api.data.DataQuery.of;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.spongepowered.api.data.DataContainer;
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

    private static DataContainer read(DataInputStream input) throws IOException {
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

}
