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
package org.spongepowered.clean.statistic;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Optional;

import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.scoreboard.critieria.Criterion;
import org.spongepowered.api.statistic.achievement.Achievement;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SAchievement extends AbstractCatalogType implements Achievement {

    public SAchievement(String id, String name) {
        super(id, name);
    }

    @Override
    public Optional<Criterion> getCriterion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NumberFormat getFormat() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Translation getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Achievement> getParent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Achievement> getChildren() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ItemStackSnapshot> getItemStackSnapshot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isSpecial() {
        // TODO Auto-generated method stub
        return false;
    }

    public static void registerTypes(FixedCatalogRegistryModule<Achievement> registry) {
        registry.register(new SAchievement("minecraft:acquire_iron", ""));
        registry.register(new SAchievement("minecraft:bake_cake", ""));
        registry.register(new SAchievement("minecraft:blaze_rod", ""));
        registry.register(new SAchievement("minecraft:bookcase", ""));
        registry.register(new SAchievement("minecraft:breed_cow", ""));
        registry.register(new SAchievement("minecraft:build_better_pickaxe", ""));
        registry.register(new SAchievement("minecraft:build_furnace", ""));
        registry.register(new SAchievement("minecraft:build_hoe", ""));
        registry.register(new SAchievement("minecraft:build_pickaxe", ""));
        registry.register(new SAchievement("minecraft:build_sword", ""));
        registry.register(new SAchievement("minecraft:build_work_bench", ""));
        registry.register(new SAchievement("minecraft:cook_fish", ""));
        registry.register(new SAchievement("minecraft:diamonds", ""));
        registry.register(new SAchievement("minecraft:diamonds_to_you", ""));
        registry.register(new SAchievement("minecraft:enchantments", ""));
        registry.register(new SAchievement("minecraft:explore_all_biomes", ""));
        registry.register(new SAchievement("minecraft:fly_pig", ""));
        registry.register(new SAchievement("minecraft:full_beacon", ""));
        registry.register(new SAchievement("minecraft:ghast", ""));
        registry.register(new SAchievement("minecraft:kill_cow", ""));
        registry.register(new SAchievement("minecraft:kill_enemy", ""));
        registry.register(new SAchievement("minecraft:kill_wither", ""));
        registry.register(new SAchievement("minecraft:make_bread", ""));
        registry.register(new SAchievement("minecraft:mine_wood", ""));
        registry.register(new SAchievement("minecraft:on_a_rail", ""));
        registry.register(new SAchievement("minecraft:open_inventory", ""));
        registry.register(new SAchievement("minecraft:overkill", ""));
        registry.register(new SAchievement("minecraft:overpowered", ""));
        registry.register(new SAchievement("minecraft:portal", ""));
        registry.register(new SAchievement("minecraft:potion", ""));
        registry.register(new SAchievement("minecraft:snipe_skeleton", ""));
        registry.register(new SAchievement("minecraft:spawn_wither", ""));
        registry.register(new SAchievement("minecraft:the_end", ""));
        registry.register(new SAchievement("minecraft:the_end2", ""));
    }

}
