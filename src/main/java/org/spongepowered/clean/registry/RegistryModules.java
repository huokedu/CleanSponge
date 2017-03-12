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
import org.spongepowered.api.boss.BossBarColor;
import org.spongepowered.api.boss.BossBarOverlay;
import org.spongepowered.api.data.type.ArmorType;
import org.spongepowered.api.data.type.Art;
import org.spongepowered.api.data.type.BannerPatternShape;
import org.spongepowered.api.data.type.BigMushroomType;
import org.spongepowered.api.data.type.BodyPart;
import org.spongepowered.api.data.type.BrickType;
import org.spongepowered.api.data.type.Career;
import org.spongepowered.api.data.type.CoalType;
import org.spongepowered.api.data.type.ComparatorType;
import org.spongepowered.api.data.type.HandType;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.scoreboard.CollisionRule;
import org.spongepowered.api.statistic.achievement.Achievement;
import org.spongepowered.api.text.chat.ChatType;
import org.spongepowered.api.text.chat.ChatVisibility;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.serializer.TextSerializer;
import org.spongepowered.api.util.ban.BanType;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.api.world.gen.PopulatorType;
import org.spongepowered.api.world.gen.type.BiomeTreeType;
import org.spongepowered.api.world.schematic.BlockPaletteType;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.block.SBlockType;
import org.spongepowered.clean.block.tileentity.STileEntityType;
import org.spongepowered.clean.block.type.SBannerPatternShape;
import org.spongepowered.clean.block.type.SBigMushroomType;
import org.spongepowered.clean.block.type.SBrickType;
import org.spongepowered.clean.block.type.SComparatorType;
import org.spongepowered.clean.boss.SBossBarColor;
import org.spongepowered.clean.boss.SBossBarOverlay;
import org.spongepowered.clean.entity.SEntityType;
import org.spongepowered.clean.entity.player.SGameMode;
import org.spongepowered.clean.entity.player.SHandType;
import org.spongepowered.clean.entity.type.SArt;
import org.spongepowered.clean.entity.type.SBodyPart;
import org.spongepowered.clean.entity.type.SCareer;
import org.spongepowered.clean.item.SItemType;
import org.spongepowered.clean.item.type.SArmorType;
import org.spongepowered.clean.item.type.SCoalType;
import org.spongepowered.clean.scoreboard.type.SCollisionRule;
import org.spongepowered.clean.service.ban.SBanType;
import org.spongepowered.clean.statistic.SAchievement;
import org.spongepowered.clean.text.format.STextColor;
import org.spongepowered.clean.text.serializer.STextSerializer;
import org.spongepowered.clean.text.type.SChatType;
import org.spongepowered.clean.text.type.SChatVisibility;
import org.spongepowered.clean.world.SDifficulty;
import org.spongepowered.clean.world.SDimensionType;
import org.spongepowered.clean.world.SWorldArchetype;
import org.spongepowered.clean.world.biome.SBiomeType;
import org.spongepowered.clean.world.gen.SGeneratorType;
import org.spongepowered.clean.world.gen.SPopulatorType;
import org.spongepowered.clean.world.gen.type.SBiomeTreeType;
import org.spongepowered.clean.world.palette.SBlockPaletteType;

public class RegistryModules {

    public static void registerModules() {
        GameRegistry reg = Sponge.getRegistry();
        reg.registerModule(Achievement.class, new FixedCatalogRegistryModule<>(Achievement.class, SAchievement::registerTypes));
        reg.registerModule(ArmorType.class, new FixedCatalogRegistryModule<>(ArmorType.class, SArmorType::registerTypes));
        reg.registerModule(Art.class, new FixedCatalogRegistryModule<>(Art.class, SArt::registerTypes));
        reg.registerModule(BannerPatternShape.class, new FixedCatalogRegistryModule<>(BannerPatternShape.class, SBannerPatternShape::registerTypes));
        reg.registerModule(BanType.class, new FixedCatalogRegistryModule<>(BanType.class, SBanType::registerTypes));
        reg.registerModule(BigMushroomType.class, new FixedCatalogRegistryModule<>(BigMushroomType.class, SBigMushroomType::registerTypes));
        reg.registerModule(BiomeTreeType.class, new FixedCatalogRegistryModule<>(BiomeTreeType.class, SBiomeTreeType::registerTypes));
        reg.registerModule(BlockPaletteType.class, new FixedCatalogRegistryModule<>(BlockPaletteType.class, SBlockPaletteType::registerTypes));
        reg.registerModule(BlockType.class, new FixedCatalogRegistryModule<>(BlockType.class, SBlockType::registerTypes));
        reg.registerModule(BodyPart.class, new FixedCatalogRegistryModule<>(BodyPart.class, SBodyPart::registerTypes));
        reg.registerModule(BossBarColor.class, new FixedCatalogRegistryModule<>(BossBarColor.class, SBossBarColor::registerTypes));
        reg.registerModule(BossBarOverlay.class, new FixedCatalogRegistryModule<>(BossBarOverlay.class, SBossBarOverlay::registerTypes));
        reg.registerModule(BrickType.class, new FixedCatalogRegistryModule<>(BrickType.class, SBrickType::registerTypes));
        reg.registerModule(Career.class, new FixedCatalogRegistryModule<>(Career.class, SCareer::registerTypes));
        reg.registerModule(ChatType.class, new FixedCatalogRegistryModule<>(ChatType.class, SChatType::registerTypes));
        reg.registerModule(ChatVisibility.class, new FixedCatalogRegistryModule<>(ChatVisibility.class, SChatVisibility::registerTypes));
        reg.registerModule(CoalType.class, new FixedCatalogRegistryModule<>(CoalType.class, SCoalType::registerTypes));
        reg.registerModule(CollisionRule.class, new FixedCatalogRegistryModule<>(CollisionRule.class, SCollisionRule::registerTypes));
        reg.registerModule(ComparatorType.class, new FixedCatalogRegistryModule<>(ComparatorType.class, SComparatorType::registerTypes));
        reg.registerModule(Difficulty.class, new FixedCatalogRegistryModule<>(Difficulty.class, SDifficulty::registerTypes));
        reg.registerModule(DimensionType.class, new FixedCatalogRegistryModule<>(DimensionType.class, SDimensionType::registerTypes));
        reg.registerModule(EntityType.class, new FixedCatalogRegistryModule<>(EntityType.class, SEntityType::registerTypes));
        reg.registerModule(GameMode.class, new FixedCatalogRegistryModule<>(GameMode.class, SGameMode::registerTypes));
        reg.registerModule(GeneratorType.class, new FixedCatalogRegistryModule<>(GeneratorType.class, SGeneratorType::registerTypes));
        reg.registerModule(HandType.class, new FixedCatalogRegistryModule<>(HandType.class, SHandType::registerTypes));
        reg.registerModule(PopulatorType.class, new FixedCatalogRegistryModule<>(PopulatorType.class, SPopulatorType::registerTypes));
        reg.registerModule(TextColor.class, new FixedCatalogRegistryModule<>(TextColor.class, STextColor::registerTypes));
        reg.registerModule(TextSerializer.class, new FixedCatalogRegistryModule<>(TextSerializer.class, STextSerializer::registerTypes));
        reg.registerModule(TileEntityType.class, new FixedCatalogRegistryModule<>(TileEntityType.class, STileEntityType::registerTypes));

        SGame.game.commandManager.registerBaseCommands();
    }

    public static void registerLateModules() {
        GameRegistry reg = Sponge.getRegistry();
        reg.registerModule(BiomeType.class, new FixedCatalogRegistryModule<>(BiomeType.class, SBiomeType::registerTypes));
        reg.registerModule(ItemType.class, new FixedCatalogRegistryModule<>(ItemType.class, SItemType::registerTypes));
        reg.registerModule(WorldArchetype.class, new FixedCatalogRegistryModule<>(WorldArchetype.class, SWorldArchetype::registerTypes));
    }

}
