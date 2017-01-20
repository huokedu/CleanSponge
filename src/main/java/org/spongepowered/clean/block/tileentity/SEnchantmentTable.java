package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.EnchantmentTable;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;

public class SEnchantmentTable extends AbstractTileEntity implements EnchantmentTable {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.ENCHANTMENT_TABLE;
    }

}
