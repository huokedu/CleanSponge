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

import org.spongepowered.api.world.biome.BiomeGenerationSettings;
import org.spongepowered.api.world.biome.BiomeGenerationSettings.Builder;
import org.spongepowered.api.world.biome.GroundCoverLayer;
import org.spongepowered.api.world.gen.GenerationPopulator;
import org.spongepowered.api.world.gen.Populator;

public class SBiomeGenerationSettingsBuilder implements BiomeGenerationSettings.Builder {

    float min;
    float max;
    final List<Populator> populators = new ArrayList<>();
    final List<GenerationPopulator> genpop = new ArrayList<>();
    final List<GroundCoverLayer> groundCover = new ArrayList<>();

    public SBiomeGenerationSettingsBuilder() {
        reset();
    }

    @Override
    public Builder minHeight(float height) {
        this.min = height;
        return this;
    }

    @Override
    public Builder maxHeight(float height) {
        this.max = height;
        return this;
    }

    @Override
    public Builder groundCover(GroundCoverLayer... coverLayers) {
        this.groundCover.clear();
        for (int i = 0; i < coverLayers.length; i++) {
            this.groundCover.add(coverLayers[i]);
        }
        return this;
    }

    @Override
    public Builder groundCover(Iterable<GroundCoverLayer> coverLayers) {
        this.groundCover.clear();
        for (GroundCoverLayer l : coverLayers) {
            this.groundCover.add(l);
        }
        return this;
    }

    @Override
    public Builder generationPopulators(GenerationPopulator... genpop) {
        this.genpop.clear();
        for (GenerationPopulator g : genpop) {
            this.genpop.add(g);
        }
        return this;
    }

    @Override
    public Builder generationPopulators(Iterable<GenerationPopulator> genpop) {
        this.genpop.clear();
        for (GenerationPopulator g : genpop) {
            this.genpop.add(g);
        }
        return this;
    }

    @Override
    public Builder populators(Populator... populators) {
        this.populators.clear();
        for (Populator p : populators) {
            this.populators.add(p);
        }
        return this;
    }

    @Override
    public Builder populators(Iterable<Populator> populators) {
        this.populators.clear();
        for (Populator p : populators) {
            this.populators.add(p);
        }
        return this;
    }

    @Override
    public Builder from(BiomeGenerationSettings value) {
        reset();
        this.min = value.getMinHeight();
        this.max = value.getMaxHeight();
        this.populators.addAll(value.getPopulators());
        this.genpop.addAll(value.getGenerationPopulators());
        this.groundCover.addAll(value.getGroundCoverLayers());
        return this;
    }

    @Override
    public Builder reset() {
        this.min = 0;
        this.max = 1;
        this.populators.clear();
        this.genpop.clear();
        this.groundCover.clear();
        return this;
    }

    @Override
    public BiomeGenerationSettings build() throws IllegalStateException {
        return new SBiomeGenerationSettings(this);
    }

}
