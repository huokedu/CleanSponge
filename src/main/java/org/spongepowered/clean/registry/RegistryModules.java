/*
 * This file is part of SpongeClean, licensed under the MIT License (MIT).
 *
 * Copyright (c) The VoxelBox <http://thevoxelbox.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.clean.registry;

import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.data.type.ArmorType;
import org.spongepowered.api.data.type.Art;
import org.spongepowered.api.data.type.HandType;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.api.world.gen.PopulatorType;
import org.spongepowered.api.world.schematic.BlockPaletteType;
import org.spongepowered.clean.block.SBlockType;
import org.spongepowered.clean.block.tileentity.STileEntityType;
import org.spongepowered.clean.data.type.SArmorType;
import org.spongepowered.clean.data.type.SArt;
import org.spongepowered.clean.entity.SEntityType;
import org.spongepowered.clean.entity.player.SGameMode;
import org.spongepowered.clean.entity.player.SHandType;
import org.spongepowered.clean.item.SItemType;
import org.spongepowered.clean.world.SDifficulty;
import org.spongepowered.clean.world.SDimensionType;
import org.spongepowered.clean.world.SWorldArchetype;
import org.spongepowered.clean.world.biome.SBiomeType;
import org.spongepowered.clean.world.gen.SGeneratorType;
import org.spongepowered.clean.world.gen.SPopulatorType;
import org.spongepowered.clean.world.palette.SBlockPaletteType;

public class RegistryModules {

    public static void registerModules() {
        GameRegistry reg = Sponge.getRegistry();
        reg.registerModule(ArmorType.class, new FixedCatalogRegistryModule<>(ArmorType.class, SArmorType::registerTypes));
        reg.registerModule(Art.class, new FixedCatalogRegistryModule<>(Art.class, SArt::registerTypes));
        reg.registerModule(BiomeType.class, new FixedCatalogRegistryModule<>(BiomeType.class, SBiomeType::registerTypes));
        reg.registerModule(BlockType.class, new FixedCatalogRegistryModule<>(BlockType.class, SBlockType::registerTypes));
        reg.registerModule(BlockPaletteType.class, new FixedCatalogRegistryModule<>(BlockPaletteType.class, SBlockPaletteType::registerTypes));
        reg.registerModule(Difficulty.class, new FixedCatalogRegistryModule<>(Difficulty.class, SDifficulty::registerTypes));
        reg.registerModule(DimensionType.class, new FixedCatalogRegistryModule<>(DimensionType.class, SDimensionType::registerTypes));
        reg.registerModule(EntityType.class, new FixedCatalogRegistryModule<>(EntityType.class, SEntityType::registerTypes));
        reg.registerModule(GameMode.class, new FixedCatalogRegistryModule<>(GameMode.class, SGameMode::registerTypes));
        reg.registerModule(GeneratorType.class, new FixedCatalogRegistryModule<>(GeneratorType.class, SGeneratorType::registerTypes));
        reg.registerModule(HandType.class, new FixedCatalogRegistryModule<>(HandType.class, SHandType::registerTypes));
        reg.registerModule(PopulatorType.class, new FixedCatalogRegistryModule<>(PopulatorType.class, SPopulatorType::registerTypes));
        reg.registerModule(TileEntityType.class, new FixedCatalogRegistryModule<>(TileEntityType.class, STileEntityType::registerTypes));
    }

    public static void registerLateModules() {
        GameRegistry reg = Sponge.getRegistry();
        reg.registerModule(ItemType.class, new FixedCatalogRegistryModule<>(ItemType.class, SItemType::registerTypes));
        reg.registerModule(WorldArchetype.class, new FixedCatalogRegistryModule<>(WorldArchetype.class, SWorldArchetype::registerTypes));
    }

}
