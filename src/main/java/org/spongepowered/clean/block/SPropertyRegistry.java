package org.spongepowered.clean.block;

import java.util.Optional;

import org.spongepowered.api.data.Property;
import org.spongepowered.api.data.property.PropertyRegistry;
import org.spongepowered.api.data.property.PropertyStore;

public class SPropertyRegistry implements PropertyRegistry {

    @Override
    public <T extends Property<?, ?>> void register(Class<T> propertyClass, PropertyStore<T> propertyStore) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T extends Property<?, ?>> Optional<PropertyStore<T>> getStore(Class<T> propertyClass) {
        // TODO Auto-generated method stub
        return null;
    }

}
