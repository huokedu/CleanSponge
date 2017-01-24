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
package org.spongepowered.clean.world.biome;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.biome.BiomeGenerationSettings;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SBiomeType extends AbstractCatalogType implements BiomeType {

    private static final Int2ObjectOpenHashMap<BiomeType> idToBiome = new Int2ObjectOpenHashMap<>();

    public static BiomeType getBiome(int id) {
        return idToBiome.get(id);
    }

    private final int biomeId;

    public SBiomeType(String id, String name, int biomeId) {
        super(id, name);
        this.biomeId = biomeId;
        idToBiome.put(this.biomeId, this);
    }

    public int getBiomeId() {
        return this.biomeId;
    }

    @Override
    public double getTemperature() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getHumidity() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public BiomeGenerationSettings createDefaultGenerationSettings(World world) {
        SBiomeGenerationSettingsBuilder builder = new SBiomeGenerationSettingsBuilder();
        // TODO populate generation settings
        return builder.build();
    }

    public static void registerTypes(FixedCatalogRegistryModule<BiomeType> registry) {
        registry.register(new SBiomeType("minecraft:ocean", "Ocean", 0));
        registry.register(new SBiomeType("minecraft:plains", "Plains", 1));
        registry.register(new SBiomeType("minecraft:desert", "Desert", 2));
        registry.register(new SBiomeType("minecraft:extreme_hills", "Extreme Hills", 3));
        registry.register(new SBiomeType("minecraft:forest", "Forest", 4));
        registry.register(new SBiomeType("minecraft:taiga", "Taiga", 5));
        registry.register(new SBiomeType("minecraft:swampland", "Swampland", 6));
        registry.register(new SBiomeType("minecraft:river", "River", 7));
        registry.register(new SBiomeType("minecraft:hell", "Hell", 8));
        registry.register(new SBiomeType("minecraft:sky", "The End", 9));
        registry.register(new SBiomeType("minecraft:frozen_ocean", "Frozen Ocean", 10));
        registry.register(new SBiomeType("minecraft:frozen_river", "Frozen River", 11));
        registry.register(new SBiomeType("minecraft:ice_flats", "Ice Plains", 12));
        registry.register(new SBiomeType("minecraft:ice_mountains", "Ice Mountains", 13));
        registry.register(new SBiomeType("minecraft:mushroom_island", "Mushroom Island", 14));
        registry.register(new SBiomeType("minecraft:mushroom_island_shore", "Mushroom Island Shore", 15));
        registry.register(new SBiomeType("minecraft:beaches", "Beaches", 16));
        registry.register(new SBiomeType("minecraft:desert_hills", "Desert Hills", 17));
        registry.register(new SBiomeType("minecraft:forest_hills", "Forest Hills", 18));
        registry.register(new SBiomeType("minecraft:taiga_hills", "Taiga Hills", 19));
        registry.register(new SBiomeType("minecraft:smaller_extreme_hills", "Extreme Hills Edge", 20));
        registry.register(new SBiomeType("minecraft:jungle", "Jungle", 21));
        registry.register(new SBiomeType("minecraft:jungle_hills", "Jungle Hills", 22));
        registry.register(new SBiomeType("minecraft:jungle_edge", "Jungle Edge", 23));
        registry.register(new SBiomeType("minecraft:deep_ocean", "Deep Ocean", 24));
        registry.register(new SBiomeType("minecraft:stone_beach", "Stone Beach", 25));
        registry.register(new SBiomeType("minecraft:cold_beach", "Cold Beach", 26));
        registry.register(new SBiomeType("minecraft:birch_forest", "Birch Forest", 27));
        registry.register(new SBiomeType("minecraft:birch_forest_hills", "Birch Forest Hills", 28));
        registry.register(new SBiomeType("minecraft:roofed_forest", "Roofed Forest", 29));
        registry.register(new SBiomeType("minecraft:taiga_cold", "Cold Taiga", 30));
        registry.register(new SBiomeType("minecraft:taiga_cold_hills", "Cold Taiga Hills", 31));
        registry.register(new SBiomeType("minecraft:redwood_taiga", "Mega Taiga", 32));
        registry.register(new SBiomeType("minecraft:redwood_taiga_hills", "Mega Taiga Hills", 33));
        registry.register(new SBiomeType("minecraft:extreme_hills_with_trees", "Extreme Hills+", 34));
        registry.register(new SBiomeType("minecraft:savanna", "Savanna", 35));
        registry.register(new SBiomeType("minecraft:savanna_rock", "Savanna Plateau", 36));
        registry.register(new SBiomeType("minecraft:mesa", "Mesa", 37));
        registry.register(new SBiomeType("minecraft:mesa_rock", "Mesa Plateau F", 38));
        registry.register(new SBiomeType("minecraft:mesa_clear_rock", "Mesa Plateau", 39));

        registry.register(new SBiomeType("minecraft:void", "The Void", 127));
        registry.register(new SBiomeType("minecraft:mutated_plains", "Sunflower Plains", 129));
        registry.register(new SBiomeType("minecraft:mutated_desert", "Desert M", 130));
        registry.register(new SBiomeType("minecraft:mutated_extreme_hills", "Extreme Hills M", 131));
        registry.register(new SBiomeType("minecraft:mutated_forest", "Flower Forest", 132));
        registry.register(new SBiomeType("minecraft:mutated_taiga", "Taiga M", 133));
        registry.register(new SBiomeType("minecraft:mutated_swampland", "Swampland M", 134));
        registry.register(new SBiomeType("minecraft:mutated_ice_flats", "Ice Plains Spikes", 140));
        registry.register(new SBiomeType("minecraft:mutated_jungle", "Jungle M", 149));
        registry.register(new SBiomeType("minecraft:mutated_jungle_edge", "Jungle Edge M", 151));
        registry.register(new SBiomeType("minecraft:mutated_birch_forest", "Birch Forest M", 155));
        registry.register(new SBiomeType("minecraft:mutated_birch_forest_hills", "Birch Forest Hills M", 156));
        registry.register(new SBiomeType("minecraft:mutated_roofed_forest", "Roofed Forest M", 157));
        registry.register(new SBiomeType("minecraft:mutated_taiga_cold", "Cold Taiga M", 158));
        registry.register(new SBiomeType("minecraft:mutated_redwood_taiga", "Mega Spruce Taiga", 160));
        registry.register(new SBiomeType("minecraft:mutated_redwood_taiga_hills", "Redwood Taiga Hills M", 161));
        registry.register(new SBiomeType("minecraft:mutated_extreme_hills_with_trees", "Extreme Hills+ M", 162));
        registry.register(new SBiomeType("minecraft:mutated_savanna", "Savanna M", 163));
        registry.register(new SBiomeType("minecraft:mutated_savanna_rock", "Savanna Plateau M", 164));
        registry.register(new SBiomeType("minecraft:mutated_mesa", "Mesa (Bryce)", 165));
        registry.register(new SBiomeType("minecraft:mutated_mesa_rock", "Mesa Plateau F M", 166));
        registry.register(new SBiomeType("minecraft:mutated_mesa_clear_rock", "Mesa Plateau M", 167));

        registry.registerAlias("minecraft:beach", "minecraft:beaches");
        registry.registerAlias("minecraft:cold_taiga", "minecraft:taiga_cold");
        registry.registerAlias("minecraft:cold_taiga_hills", "minecraft:taiga_cold_hills");
        registry.registerAlias("minecraft:extreme_hills_edge", "minecraft:smaller_extreme_hills");
        registry.registerAlias("minecraft:extreme_hills_plus", "minecraft:extreme_hills_with_trees");
        registry.registerAlias("minecraft:ice_plains", "minecraft:ice_flats");
        registry.registerAlias("minecraft:mega_taiga", "minecraft:redwood_taiga");
        registry.registerAlias("minecraft:mega_taiga_hills", "minecraft:redwood_taiga_hills");
        registry.registerAlias("minecraft:mesa_plateau", "minecraft:mesa_clear_rock");
        registry.registerAlias("minecraft:mesa_plateau_forest", "minecraft:mesa_rock");
        registry.registerAlias("minecraft:savanna_plateau", "minecraft:savanna_rock");
        registry.registerAlias("minecraft:birch_forest_hills_mountains", "minecraft:mutated_birch_forest_hills");
        registry.registerAlias("minecraft:birch_forest_mountains", "minecraft:mutated_birch_forest");
        registry.registerAlias("minecraft:cold_taiga_mountains", "minecraft:mutated_taiga_cold");
        registry.registerAlias("minecraft:desert_mountains", "minecraft:mutated_desert");
        registry.registerAlias("minecraft:extreme_hills_mountains", "minecraft:mutated_extreme_hills");
        registry.registerAlias("minecraft:extreme_hills_plus_mountains", "minecraft:mutated_extreme_hills_with_trees");
        registry.registerAlias("minecraft:flower_forest", "minecraft:mutated_forest");
        registry.registerAlias("minecraft:ice_plains_spikes", "minecraft:mutated_ice_flats");
        registry.registerAlias("minecraft:jungle_edge_mountains", "minecraft:mutated_jungle_edge");
        registry.registerAlias("minecraft:jungle_mountains", "minecraft:mutated_jungle");
        registry.registerAlias("minecraft:mega_spruce_taiga", "minecraft:mutated_redwood_taiga");
        registry.registerAlias("minecraft:mega_spruce_taiga_hills", "minecraft:mutated_redwood_taiga_hills");
        registry.registerAlias("minecraft:mesa_bryce", "minecraft:mutated_mesa");
        registry.registerAlias("minecraft:mesa_plateau_forest_mountains", "minecraft:mutated_mesa_rock");
        registry.registerAlias("minecraft:mesa_plateau_mountains", "minecraft:mutated_mesa_clear_rock");
        registry.registerAlias("minecraft:roofed_forest_mountains", "minecraft:mutated_roofed_forest");
        registry.registerAlias("minecraft:savanna_mountains", "minecraft:mutated_savanna");
        registry.registerAlias("minecraft:savanna_plateau_mountains", "minecraft:mutated_savanna_rock");
        registry.registerAlias("minecraft:sunflower_plains", "minecraft:mutated_plains");
        registry.registerAlias("minecraft:swampland_mountains", "minecraft:mutated_swampland");
        registry.registerAlias("minecraft:taiga_mountains", "minecraft:mutated_taiga");
    }

}
