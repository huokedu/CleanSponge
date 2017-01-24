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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.spongepowered.api.world.biome.BiomeGenerationSettings;
import org.spongepowered.api.world.biome.GroundCoverLayer;
import org.spongepowered.api.world.gen.GenerationPopulator;
import org.spongepowered.api.world.gen.Populator;

public class SBiomeGenerationSettings implements BiomeGenerationSettings {

    private float min;
    private float max;
    private final List<Populator> populators = new ArrayList<>();
    private final List<GenerationPopulator> genpop = new ArrayList<>();
    private final List<GroundCoverLayer> groundCover = new ArrayList<>();

    public SBiomeGenerationSettings(SBiomeGenerationSettingsBuilder builder) {
        this.min = builder.min;
    }

    @Override
    public float getMinHeight() {
        return this.min;
    }

    @Override
    public void setMinHeight(float height) {
        this.min = height;
    }

    @Override
    public float getMaxHeight() {
        return this.max;
    }

    @Override
    public void setMaxHeight(float height) {
        this.max = height;
    }

    @Override
    public List<GroundCoverLayer> getGroundCoverLayers() {
        return this.groundCover;
    }

    @Override
    public List<GenerationPopulator> getGenerationPopulators() {
        return this.genpop;
    }

    @Override
    public List<GenerationPopulator> getGenerationPopulators(Class<? extends GenerationPopulator> type) {
        return this.genpop.stream().filter((g) -> type.isInstance(g)).collect(Collectors.toList());
    }

    @Override
    public List<Populator> getPopulators() {
        return this.populators;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Populator> List<T> getPopulators(Class<T> type) {
        return (List<T>) this.populators.stream().filter((g) -> type.isInstance(g)).collect(Collectors.toList());
    }

    @Override
    public BiomeGenerationSettings copy() {
        return new SBiomeGenerationSettingsBuilder().from(this).build();
    }

}
