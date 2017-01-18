package org.spongepowered.clean.world;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

public class SWorldProperties implements WorldProperties {

    private static final int CURRENT_CONTENT_VERSION = 0;
    
    public SWorldProperties() {

    }

    @Override
    public int getContentVersion() {
        return CURRENT_CONTENT_VERSION;
    }

    @Override
    public DataContainer toContainer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isInitialized() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getWorldName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UUID getUniqueId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setEnabled(boolean state) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean loadOnStartup() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setLoadOnStartup(boolean state) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean doesKeepSpawnLoaded() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setKeepSpawnLoaded(boolean state) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean doesGenerateSpawnOnLoad() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setGenerateSpawnOnLoad(boolean state) {
        // TODO Auto-generated method stub

    }

    @Override
    public Vector3i getSpawnPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSpawnPosition(Vector3i position) {
        // TODO Auto-generated method stub

    }

    @Override
    public GeneratorType getGeneratorType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setGeneratorType(GeneratorType type) {
        // TODO Auto-generated method stub

    }

    @Override
    public long getSeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getTotalTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getWorldTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWorldTime(long time) {
        // TODO Auto-generated method stub

    }

    @Override
    public DimensionType getDimensionType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PortalAgentType getPortalAgentType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isPVPEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setPVPEnabled(boolean enabled) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isRaining() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setRaining(boolean state) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getRainTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setRainTime(int time) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isThundering() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setThundering(boolean state) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getThunderTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setThunderTime(int time) {
        // TODO Auto-generated method stub

    }

    @Override
    public GameMode getGameMode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setGameMode(GameMode gamemode) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean usesMapFeatures() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setMapFeaturesEnabled(boolean state) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isHardcore() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setHardcore(boolean state) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean areCommandsAllowed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setCommandsAllowed(boolean state) {
        // TODO Auto-generated method stub

    }

    @Override
    public Difficulty getDifficulty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean doesGenerateBonusChest() {
        // TODO Auto-generated method stub
        return false;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setGeneratorModifiers(Collection<WorldGeneratorModifier> modifiers) {
        // TODO Auto-generated method stub

    }

    @Override
    public DataContainer getGeneratorSettings() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SerializationBehavior getSerializationBehavior() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSerializationBehavior(SerializationBehavior behavior) {
        // TODO Auto-generated method stub

    }

}
