package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.block.tileentity.carrier.Hopper;

public class SHopper extends AbstractTileEntityCarrier implements Hopper {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.HOPPER;
    }

    @Override
    public void transferItem() {
        // TODO Auto-generated method stub

    }

}
