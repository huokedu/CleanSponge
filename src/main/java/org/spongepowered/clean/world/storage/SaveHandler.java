package org.spongepowered.clean.world.storage;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.world.storage.WorldProperties;
import org.spongepowered.clean.nbt.NbtIO;
import org.spongepowered.clean.world.SWorldProperties;

import java.io.File;
import java.io.IOException;

public class SaveHandler {

    private final File worldDir;

    public SaveHandler(File dir) {
        this.worldDir = dir;
    }

    public WorldProperties loadProperties() throws IOException {
        File levelDatFile = new File(this.worldDir, "level.dat");
        if (!levelDatFile.exists()) {
            return new SWorldProperties(this);
        }
        SWorldProperties props = new SWorldProperties(this);
        DataContainer levelDat = NbtIO.read(levelDatFile);
        // TODO load data from level.dat
        return props;
    }

}
