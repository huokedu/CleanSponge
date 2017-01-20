package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.Jukebox;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.item.inventory.ItemStack;

public class SJukebox extends AbstractTileEntity implements Jukebox {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.JUKEBOX;
    }

    @Override
    public void playRecord() {
        // TODO Auto-generated method stub

    }

    @Override
    public void ejectRecord() {
        // TODO Auto-generated method stub

    }

    @Override
    public void insertRecord(ItemStack record) {
        // TODO Auto-generated method stub

    }

}
