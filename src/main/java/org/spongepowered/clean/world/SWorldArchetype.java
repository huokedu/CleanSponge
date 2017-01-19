package org.spongepowered.clean.world;

import java.util.Collection;
import java.util.List;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.PortalAgentType;
import org.spongepowered.api.world.SerializationBehavior;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;
import org.spongepowered.clean.registry.AbstractCatalogType;

import com.google.common.collect.ImmutableList;

public class SWorldArchetype extends AbstractCatalogType implements WorldArchetype {

    private final boolean enabled;
    private final boolean loadsOnStartup;
    private final boolean keepsSpawnLoaded;
    private final boolean generateSpawnOnLoad;
    private final long seed;
    private final GameMode gamemode;
    private final GeneratorType generatorType;
    private final List<WorldGeneratorModifier> modifiers;
    private final DimensionType dimension;
    private final Difficulty difficulty;
    private final boolean mapFeatures;
    private final boolean hardcore;
    private final DataContainer customSettings;
    private final PortalAgentType portalAgent;
    private final boolean pvp;
    private final boolean commandsAllowed;
    private final boolean generateBonusChest;
    private final SerializationBehavior serialization;

    public SWorldArchetype(String id, String name, SWorldArchetypeBuilder builder) {
        super(id, name);
        this.enabled = builder.enabled;
        this.loadsOnStartup = builder.loadsOnStartup;
        this.keepsSpawnLoaded = builder.keepsSpawnLoaded;
        this.generateSpawnOnLoad = builder.generateSpawnOnLoad;
        this.seed = builder.seed;
        this.gamemode = builder.gamemode;
        this.generatorType = builder.generatorType;
        this.modifiers = ImmutableList.copyOf(builder.modifiers);
        this.dimension = builder.dimension;
        this.difficulty = builder.difficulty;
        this.mapFeatures = builder.mapFeatures;
        this.hardcore = builder.hardcore;
        this.customSettings = builder.customSettings;
        this.portalAgent = builder.portalAgent;
        this.pvp = builder.pvp;
        this.commandsAllowed = builder.commandsAllowed;
        this.generateBonusChest = builder.generateBonusChest;
        this.serialization = builder.serialization;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean loadOnStartup() {
        return this.loadsOnStartup;
    }

    @Override
    public boolean doesKeepSpawnLoaded() {
        return this.keepsSpawnLoaded;
    }

    @Override
    public boolean doesGenerateSpawnOnLoad() {
        return this.generateSpawnOnLoad;
    }

    @Override
    public long getSeed() {
        return this.seed;
    }

    @Override
    public GameMode getGameMode() {
        return this.gamemode;
    }

    @Override
    public GeneratorType getGeneratorType() {
        return this.generatorType;
    }

    @Override
    public Collection<WorldGeneratorModifier> getGeneratorModifiers() {
        return this.modifiers;
    }

    @Override
    public boolean usesMapFeatures() {
        return this.mapFeatures;
    }

    @Override
    public boolean isHardcore() {
        return this.hardcore;
    }

    @Override
    public boolean areCommandsAllowed() {
        return this.commandsAllowed;
    }

    @Override
    public boolean doesGenerateBonusChest() {
        return this.generateBonusChest;
    }

    @Override
    public DimensionType getDimensionType() {
        return this.dimension;
    }

    @Override
    public PortalAgentType getPortalAgentType() {
        return this.portalAgent;
    }

    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    @Override
    public boolean isPVPEnabled() {
        return this.pvp;
    }

    @Override
    public DataContainer getGeneratorSettings() {
        return this.customSettings;
    }

    @Override
    public SerializationBehavior getSerializationBehavior() {
        return this.serialization;
    }

}
