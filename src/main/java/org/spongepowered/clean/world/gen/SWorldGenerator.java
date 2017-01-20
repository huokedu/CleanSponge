/*
 * Copyright (c) 2015-2016 VoxelBox <http://engine.thevoxelbox.com>.
 * All Rights Reserved.
 */
package org.spongepowered.clean.world.gen;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.spongepowered.api.world.biome.BiomeGenerationSettings;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.gen.BiomeGenerator;
import org.spongepowered.api.world.gen.GenerationPopulator;
import org.spongepowered.api.world.gen.Populator;
import org.spongepowered.api.world.gen.WorldGenerator;
import org.spongepowered.clean.world.SChunk;
import org.spongepowered.clean.world.SWorld;
import org.spongepowered.clean.world.buffer.SMutableBiomeVolume;

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

    public SWorldGenerator(SWorld world) {
        this.world = world;
    }

    public SChunk generateChunk(int x, int z) {
        SChunk chunk = new SChunk(this.world, x, z);
        chunk.setPhysics(false);
        chunk.setLighting(false);
        SMutableBiomeVolume biomes = new SMutableBiomeVolume(new Vector3i(x, 0, z), SChunk.BIOME_SIZE);
        this.biomeGen.generateBiomes(biomes);
        ImmutableBiomeVolume ibiomes = biomes.getImmutableBiomeCopy();
        this.basePopulator.populate(this.world, chunk, ibiomes);

        for (GenerationPopulator genpop : this.genpopulators) {
            genpop.populate(this.world, chunk, ibiomes);
        }
        return chunk;
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
        }
        return override;
    }

}
