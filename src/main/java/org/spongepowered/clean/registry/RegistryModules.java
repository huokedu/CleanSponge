package org.spongepowered.clean.registry;

import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.schematic.BlockPaletteType;
import org.spongepowered.clean.block.SBlockType;
import org.spongepowered.clean.world.biome.SBiomeType;
import org.spongepowered.clean.world.palette.SBlockPaletteType;

public class RegistryModules {

    public static void registerModules() {
        GameRegistry reg = Sponge.getRegistry();
        reg.registerModule(BiomeType.class, new FixedCatalogRegistryModule<>(BiomeType.class, SBiomeType::registerTypes));
        reg.registerModule(BlockType.class, new FixedCatalogRegistryModule<>(BlockType.class, SBlockType::registerTypes));
        reg.registerModule(BlockPaletteType.class, new FixedCatalogRegistryModule<>(BlockPaletteType.class, SBlockPaletteType::registerTypes));
    }

}
