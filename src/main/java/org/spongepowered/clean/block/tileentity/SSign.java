package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.Sign;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SSign extends AbstractTileEntity implements Sign {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.SIGN;
    }

}
