package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.EnderChest;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SEnderChest extends AbstractTileEntity implements EnderChest {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.ENDER_CHEST;
    }

}
