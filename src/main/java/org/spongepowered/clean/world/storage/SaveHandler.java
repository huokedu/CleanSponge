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
