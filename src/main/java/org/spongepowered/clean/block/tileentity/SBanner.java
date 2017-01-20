package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.Banner;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SBanner extends AbstractTileEntity implements Banner {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.BANNER;
    }

}
