package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.block.tileentity.carrier.Furnace;

public class SFurnace extends AbstractTileEntityCarrier implements Furnace {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.FURNACE;
    }

    @Override
    public boolean smelt() {
        // TODO Auto-generated method stub
        return false;
    }

}
