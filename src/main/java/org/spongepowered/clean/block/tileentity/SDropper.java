package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.block.tileentity.carrier.Dropper;

public class SDropper extends AbstractTileEntityCarrier implements Dropper {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.DROPPER;
    }

}
