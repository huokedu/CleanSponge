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
package org.spongepowered.clean.world;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.PortalAgentType;
import org.spongepowered.api.world.SerializationBehavior;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;

import java.util.Collection;

public class SpongeWorldArchetype implements WorldArchetype {

    private final String id;

    public SpongeWorldArchetype(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean loadOnStartup() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean doesKeepSpawnLoaded() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean doesGenerateSpawnOnLoad() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getSeed() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameMode getGameMode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GeneratorType getGeneratorType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<WorldGeneratorModifier> getGeneratorModifiers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean usesMapFeatures() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isHardcore() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean areCommandsAllowed() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean doesGenerateBonusChest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DimensionType getDimensionType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PortalAgentType getPortalAgentType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Difficulty getDifficulty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPVPEnabled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataContainer getGeneratorSettings() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SerializationBehavior getSerializationBehavior() {
        throw new UnsupportedOperationException();
    }

}
