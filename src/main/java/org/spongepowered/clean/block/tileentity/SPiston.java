package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.Piston;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SPiston extends AbstractTileEntity implements Piston {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.PISTON;
    }

}
