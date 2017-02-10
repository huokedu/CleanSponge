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
package org.spongepowered.clean.world.gen;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.biome.BiomeGenerationSettings;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.biome.GroundCoverLayer;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.BiomeGenerator;
import org.spongepowered.api.world.gen.GenerationPopulator;
import org.spongepowered.api.world.gen.Populator;
import org.spongepowered.api.world.gen.WorldGenerator;
import org.spongepowered.clean.world.SChunk;
import org.spongepowered.clean.world.SWorld;
import org.spongepowered.clean.world.buffer.SMutableBiomeVolume;
import org.spongepowered.clean.world.buffer.SMutableBlockVolume;
import org.spongepowered.clean.world.gen.base.OverworldGenerator;
import org.spongepowered.clean.world.palette.GlobalPalette;

import com.flowpowered.math.vector.Vector3i;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SWorldGenerator implements WorldGenerator {

    private final SWorld world;
    private final List<GenerationPopulator> genpopulators = Lists.newArrayList();
    private final List<Populator> populators = Lists.newArrayList();
    private final Map<BiomeType, BiomeGenerationSettings> biomeOverrides = Maps.newHashMap();
    private GenerationPopulator basePopulator;
    private BiomeGenerator biomeGen;

    public SWorldGenerator(SWorld world, BiomeGenerator biomes, GenerationPopulator base) {
        this.world = world;
        this.biomeGen = biomes;
        this.basePopulator = base;
    }

    public SChunk generateChunk(int cx, int cz) {
        MutableBlockVolume blocks = new SMutableBlockVolume(GlobalPalette.instance, new Vector3i(cx * 16, 0, cz * 16), SChunk.CHUNK_SIZE);
        SMutableBiomeVolume biomes = new SMutableBiomeVolume(new Vector3i(cx * 16 - 4, 0, cz * 16 - 4), new Vector3i(24, 1, 24), true);
        this.biomeGen.generateBiomes(biomes);
        ImmutableBiomeVolume ibiomes = biomes.getImmutableBiomeCopy();
        if(this.basePopulator instanceof OverworldGenerator) {
            ((OverworldGenerator) this.basePopulator).setBiomes(biomes);
        }
        this.basePopulator.populate(this.world, blocks, ibiomes);

        for (GenerationPopulator genpop : this.genpopulators) {
            genpop.populate(this.world, blocks, ibiomes);
        }

        Random rand = new Random((((cx & 0xFFFFFFFFL) << 32) | (cz & 0xFFFFFFFFL)) ^ this.world.getProperties().getSeed());

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                BiomeGenerationSettings settings = getBiomeSettings(biomes.getBiome(cx * 16 + x, 0, cz * 16 + z));
                groundcover: for (int y = 255; y >= 0; y--) {
                    if (blocks.getBlock(cx * 16 + x, y, cz * 16 + z).getType() == BlockTypes.STONE) {
                        for (GroundCoverLayer gcl : settings.getGroundCoverLayers()) {
                            int depth = gcl.getDepth().getFlooredAmount(rand, 0.0);
                            for (int y0 = y; y >= y0 - depth; y--) {
                                blocks.setBlock(cx * 16 + x, y0, cz * 16 + z, gcl.getBlockState().apply(0.0), null);
                            }
                        }
                        break groundcover;
                    }
                }
            }
        }
        return new SChunk(this.world, cx, cz, blocks, biomes);
    }

    public void populateChunk(SChunk chunk) {

        // TODO population

        chunk.setPhysics(true);
        chunk.setLighting(true);
    }

    @Override
    public GenerationPopulator getBaseGenerationPopulator() {
        return this.basePopulator;
    }

    @Override
    public void setBaseGenerationPopulator(GenerationPopulator generator) {
        this.basePopulator = generator;
    }

    @Override
    public List<GenerationPopulator> getGenerationPopulators() {
        return this.genpopulators;
    }

    @Override
    public List<GenerationPopulator> getGenerationPopulators(Class<? extends GenerationPopulator> type) {
        return this.genpopulators.stream().filter((p) -> type.isInstance(p)).collect(Collectors.toList());
    }

    @Override
    public List<Populator> getPopulators() {
        return this.populators;
    }

    @Override
    public List<Populator> getPopulators(Class<? extends Populator> type) {
        return this.populators.stream().filter((p) -> type.isInstance(p)).collect(Collectors.toList());
    }

    @Override
    public BiomeGenerator getBiomeGenerator() {
        return this.biomeGen;
    }

    @Override
    public void setBiomeGenerator(BiomeGenerator biomeGenerator) {
        this.biomeGen = biomeGenerator;
    }

    @Override
    public BiomeGenerationSettings getBiomeSettings(BiomeType type) {
        BiomeGenerationSettings override = this.biomeOverrides.get(type);
        if (override == null) {
            override = type.createDefaultGenerationSettings(this.world);
            this.biomeOverrides.put(type, override);
        }
        return override;
    }

}
