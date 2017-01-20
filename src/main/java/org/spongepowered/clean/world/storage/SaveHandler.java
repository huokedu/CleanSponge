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
package org.spongepowered.clean.world.storage;

import java.io.File;
import java.io.IOException;

import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.world.storage.WorldProperties;
import org.spongepowered.clean.data.DataQueries;
import org.spongepowered.clean.util.NbtIO;
import org.spongepowered.clean.world.SChunk;
import org.spongepowered.clean.world.SWorldProperties;

public class SaveHandler {

    public static final int CURRENT_CONTENT_VERSION = 0;

    private final File worldDir;
    private String name;

    public SaveHandler(File dir) {
        this.worldDir = dir;
    }

    public String getWorldName() {
        return this.name;
    }

    public WorldProperties loadProperties() throws IOException {
        File levelDatFile = new File(this.worldDir, "level.dat");
        if (!levelDatFile.exists()) {
            throw new IllegalStateException("Cannot load properties of world " + this.worldDir.getAbsolutePath());
        }
        DataView levelDat = NbtIO.read(levelDatFile).getView(DataQuery.of("Data")).get();
        SWorldProperties props = new SWorldProperties(this, levelDat);
        this.name = levelDat.getString(DataQueries.LEVEL_NAME).get();
        return props;
    }

    public SChunk loadChunk(int x, int z) {
        // TODO make sure a not world-synced load for this chunk is not already
        // in flight or queued
        // TODO Auto-generated method stub
        return null;
    }

}
