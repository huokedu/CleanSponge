package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.FlowerPot;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SFlowerPot extends AbstractTileEntity implements FlowerPot {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.FLOWER_POT;
    }

}
