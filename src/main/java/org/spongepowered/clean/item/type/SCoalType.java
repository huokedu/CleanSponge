package org.spongepowered.clean.item.type;

import org.spongepowered.api.data.type.CoalType;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SCoalType extends AbstractCatalogType implements CoalType {

    public SCoalType(String id, String name) {
        super(id, name);
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static void registerTypes(FixedCatalogRegistryModule<CoalType> registry) {
        registry.register(new SCoalType("minecraft:coal", ""));
        registry.register(new SCoalType("minecraft:charcoal", ""));
    }

}
