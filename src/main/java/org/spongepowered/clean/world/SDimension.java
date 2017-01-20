package org.spongepowered.clean.world;

import org.spongepowered.api.service.context.Context;
import org.spongepowered.api.world.Dimension;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.GeneratorType;

public class SDimension implements Dimension {

    private final DimensionType type;

    public SDimension(DimensionType type) {
        this.type = type;
    }

    @Override
    public Context getContext() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DimensionType getType() {
        return this.type;
    }

    @Override
    public GeneratorType getGeneratorType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean allowsPlayerRespawns() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getMinimumSpawnHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean doesWaterEvaporate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasSky() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getBuildHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

}
