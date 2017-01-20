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

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.PortalAgentType;
import org.spongepowered.api.world.SerializationBehavior;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;
import org.spongepowered.api.world.storage.WorldProperties;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.data.DataQueries;
import org.spongepowered.clean.entity.player.SGameMode;
import org.spongepowered.clean.world.storage.SaveHandler;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SWorldProperties implements WorldProperties {

    private final SaveHandler save;
    private final String name;
    private UUID uuid;

    private boolean enabled;
    private boolean loadsOnStartup;
    private boolean keepsSpawnLoaded;
    private boolean generateSpawnOnLoad;
    private long seed;
    private GameMode gamemode;
    private GeneratorType generatorType;
    private List<WorldGeneratorModifier> modifiers;
    private DimensionType dimension;
    private Difficulty difficulty;
    private boolean mapFeatures;
    private boolean hardcore;
    private DataContainer customSettings;
    private PortalAgentType portalAgent;
    private boolean pvp;
    private boolean commandsAllowed;
    private boolean generateBonusChest;
    private SerializationBehavior serialization;

    private int version;
    private boolean initialized;
    private long lastload;
    private long time;
    private long daytime;
    private Vector3i worldSpawn;
    private boolean raining;
    private int raintime;
    private boolean thundering;
    private int thundertime;
    private int clearWeatherTime;

    public SWorldProperties(SaveHandler save, DataView levelDat) {
        this.save = save;
        this.name = save.getWorldName();

        this.version = levelDat.getInt(DataQueries.LEVEL_VERSION).orElse(19133);
        this.initialized = levelDat.getBoolean(DataQueries.LEVEL_INITIALIZED).orElse(true);
        String generatorName = levelDat.getString(DataQueries.LEVEL_GENERATOR_NAME).orElse("default");
        this.generatorType = Sponge.getRegistry().getType(GeneratorType.class, generatorName).get();
        // TODO int generatorVersion =
        // levelDat.getInt(DataQueries.LEVEL_GENERATOR_VERSION).orElse(0);
        // TODO String generatorSettings =
        // levelDat.getString(DataQueries.LEVEL_GENERATOR_OPTIONS).orElse("");
        this.seed = levelDat.getLong(DataQueries.LEVEL_RANDOM_SEED).get();
        this.mapFeatures = levelDat.getBoolean(DataQueries.LEVEL_MAP_FEATURES).orElse(true);
        this.lastload = levelDat.getLong(DataQueries.LEVEL_LAST_PLAYED).orElse(System.currentTimeMillis());
        // TODO size on disk?
        this.commandsAllowed = levelDat.getBoolean(DataQueries.LEVEL_ALLOW_COMMANDS).orElse(true);
        this.hardcore = levelDat.getBoolean(DataQueries.LEVEL_HARDCORE).orElse(false);
        int gametype = levelDat.getInt(DataQueries.LEVEL_GAME_TYPE).orElse(0);
        this.gamemode = SGameMode.getById(gametype);
        byte difficultyId = levelDat.getByte(DataQueries.LEVEL_DIFFICULTY).orElse((byte) 2);
        this.difficulty = SDifficulty.getById(difficultyId);
//         TODO boolean difficultyLocked = levelDat.getBoolean(DataQueries.LEVEL_DIFFICULTY_LOCKED).orElse(false);
        this.time = levelDat.getLong(DataQueries.LEVEL_TIME).orElse(0L);
        this.daytime = levelDat.getLong(DataQueries.LEVEL_DAY_TIME).orElse(0L);
        int spawnx = levelDat.getInt(DataQueries.LEVEL_SPAWN_X).orElse(0);
        int spawny = levelDat.getInt(DataQueries.LEVEL_SPAWN_Y).orElse(64);
        int spawnz = levelDat.getInt(DataQueries.LEVEL_SPAWN_Z).orElse(0);
        this.worldSpawn = new Vector3i(spawnx, spawny, spawnz);
        // TODO world border props
        this.raining = levelDat.getBoolean(DataQueries.LEVEL_RAINING).orElse(false);
        this.raintime = levelDat.getInt(DataQueries.LEVEL_RAIN_TIME).orElse(0);
        this.thundering = levelDat.getBoolean(DataQueries.LEVEL_THUNDERING).orElse(false);
        this.thundertime = levelDat.getInt(DataQueries.LEVEL_THUNDER_TIME).orElse(0);
        this.clearWeatherTime = levelDat.getInt(DataQueries.LEVEL_CLEAR_WEATHER_TIME).orElse(1500);

        // TODO gamerules
        // TODO additional version info

        // TODO load sponge level dat

        // TODO load from sponge conf
        this.enabled = true;
        // TODO end has extra data that needs to be loaded here

    }

    public SWorldProperties(String name, WorldArchetype archetype) {
        File dir = SGame.game.getWorldsDir().resolve(name).toFile();
        this.save = new SaveHandler(dir);
        this.name = name;

        this.enabled = archetype.isEnabled();
        this.loadsOnStartup = archetype.loadOnStartup();
        this.keepsSpawnLoaded = archetype.doesKeepSpawnLoaded();
        this.generateSpawnOnLoad = archetype.doesGenerateSpawnOnLoad();
        this.seed = archetype.getSeed();
        this.gamemode = archetype.getGameMode();
        this.generatorType = archetype.getGeneratorType();
        this.modifiers = ImmutableList.copyOf(archetype.getGeneratorModifiers());
        this.dimension = archetype.getDimensionType();
        this.difficulty = archetype.getDifficulty();
        this.mapFeatures = archetype.usesMapFeatures();
        this.hardcore = archetype.isHardcore();
        this.customSettings = archetype.getGeneratorSettings();
        this.portalAgent = archetype.getPortalAgentType();
        this.pvp = archetype.isPVPEnabled();
        this.commandsAllowed = archetype.areCommandsAllowed();
        this.generateBonusChest = archetype.doesGenerateBonusChest();
        this.serialization = archetype.getSerializationBehavior();

        this.version = 19133;
        this.initialized = true;
        this.lastload = System.currentTimeMillis();
        this.time = 0;
        this.daytime = 0;
        this.raining = false;
        this.raintime = 0;
        this.thundering = false;
        this.thundertime = 0;
        this.clearWeatherTime = 1500;
        
        this.uuid = UUID.randomUUID();
    }

    public SaveHandler getSaveHandler() {
        return this.save;
    }

    @Override
    public boolean isInitialized() {
        return this.initialized;
    }

    @Override
    public String getWorldName() {
        return this.name;
    }

    @Override
    public UUID getUniqueId() {
        return this.uuid;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean state) {
        this.enabled = state;
    }

    @Override
    public boolean loadOnStartup() {
        return this.loadsOnStartup;
    }

    @Override
    public void setLoadOnStartup(boolean state) {
        this.loadsOnStartup = state;
    }

    @Override
    public boolean doesKeepSpawnLoaded() {
        return this.keepsSpawnLoaded;
    }

    @Override
    public void setKeepSpawnLoaded(boolean state) {
        this.keepsSpawnLoaded = state;
    }

    @Override
    public boolean doesGenerateSpawnOnLoad() {
        return this.generateSpawnOnLoad;
    }

    @Override
    public void setGenerateSpawnOnLoad(boolean state) {
        this.generateSpawnOnLoad = state;
    }

    @Override
    public Vector3i getSpawnPosition() {
        return this.worldSpawn;
    }

    @Override
    public void setSpawnPosition(Vector3i position) {
        this.worldSpawn = position;
    }

    @Override
    public GeneratorType getGeneratorType() {
        return this.generatorType;
    }

    @Override
    public void setGeneratorType(GeneratorType type) {
        this.generatorType = type;
    }

    @Override
    public long getSeed() {
        return this.seed;
    }

    @Override
    public long getTotalTime() {
        return this.time;
    }

    @Override
    public long getWorldTime() {
        return this.daytime;
    }

    @Override
    public void setWorldTime(long time) {
        this.daytime = time;
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
    public boolean isPVPEnabled() {
        return this.pvp;
    }

    @Override
    public void setPVPEnabled(boolean enabled) {
        this.pvp = enabled;
    }

    @Override
    public boolean isRaining() {
        return this.raining;
    }

    @Override
    public void setRaining(boolean state) {
        this.raining = state;
    }

    @Override
    public int getRainTime() {
        return this.raintime;
    }

    @Override
    public void setRainTime(int time) {
        this.raintime = time;
    }

    @Override
    public boolean isThundering() {
        return this.thundering;
    }

    @Override
    public void setThundering(boolean state) {
        this.thundering = state;
    }

    @Override
    public int getThunderTime() {
        return this.thundertime;
    }

    @Override
    public void setThunderTime(int time) {
        this.thundertime = time;
    }

    @Override
    public GameMode getGameMode() {
        return this.gamemode;
    }

    @Override
    public void setGameMode(GameMode gamemode) {
        this.gamemode = gamemode;
    }

    @Override
    public boolean usesMapFeatures() {
        return this.mapFeatures;
    }

    @Override
    public void setMapFeaturesEnabled(boolean state) {
        this.mapFeatures = state;
    }

    @Override
    public boolean isHardcore() {
        return this.hardcore;
    }

    @Override
    public void setHardcore(boolean state) {
        this.hardcore = state;
    }

    @Override
    public boolean areCommandsAllowed() {
        return this.commandsAllowed;
    }

    @Override
    public void setCommandsAllowed(boolean state) {
        this.commandsAllowed = state;
    }

    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean doesGenerateBonusChest() {
        return this.generateBonusChest;
    }

    @Override
    public Vector3d getWorldBorderCenter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setWorldBorderCenter(double x, double z) {
        // TODO Auto-generated method stub

    }

    @Override
    public double getWorldBorderDiameter() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWorldBorderDiameter(double diameter) {
        // TODO Auto-generated method stub

    }

    @Override
    public long getWorldBorderTimeRemaining() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWorldBorderTimeRemaining(long time) {
        // TODO Auto-generated method stub

    }

    @Override
    public double getWorldBorderTargetDiameter() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWorldBorderTargetDiameter(double diameter) {
        // TODO Auto-generated method stub

    }

    @Override
    public double getWorldBorderDamageThreshold() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWorldBorderDamageThreshold(double distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public double getWorldBorderDamageAmount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWorldBorderDamageAmount(double damage) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getWorldBorderWarningTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWorldBorderWarningTime(int time) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getWorldBorderWarningDistance() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWorldBorderWarningDistance(int distance) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<String> getGameRule(String gameRule) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, String> getGameRules() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setGameRule(String gameRule, String value) {
        // TODO Auto-generated method stub

    }

    @Override
    public DataContainer getAdditionalProperties() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<DataView> getPropertySection(DataQuery path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPropertySection(DataQuery path, DataView data) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<WorldGeneratorModifier> getGeneratorModifiers() {
        return this.modifiers;
    }

    @Override
    public void setGeneratorModifiers(Collection<WorldGeneratorModifier> modifiers) {
        this.modifiers = Lists.newArrayList(modifiers);
    }

    @Override
    public DataContainer getGeneratorSettings() {
        return this.customSettings;
    }

    @Override
    public SerializationBehavior getSerializationBehavior() {
        return this.serialization;
    }

    @Override
    public void setSerializationBehavior(SerializationBehavior behavior) {
        this.serialization = behavior;
    }

    @Override
    public int getContentVersion() {
        return SaveHandler.CURRENT_CONTENT_VERSION;
    }

    @Override
    public DataContainer toContainer() {
        // TODO Auto-generated method stub
        return null;
    }

}
