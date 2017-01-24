package org.spongepowered.clean.block.type;

import org.spongepowered.api.data.type.ComparatorType;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SComparatorType extends AbstractCatalogType implements ComparatorType {

    public SComparatorType(String id, String name) {
        super(id, name);
    }

    public static void registerTypes(FixedCatalogRegistryModule<ComparatorType> registry) {
        registry.register(new SComparatorType("minecraft:compare", ""));
        registry.register(new SComparatorType("minecraft:subtract", ""));
    }

}
