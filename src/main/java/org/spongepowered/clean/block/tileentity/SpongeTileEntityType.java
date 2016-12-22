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
package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.modules.block.TileEntityTypeRegistryModule;

public class SpongeTileEntityType extends AbstractCatalogType implements TileEntityType {

    // @formatter:off
    public static final SpongeTileEntityType BANNER = new SpongeTileEntityType("minecraft:banner", "Banner", SpongeTileEntityBanner.class);
    public static final SpongeTileEntityType BEACON = new SpongeTileEntityType("minecraft:beacon", "Beacon", SpongeTileEntityBeacon.class);
    public static final SpongeTileEntityType BREWING_STAND = new SpongeTileEntityType("minecraft:brewing_stand", "Brewing Stand", SpongeTileEntityBrewingStand.class);
    public static final SpongeTileEntityType CHEST = new SpongeTileEntityType("minecraft:chest", "Chest", SpongeTileEntityChest.class);
    public static final SpongeTileEntityType COMMAND_BLOCK = new SpongeTileEntityType("minecraft:command_block", "Chest", SpongeTileEntityCommandBlock.class);
    public static final SpongeTileEntityType COMPARATOR = new SpongeTileEntityType("minecraft:comparator", "Chest", SpongeTileEntityComparator.class);
    public static final SpongeTileEntityType DAYLIGHT_DETECTOR = new SpongeTileEntityType("minecraft:daylight_detector", "Chest", SpongeTileEntityDaylightDetector.class);
    public static final SpongeTileEntityType DISPENSER = new SpongeTileEntityType("minecraft:dispenser", "Chest", SpongeTileEntityDispenser.class);
    public static final SpongeTileEntityType DROPPER = new SpongeTileEntityType("minecraft:dropper", "Chest", SpongeTileEntityDropper.class);
    public static final SpongeTileEntityType ENCHANTMENT_TABLE = new SpongeTileEntityType("minecraft:enchanting_table", "Chest", SpongeTileEntityEnchantmentTable.class);
    public static final SpongeTileEntityType ENDER_CHEST = new SpongeTileEntityType("minecraft:ender_chest", "Chest", SpongeTileEntityEnderChest.class);
    public static final SpongeTileEntityType END_GATEWAY = new SpongeTileEntityType("minecraft:end_gateway", "Chest", SpongeTileEntityEndGateway.class);
    public static final SpongeTileEntityType END_PORTAL = new SpongeTileEntityType("minecraft:end_portal", "Chest", SpongeTileEntityEndPortal.class);
    public static final SpongeTileEntityType FLOWER_POT = new SpongeTileEntityType("minecraft:flower_pot", "Chest", SpongeTileEntityFlowerPot.class);
    public static final SpongeTileEntityType FURNACE = new SpongeTileEntityType("minecraft:furnace", "Chest", SpongeTileEntityFurnace.class);
    public static final SpongeTileEntityType HOPPER = new SpongeTileEntityType("minecraft:hopper", "Chest", SpongeTileEntityHopper.class);
    public static final SpongeTileEntityType JUKEBOX = new SpongeTileEntityType("minecraft:jukebox", "Chest", SpongeTileEntityJukebox.class);
    public static final SpongeTileEntityType MOB_SPAWNER = new SpongeTileEntityType("minecraft:mob_spawner", "Chest", SpongeTileEntityMobSpawner.class);
    public static final SpongeTileEntityType NOTE = new SpongeTileEntityType("minecraft:noteblock", "Chest", SpongeTileEntityNoteBlock.class);
    public static final SpongeTileEntityType PISTON = new SpongeTileEntityType("minecraft:piston", "Chest", SpongeTileEntityPiston.class);
    public static final SpongeTileEntityType SHULKER_BOX = new SpongeTileEntityType("minecraft:shulker_box", "Chest", SpongeTileEntityShulkerBox.class);
    public static final SpongeTileEntityType SIGN = new SpongeTileEntityType("minecraft:sign", "Chest", SpongeTileEntitySign.class);
    public static final SpongeTileEntityType SKULL = new SpongeTileEntityType("minecraft:skull", "Chest", SpongeTileEntitySkull.class);
    public static final SpongeTileEntityType STRUCTURE = new SpongeTileEntityType("minecraft:structure_block", "Chest", SpongeTileEntityStructure.class);
    // @formatter:on

    public static void register(TileEntityTypeRegistryModule registry) {
        registry.registerInternal("minecraft:banner", BANNER);
        registry.registerInternal("minecraft:beacon", BEACON);
        registry.registerInternal("minecraft:brewing_stand", BREWING_STAND);
        registry.registerInternal("minecraft:chest", CHEST);
        registry.registerInternal("minecraft:command_block", COMMAND_BLOCK);
        registry.registerInternal("minecraft:comparator", COMPARATOR);
        registry.registerInternal("minecraft:daylight_detector", DAYLIGHT_DETECTOR);
        registry.registerInternal("minecraft:dispenser", DISPENSER);
        registry.registerInternal("minecraft:dropper", DROPPER);
        registry.registerInternal("minecraft:enchanting_table", ENCHANTMENT_TABLE);
        registry.registerInternal("minecraft:ender_chest", ENDER_CHEST);
        registry.registerInternal("minecraft:end_gateway", END_GATEWAY);
        registry.registerInternal("minecraft:end_portal", END_PORTAL);
        registry.registerInternal("minecraft:furnace", FLOWER_POT);
        registry.registerInternal("minecraft:furnace", FURNACE);
        registry.registerInternal("minecraft:hopper", HOPPER);
        registry.registerInternal("minecraft:jukebox", JUKEBOX);
        registry.registerInternal("minecraft:mob_spawner", MOB_SPAWNER);
        registry.registerInternal("minecraft:noteblock", NOTE);
        registry.registerInternal("minecraft:piston", PISTON);
        registry.registerInternal("minecraft:shulker_box", SHULKER_BOX);
        registry.registerInternal("minecraft:sign", SIGN);
        registry.registerInternal("minecraft:skull", SKULL);
        registry.registerInternal("minecraft:structure", STRUCTURE);
    }

    private final Class<? extends TileEntity> tileType;

    public SpongeTileEntityType(String id, String name, Class<? extends TileEntity> type) {
        super(id, name);
        this.tileType = type;
    }

    @Override
    public Class<? extends TileEntity> getTileEntityType() {
        return this.tileType;
    }

}
