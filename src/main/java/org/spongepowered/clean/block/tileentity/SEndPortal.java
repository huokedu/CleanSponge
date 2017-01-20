package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.EndPortal;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SEndPortal extends AbstractTileEntity implements EndPortal {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.END_PORTAL;
    }

}
