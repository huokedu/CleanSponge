package org.spongepowered.clean.block;

import java.util.Collection;
import java.util.Optional;

import org.spongepowered.api.block.BlockSoundGroup;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.trait.BlockTrait;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.registry.AbstractCatalogType;

public class SBlockType extends AbstractCatalogType implements BlockType {
    
    public SBlockType(String id, String name) {
        super(id, name);
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getProperty(Class<T> propertyClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Property<?, ?>> getApplicableProperties() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockState getDefaultState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ItemType> getItem() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean getTickRandomly() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setTickRandomly(boolean tickRandomly) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Collection<BlockTrait<?>> getTraits() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockTrait<?>> getTrait(String blockTrait) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockSoundGroup getSoundGroup() {
        // TODO Auto-generated method stub
        return null;
    }

}
