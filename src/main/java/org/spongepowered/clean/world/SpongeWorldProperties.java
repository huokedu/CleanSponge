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
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.PortalAgentType;
import org.spongepowered.api.world.SerializationBehavior;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;
import org.spongepowered.api.world.storage.WorldProperties;
import org.spongepowered.clean.world.gen.SpongeGeneratorType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SpongeWorldProperties implements WorldProperties {

    private final String        name;
    private Vector3i            spawn_position = new Vector3i(0, 64, 0);
    private SpongeGeneratorType generator_type = SpongeGeneratorType.DEFAULT;
    private UUID                uuid;

    private boolean             enabled;
    private boolean             loadOnStartup;
    private boolean             keepSpawnLoaded;

    public SpongeWorldProperties(String name) {
        this.name = name;
    }

    @Override
    public boolean isInitialized() {
        throw new UnsupportedOperationException();
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
        return this.loadOnStartup;
    }

    @Override
    public void setLoadOnStartup(boolean state) {
        this.loadOnStartup = state;
    }

    @Override
    public boolean doesKeepSpawnLoaded() {
        return this.keepSpawnLoaded;
    }

    @Override
    public void setKeepSpawnLoaded(boolean state) {
        this.keepSpawnLoaded = state;
    }

    @Override
    public boolean doesGenerateSpawnOnLoad() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGenerateSpawnOnLoad(boolean state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector3i getSpawnPosition() {
        return this.spawn_position;
    }

    @Override
    public void setSpawnPosition(Vector3i position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GeneratorType getGeneratorType() {
        return this.generator_type;
    }

    @Override
    public void setGeneratorType(GeneratorType type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getSeed() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getTotalTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getWorldTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWorldTime(long time) {
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
    public boolean isPVPEnabled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPVPEnabled(boolean enabled) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isRaining() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRaining(boolean state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getRainTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRainTime(int time) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isThundering() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setThundering(boolean state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getThunderTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setThunderTime(int time) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameMode getGameMode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGameMode(GameMode gamemode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean usesMapFeatures() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMapFeaturesEnabled(boolean state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isHardcore() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setHardcore(boolean state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean areCommandsAllowed() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCommandsAllowed(boolean state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Difficulty getDifficulty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean doesGenerateBonusChest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector3d getWorldBorderCenter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWorldBorderCenter(double x, double z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getWorldBorderDiameter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWorldBorderDiameter(double diameter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getWorldBorderTimeRemaining() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWorldBorderTimeRemaining(long time) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getWorldBorderTargetDiameter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWorldBorderTargetDiameter(double diameter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getWorldBorderDamageThreshold() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWorldBorderDamageThreshold(double distance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getWorldBorderDamageAmount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWorldBorderDamageAmount(double damage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getWorldBorderWarningTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWorldBorderWarningTime(int time) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getWorldBorderWarningDistance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWorldBorderWarningDistance(int distance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<String> getGameRule(String gameRule) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, String> getGameRules() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGameRule(String gameRule, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataContainer getAdditionalProperties() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<DataView> getPropertySection(DataQuery path) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPropertySection(DataQuery path, DataView data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<WorldGeneratorModifier> getGeneratorModifiers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGeneratorModifiers(Collection<WorldGeneratorModifier> modifiers) {
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

    @Override
    public void setSerializationBehavior(SerializationBehavior behavior) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getContentVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataContainer toContainer() {
        throw new UnsupportedOperationException();
    }

}
