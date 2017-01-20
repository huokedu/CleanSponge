package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.block.tileentity.carrier.Beacon;

public class SBeacon extends AbstractTileEntityCarrier implements Beacon {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.BEACON;
    }

    @Override
    public int getCompletedLevels() {
        // TODO Auto-generated method stub
        return 0;
    }

}
