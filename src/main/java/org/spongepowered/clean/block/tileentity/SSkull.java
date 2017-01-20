package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.Skull;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SSkull extends AbstractTileEntity implements Skull {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.SKULL;
    }

}
