package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.Comparator;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SComparator extends AbstractTileEntity implements Comparator {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.COMPARATOR;
    }

}
