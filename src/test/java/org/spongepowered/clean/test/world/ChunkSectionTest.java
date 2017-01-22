/*
 * Copyright (c) 2015-2016 VoxelBox <http://engine.thevoxelbox.com>.
 * All Rights Reserved.
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
