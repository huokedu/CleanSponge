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
package org.spongepowered.clean.entity;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.entity.item.SpongeExperienceOrb;
import org.spongepowered.clean.entity.item.SpongeFallingBlock;
import org.spongepowered.clean.entity.item.SpongeEntityItem;
import org.spongepowered.clean.entity.player.SpongePlayer;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.modules.entity.EntityTypeRegistryModule;
import org.spongepowered.clean.world.SpongeWorld;

import java.util.function.Function;

public class SpongeEntityType extends AbstractCatalogType implements EntityType {

    // @formatter:off
    public static final SpongeEntityType EXPERIENCE_ORB = new SpongeEntityType("minecraft:exp_orb", "Experience Orb", SpongeExperienceOrb.class, SpongeExperienceOrb::new);
    public static final SpongeEntityType FALLING_BLOCK = new SpongeEntityType("minecraft:falling_block", "Falling Block", SpongeFallingBlock.class, SpongeFallingBlock::new);
    public static final SpongeEntityType ITEM = new SpongeEntityType("minecraft:item", "Item", SpongeEntityItem.class, SpongeEntityItem::new);
    public static final SpongeEntityType PLAYER = new SpongeEntityType("minecraft:player", "Player", SpongePlayer.class, null);
    // @formatter:on

    public static void register(EntityTypeRegistryModule registry) {
        registry.registerInternal("minecraft:falling_block", FALLING_BLOCK);
        registry.registerInternal("minecraft:item", ITEM);
        registry.registerInternal("minecraft:player", PLAYER);
    }

    private final Class<? extends Entity> entityType;
    private Function<SpongeWorld, Entity> builder;

    public SpongeEntityType(String id, String name, Class<? extends Entity> type, Function<SpongeWorld, Entity> builder) {
        super(id, name);
        this.entityType = type;
        this.builder = builder;
    }

    @Override
    public Translation getTranslation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<? extends Entity> getEntityClass() {
        return this.entityType;
    }

    public Function<SpongeWorld, Entity> getBuilder() {
        return this.builder;
    }

}
