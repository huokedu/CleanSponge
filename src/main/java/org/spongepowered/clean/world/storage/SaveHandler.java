package org.spongepowered.clean.world.storage;

import java.io.File;
import java.io.IOException;

import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.world.storage.WorldProperties;
import org.spongepowered.clean.data.DataQueries;
import org.spongepowered.clean.nbt.NbtIO;
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
            return new SWorldProperties(this);
        }
        SWorldProperties props = new SWorldProperties(this);
        DataView levelDat = NbtIO.read(levelDatFile).getView(DataQuery.of("Data")).get();
        // TODO load data from level.dat
        this.name = levelDat.getString(DataQueries.LEVEL_NAME).get();
        return props;
    }

}
