package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.Structure;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SStructure extends AbstractTileEntity implements Structure {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.STRUCTURE;
    }

}
