package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.carrier.TileEntityCarrier;
import org.spongepowered.api.item.inventory.type.TileEntityInventory;

public abstract class AbstractTileEntityCarrier extends AbstractTileEntity implements TileEntityCarrier {

    public AbstractTileEntityCarrier() {

    }

    @Override
    public TileEntityInventory<TileEntityCarrier> getInventory() {
        // TODO Auto-generated method stub
        return null;
    }

}
