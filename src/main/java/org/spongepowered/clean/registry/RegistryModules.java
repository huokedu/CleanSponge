package org.spongepowered.clean.registry;

import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.api.world.schematic.BlockPaletteType;
import org.spongepowered.clean.block.SBlockType;
import org.spongepowered.clean.block.tileentity.STileEntityType;
import org.spongepowered.clean.entity.player.SGameMode;
import org.spongepowered.clean.item.SItemType;
import org.spongepowered.clean.world.SDifficulty;
import org.spongepowered.clean.world.SDimensionType;
import org.spongepowered.clean.world.biome.SBiomeType;
import org.spongepowered.clean.world.gen.SGeneratorType;
import org.spongepowered.clean.world.palette.SBlockPaletteType;

public class RegistryModules {

    public static void registerModules() {
        GameRegistry reg = Sponge.getRegistry();
        reg.registerModule(BiomeType.class, new FixedCatalogRegistryModule<>(BiomeType.class, SBiomeType::registerTypes));
        reg.registerModule(BlockType.class, new FixedCatalogRegistryModule<>(BlockType.class, SBlockType::registerTypes));
        reg.registerModule(BlockPaletteType.class, new FixedCatalogRegistryModule<>(BlockPaletteType.class, SBlockPaletteType::registerTypes));
        reg.registerModule(Difficulty.class, new FixedCatalogRegistryModule<>(Difficulty.class, SDifficulty::registerTypes));
        reg.registerModule(DimensionType.class, new FixedCatalogRegistryModule<>(DimensionType.class, SDimensionType::registerTypes));
        reg.registerModule(GameMode.class, new FixedCatalogRegistryModule<>(GameMode.class, SGameMode::registerTypes));
        reg.registerModule(GeneratorType.class, new FixedCatalogRegistryModule<>(GeneratorType.class, SGeneratorType::registerTypes));
        reg.registerModule(TileEntityType.class, new FixedCatalogRegistryModule<>(TileEntityType.class, STileEntityType::registerTypes));

        reg.registerModule(ItemType.class, new FixedCatalogRegistryModule<>(ItemType.class, SItemType::registerTypes));
    }

}
