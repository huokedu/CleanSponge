package org.spongepowered.clean.world.gen;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.gen.WorldGenerator;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SGeneratorType extends AbstractCatalogType implements GeneratorType {

    public SGeneratorType(String id, String name) {
        super(id, name);
    }

    @Override
    public DataContainer getGeneratorSettings() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WorldGenerator createGenerator(World world) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void registerTypes(FixedCatalogRegistryModule<GeneratorType> registry) {
        registry.register(new SGeneratorType("minecraft:amplified", "Amplified"));
        registry.register(new SGeneratorType("minecraft:debug", "Debug"));
        registry.register(new SGeneratorType("minecraft:default", "Default"));
        registry.register(new SGeneratorType("minecraft:flat", "Flat"));
        registry.register(new SGeneratorType("minecraft:large_biomes", "Large Biomes"));
        registry.register(new SGeneratorType("minecraft:nether", "Nether"));
        registry.register(new SGeneratorType("minecraft:overworld", "Overworld"));
        registry.register(new SGeneratorType("minecraft:the_end", "The End"));
    }

}
