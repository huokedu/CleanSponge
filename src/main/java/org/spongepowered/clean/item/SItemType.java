package org.spongepowered.clean.item;

import java.util.Optional;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.registry.AbstractCatalogType;

public class SItemType extends AbstractCatalogType implements ItemType {

    public SItemType(String id, String name) {
        super(id, name);
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemType getType() {
        return this;
    }

    @Override
    public boolean matches(ItemStack stack) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSpecific() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ItemStackSnapshot getTemplate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockType> getBlock() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getMaxStackQuantity() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getDefaultProperty(Class<T> propertyClass) {
        // TODO Auto-generated method stub
        return null;
    }

}
