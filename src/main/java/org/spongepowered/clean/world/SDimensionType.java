package org.spongepowered.clean.world;

import org.spongepowered.api.world.Dimension;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SDimensionType extends AbstractCatalogType implements DimensionType {

    public SDimensionType(String id, String name) {
        super(id, name);
    }

    @Override
    public Class<? extends Dimension> getDimensionClass() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void registerTypes(FixedCatalogRegistryModule<DimensionType> registry) {
        registry.register(new SDimensionType("minecraft:nether", "Nether"));
        registry.register(new SDimensionType("minecraft:overworld", "Overworld"));
        registry.register(new SDimensionType("minecraft:the_end", "The End"));
    }

}
