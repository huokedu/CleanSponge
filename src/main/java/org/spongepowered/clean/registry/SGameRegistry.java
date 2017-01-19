package org.spongepowered.clean.registry;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.value.ValueFactory;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.ai.task.AITaskType;
import org.spongepowered.api.entity.ai.task.AbstractAITask;
import org.spongepowered.api.entity.living.Agent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.merchant.VillagerRegistry;
import org.spongepowered.api.item.recipe.RecipeRegistry;
import org.spongepowered.api.network.status.Favicon;
import org.spongepowered.api.registry.CatalogRegistryModule;
import org.spongepowered.api.registry.CatalogTypeAlreadyRegisteredException;
import org.spongepowered.api.registry.RegistryModule;
import org.spongepowered.api.registry.RegistryModuleAlreadyRegisteredException;
import org.spongepowered.api.resourcepack.ResourcePack;
import org.spongepowered.api.scoreboard.displayslot.DisplaySlot;
import org.spongepowered.api.statistic.BlockStatistic;
import org.spongepowered.api.statistic.EntityStatistic;
import org.spongepowered.api.statistic.ItemStatistic;
import org.spongepowered.api.statistic.StatisticType;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.selector.SelectorFactory;
import org.spongepowered.api.text.serializer.TextSerializerFactory;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.util.rotation.Rotation;
import org.spongepowered.api.world.extent.ExtentBufferFactory;
import org.spongepowered.clean.SGame;

import com.google.common.collect.Maps;

public class SGameRegistry implements GameRegistry {

    private final Map<Class<? extends CatalogType>, CatalogRegistryModule<?>> modules = Maps.newHashMap();

    public SGameRegistry() {

    }

    public void performDefaultRegistrations() {
        SGame.getLogger().info("Performing initial type registrations");
        for (CatalogRegistryModule<?> m : this.modules.values()) {
            m.registerDefaults();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends CatalogType> Optional<T> getType(Class<T> typeClass, String id) {
        return ((CatalogRegistryModule<T>) this.modules.get(typeClass)).getById(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends CatalogType> Collection<T> getAllOf(Class<T> typeClass) {
        return ((CatalogRegistryModule<T>) this.modules.get(typeClass)).getAll();
    }

    @Override
    public <T extends CatalogType> Collection<T> getAllFor(String pluginId, Class<T> typeClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends CatalogType> GameRegistry registerModule(Class<T> catalogClass, CatalogRegistryModule<T> registryModule)
            throws IllegalArgumentException, RegistryModuleAlreadyRegisteredException {
        if (this.modules.containsKey(catalogClass)) {
            throw new RegistryModuleAlreadyRegisteredException("Registry module for type " + catalogClass.getName() + " was already registered",
                    this.modules.get(catalogClass));
        }
        this.modules.put(catalogClass, registryModule);
        return this;
    }

    @Override
    public GameRegistry registerModule(RegistryModule module) throws RegistryModuleAlreadyRegisteredException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> GameRegistry registerBuilderSupplier(Class<T> builderClass, Supplier<? extends T> supplier) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends ResettableBuilder<?, ? super T>> T createBuilder(Class<T> builderClass) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends CatalogType> T register(Class<T> type, T obj) throws IllegalArgumentException, CatalogTypeAlreadyRegisteredException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<String> getDefaultGameRules() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<EntityStatistic> getEntityStatistic(StatisticType statType, EntityType entityType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ItemStatistic> getItemStatistic(StatisticType statType, ItemType itemType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockStatistic> getBlockStatistic(StatisticType statType, BlockType blockType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Rotation> getRotationFromDegree(int degrees) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Favicon loadFavicon(String raw) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Favicon loadFavicon(Path path) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Favicon loadFavicon(URL url) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Favicon loadFavicon(InputStream in) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Favicon loadFavicon(BufferedImage image) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RecipeRegistry getRecipeRegistry() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ResourcePack> getResourcePackById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<DisplaySlot> getDisplaySlotForColor(TextColor color) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AITaskType registerAITaskType(Object plugin, String id, String name, Class<? extends AbstractAITask<? extends Agent>> aiClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ExtentBufferFactory getExtentBufferFactory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ValueFactory getValueFactory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VillagerRegistry getVillagerRegistry() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TextSerializerFactory getTextSerializerFactory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SelectorFactory getSelectorFactory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Locale getLocale(String locale) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Translation> getTranslationById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}
