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

import java.util.List;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.registry.CatalogTypeAlreadyRegisteredException;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.DimensionTypes;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.GeneratorTypes;
import org.spongepowered.api.world.PortalAgentType;
import org.spongepowered.api.world.PortalAgentTypes;
import org.spongepowered.api.world.SerializationBehavior;
import org.spongepowered.api.world.SerializationBehaviors;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.WorldArchetype.Builder;
import org.spongepowered.api.world.difficulty.Difficulties;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;
import org.spongepowered.api.world.storage.WorldProperties;

import com.google.common.collect.Lists;

public class SWorldArchetypeBuilder implements WorldArchetype.Builder {

    boolean enabled;
    boolean loadsOnStartup;
    boolean keepsSpawnLoaded;
    boolean generateSpawnOnLoad;
    long seed;
    GameMode gamemode;
    GeneratorType generatorType;
    final List<WorldGeneratorModifier> modifiers = Lists.newArrayList();
    DimensionType dimension;
    Difficulty difficulty;
    boolean mapFeatures;
    boolean hardcore;
    DataContainer customSettings;
    PortalAgentType portalAgent;
    boolean pvp;
    boolean commandsAllowed;
    boolean generateBonusChest;
    SerializationBehavior serialization;

    public SWorldArchetypeBuilder() {
        reset();
    }

    @Override
    public Builder enabled(boolean state) {
        this.enabled = state;
        return this;
    }

    @Override
    public Builder loadsOnStartup(boolean state) {
        this.loadsOnStartup = state;
        return this;
    }

    @Override
    public Builder keepsSpawnLoaded(boolean state) {
        this.keepsSpawnLoaded = state;
        return this;
    }

    @Override
    public Builder generateSpawnOnLoad(boolean state) {
        this.generateSpawnOnLoad = state;
        return this;
    }

    @Override
    public Builder seed(long seed) {
        this.seed = seed;
        return this;
    }

    @Override
    public Builder gameMode(GameMode gameMode) {
        this.gamemode = gameMode;
        return this;
    }

    @Override
    public Builder generator(GeneratorType type) {
        this.generatorType = type;
        return this;
    }

    @Override
    public Builder generatorModifiers(WorldGeneratorModifier... modifier) {
        this.modifiers.clear();
        for (WorldGeneratorModifier mod : modifier) {
            this.modifiers.add(mod);
        }
        return this;
    }

    @Override
    public Builder dimension(DimensionType type) {
        this.dimension = type;
        return this;
    }

    @Override
    public Builder difficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    @Override
    public Builder usesMapFeatures(boolean state) {
        this.mapFeatures = state;
        return this;
    }

    @Override
    public Builder hardcore(boolean state) {
        this.hardcore = state;
        return this;
    }

    @Override
    public Builder generatorSettings(DataContainer settings) {
        this.customSettings = settings;
        return this;
    }

    @Override
    public Builder portalAgent(PortalAgentType type) {
        this.portalAgent = type;
        return this;
    }

    @Override
    public Builder pvp(boolean state) {
        this.pvp = state;
        return this;
    }

    @Override
    public Builder commandsAllowed(boolean state) {
        this.commandsAllowed = state;
        return this;
    }

    @Override
    public Builder generateBonusChest(boolean state) {
        this.generateBonusChest = state;
        return this;
    }

    @Override
    public Builder serializationBehavior(SerializationBehavior behavior) {
        this.serialization = behavior;
        return this;
    }

    @Override
    public Builder from(WorldProperties value) {
        this.enabled = value.isEnabled();
        this.loadsOnStartup = value.loadOnStartup();
        this.keepsSpawnLoaded = value.doesKeepSpawnLoaded();
        this.generateSpawnOnLoad = value.doesGenerateSpawnOnLoad();
        this.seed = value.getSeed();
        this.gamemode = value.getGameMode();
        this.generatorType = value.getGeneratorType();
        this.modifiers.clear();
        this.modifiers.addAll(value.getGeneratorModifiers());
        this.dimension = value.getDimensionType();
        this.difficulty = value.getDifficulty();
        this.mapFeatures = value.usesMapFeatures();
        this.hardcore = value.isHardcore();
        this.customSettings = value.getGeneratorSettings();
        this.portalAgent = value.getPortalAgentType();
        this.pvp = value.isPVPEnabled();
        this.commandsAllowed = value.areCommandsAllowed();
        this.generateBonusChest = value.doesGenerateBonusChest();
        this.serialization = value.getSerializationBehavior();
        return this;
    }

    @Override
    public Builder from(WorldArchetype value) {
        this.enabled = value.isEnabled();
        this.loadsOnStartup = value.loadOnStartup();
        this.keepsSpawnLoaded = value.doesKeepSpawnLoaded();
        this.generateSpawnOnLoad = value.doesGenerateSpawnOnLoad();
        this.seed = value.getSeed();
        this.gamemode = value.getGameMode();
        this.generatorType = value.getGeneratorType();
        this.modifiers.clear();
        this.modifiers.addAll(value.getGeneratorModifiers());
        this.dimension = value.getDimensionType();
        this.difficulty = value.getDifficulty();
        this.mapFeatures = value.usesMapFeatures();
        this.hardcore = value.isHardcore();
        this.customSettings = value.getGeneratorSettings();
        this.portalAgent = value.getPortalAgentType();
        this.pvp = value.isPVPEnabled();
        this.commandsAllowed = value.areCommandsAllowed();
        this.generateBonusChest = value.doesGenerateBonusChest();
        this.serialization = value.getSerializationBehavior();
        return this;
    }

    @Override
    public Builder reset() {
        this.enabled = true;
        this.loadsOnStartup = true;
        this.keepsSpawnLoaded = true;
        this.generateSpawnOnLoad = true;
        this.seed = 0;
        this.gamemode = GameModes.SURVIVAL;
        this.generatorType = GeneratorTypes.DEFAULT;
        this.modifiers.clear();
        this.dimension = DimensionTypes.OVERWORLD;
        this.difficulty = Difficulties.NORMAL;
        this.mapFeatures = true;
        this.hardcore = false;
        this.customSettings = null;
        this.portalAgent = PortalAgentTypes.DEFAULT;
        this.pvp = true;
        this.commandsAllowed = true;
        this.generateBonusChest = false;
        this.serialization = SerializationBehaviors.AUTOMATIC;
        return this;
    }

    @Override
    public WorldArchetype build(String id, String name) throws IllegalArgumentException, CatalogTypeAlreadyRegisteredException {
        SWorldArchetype archetype = new SWorldArchetype(id, name, this);
        return archetype;
    }

}
