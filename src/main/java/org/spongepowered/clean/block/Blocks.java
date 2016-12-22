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
package org.spongepowered.clean.block;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.clean.registry.modules.block.BlockTypeRegistryModule;

public class Blocks {

    public static SpongeBlockType air;
    public static SpongeBlockType stone;
    public static SpongeBlockType grass;
    public static SpongeBlockType dirt;
    public static SpongeBlockType cobblestone;
    public static SpongeBlockType planks;
    public static SpongeBlockType saplings;
    public static SpongeBlockType bedrock;
    public static SpongeBlockType flowing_water;
    public static SpongeBlockType water;
    public static SpongeBlockType flowing_lava;
    public static SpongeBlockType lava;
    public static SpongeBlockType sand;
    public static SpongeBlockType gravel;
    public static SpongeBlockType gold_ore;
    public static SpongeBlockType iron_ore;
    public static SpongeBlockType coal_ore;
    public static SpongeBlockType log;
    public static SpongeBlockType log2;
    public static SpongeBlockType leaves;
    public static SpongeBlockType leaves2;
    public static SpongeBlockType sponge;
    public static SpongeBlockType glass;
    public static SpongeBlockType lapis_ore;
    public static SpongeBlockType lapis_block;
    public static SpongeBlockType dispenser;
    public static SpongeBlockType sandstone;
    public static SpongeBlockType noteblock;
    public static SpongeBlockType bed;
    public static SpongeBlockType golden_rail;
    public static SpongeBlockType detector_rail;
    public static SpongeBlockType sticky_piston;
    public static SpongeBlockType web;
    public static SpongeBlockType tallgrass;
    public static SpongeBlockType deadbush;
    public static SpongeBlockType piston;
    public static SpongeBlockType piston_head;
    public static SpongeBlockType wool;
    public static SpongeBlockType piston_extension;
    public static SpongeBlockType yellow_flower;
    public static SpongeBlockType red_flower;
    public static SpongeBlockType brown_mushroom;
    public static SpongeBlockType red_mushroom;
    public static SpongeBlockType gold_block;
    public static SpongeBlockType iron_block;
    public static SpongeBlockType double_stone_slab;
    public static SpongeBlockType stone_slab;
    public static SpongeBlockType brick_block;
    public static SpongeBlockType tnt;
    public static SpongeBlockType bookshelf;
    public static SpongeBlockType mossy_cobblestone;
    public static SpongeBlockType obsidian;
    public static SpongeBlockType torch;
    public static SpongeBlockType fire;
    public static SpongeBlockType mobspawner;
    public static SpongeBlockType oak_stairs;
    public static SpongeBlockType chest;
    public static SpongeBlockType redstone_wire;
    public static SpongeBlockType diamond_ore;
    public static SpongeBlockType diamond_block;
    public static SpongeBlockType crafting_table;
    public static SpongeBlockType wheat;
    public static SpongeBlockType farmland;
    public static SpongeBlockType furnace;
    public static SpongeBlockType lit_furnace;

    public static void register(BlockTypeRegistryModule registry) {
        registry.registerInternal("minecraft:air", air = new BlockAir());
        registry.registerInternal("minecraft:stone", stone = new BlockStone());
        
        for(BlockType block: registry.getAll()) {
            ((SpongeBlockType)block).registerStates();
        }
    }

}
