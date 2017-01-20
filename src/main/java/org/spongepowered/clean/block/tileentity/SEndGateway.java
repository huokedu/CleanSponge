package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.EndGateway;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SEndGateway extends AbstractTileEntity implements EndGateway {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.END_GATEWAY;
    }

}
