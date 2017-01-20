package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.block.tileentity.carrier.BrewingStand;

public class SBrewingStand extends AbstractTileEntityCarrier implements BrewingStand {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.BREWING_STAND;
    }

    @Override
    public boolean brew() {
        // TODO Auto-generated method stub
        return false;
    }

}
