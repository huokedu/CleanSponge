package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.block.tileentity.carrier.Chest;
import org.spongepowered.api.item.inventory.Inventory;

import java.util.Optional;

public class SChest extends AbstractTileEntityCarrier implements Chest {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.CHEST;
    }

    @Override
    public Optional<Inventory> getDoubleChestInventory() {
        // TODO Auto-generated method stub
        return null;
    }

}
