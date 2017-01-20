package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.Note;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SNote extends AbstractTileEntity implements Note {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.NOTE;
    }

    @Override
    public void playNote() {
        // TODO Auto-generated method stub
        
    }

}
