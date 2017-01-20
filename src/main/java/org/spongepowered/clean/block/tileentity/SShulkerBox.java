package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.block.tileentity.carrier.ShulkerBox;

public class SShulkerBox extends AbstractTileEntityCarrier implements ShulkerBox {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.SHULKER_BOX;
    }

}
