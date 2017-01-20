package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.MobSpawner;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SMobSpawner extends AbstractTileEntity implements MobSpawner {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.MOB_SPAWNER;
    }

    @Override
    public void spawnEntityBatchImmediately(boolean force) {
        // TODO Auto-generated method stub
        
    }

}
