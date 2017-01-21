package org.spongepowered.clean.world.gen;

import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.api.world.gen.PopulatorType;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SPopulatorType extends AbstractCatalogType implements PopulatorType {

    public SPopulatorType(String id, String name) {
        super(id, name);
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void registerTypes(FixedCatalogRegistryModule<PopulatorType> registry) {
        registry.register(new SPopulatorType("minecraft:big_mushroom", "Big Mushroom"));
        registry.register(new SPopulatorType("minecraft:block_blob", "Block Blob"));
        registry.register(new SPopulatorType("minecraft:cactus", "Cactus"));
        registry.register(new SPopulatorType("minecraft:chorus_flower", "Chorus Flower"));
        registry.register(new SPopulatorType("minecraft:dead_bush", "Dead Bush"));
        registry.register(new SPopulatorType("minecraft:desert_well", "Desert Well"));
        registry.register(new SPopulatorType("minecraft:double_plant", "Double Plant"));
        registry.register(new SPopulatorType("minecraft:dungeon", "Dungeon"));
        registry.register(new SPopulatorType("minecraft:end_island", "End Island"));
        registry.register(new SPopulatorType("minecraft:flower", "Flower"));
        registry.register(new SPopulatorType("minecraft:forest", "Forest"));
        registry.register(new SPopulatorType("minecraft:generic_block", "Generic Block"));
        registry.register(new SPopulatorType("minecraft:generic_object", "Generic Object"));
        registry.register(new SPopulatorType("minecraft:glowstone", "Glowstone"));
        registry.register(new SPopulatorType("minecraft:ice_path", "Ice Path"));
        registry.register(new SPopulatorType("minecraft:ice_spike", "Ice Spike"));
        registry.register(new SPopulatorType("minecraft:lake", "Lake"));
        registry.register(new SPopulatorType("minecraft:melon", "Melon"));
        registry.register(new SPopulatorType("minecraft:mushroom", "Mushroom"));
        registry.register(new SPopulatorType("minecraft:nether_fire", "Nether Fire"));
        registry.register(new SPopulatorType("minecraft:ore", "Ore"));
        registry.register(new SPopulatorType("minecraft:pumpkin", "Pumpkin"));
        registry.register(new SPopulatorType("minecraft:reed", "Reed"));
        registry.register(new SPopulatorType("minecraft:sea_floor", "Sea Floor"));
        registry.register(new SPopulatorType("minecraft:shrub", "Shrub"));
        registry.register(new SPopulatorType("minecraft:vine", "Vine"));
        registry.register(new SPopulatorType("minecraft:water_lily", "Water Lily"));
    }

}
