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
package org.spongepowered.clean.test.world;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.clean.block.SBlockType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;
import org.spongepowered.clean.world.SChunk.ChunkSection;

public class ChunkSectionTest {

    @BeforeClass
    public static void setUp() {
        FixedCatalogRegistryModule<BlockType> blocks = new FixedCatalogRegistryModule<>(BlockType.class, SBlockType::registerTypes);
        blocks.registerDefaults();
    }
    
    @Test
    public void testGetSet() {
        ChunkSection section = new ChunkSection(0);
        BlockState air = BlockTypes.AIR.getDefaultState();
        BlockState stone = BlockTypes.STONE.getDefaultState();
        BlockState grass = BlockTypes.GRASS.getDefaultState();
        section.setBlock(4, 1, 1, stone);
        section.setBlock(5, 1, 1, stone);
        section.setBlock(6, 1, 1, stone);
        assertEquals(stone, section.getBlock(6, 1, 1));
        assertEquals(stone, section.getBlock(4, 1, 1));
        assertEquals(stone, section.getBlock(5, 1, 1));

        assertEquals(air, section.getBlock(3, 1, 1));
        assertEquals(air, section.getBlock(7, 1, 1));

        assertEquals(air, section.getBlock(4, 0, 1));
        assertEquals(air, section.getBlock(5, 1, 0));
        assertEquals(air, section.getBlock(6, 1, 2));
        
        section.setBlock(5, 1, 1, grass);
        
        assertEquals(stone, section.getBlock(4, 1, 1));
        assertEquals(grass, section.getBlock(5, 1, 1));
        assertEquals(stone, section.getBlock(6, 1, 1));
    }
    
}
