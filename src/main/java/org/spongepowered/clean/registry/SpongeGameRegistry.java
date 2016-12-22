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
package org.spongepowered.clean.registry;

import com.google.common.collect.Maps;
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.tileentity.TileEntityType;
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
import org.spongepowered.api.statistic.Statistic;
import org.spongepowered.api.statistic.StatisticGroup;
import org.spongepowered.api.statistic.TeamStatistic;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.selector.SelectorFactory;
import org.spongepowered.api.text.serializer.TextSerializerFactory;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.util.rotation.Rotation;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.api.world.extent.ExtentBufferFactory;
import org.spongepowered.clean.registry.modules.block.BlockTypeRegistryModule;
import org.spongepowered.clean.registry.modules.block.TileEntityTypeRegistryModule;
import org.spongepowered.clean.registry.modules.entity.EntityTypeRegistryModule;
import org.spongepowered.clean.registry.modules.world.DifficultyRegistryModule;

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
import java.util.stream.Collectors;

public class SpongeGameRegistry implements GameRegistry {

    private final Map<Class<? extends CatalogType>, CatalogRegistryModule<?>> modules = Maps.newHashMap();

    public SpongeGameRegistry() {

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends CatalogType> Optional<T> getType(Class<T> typeClass, String id) {
        CatalogRegistryModule<T> module = (CatalogRegistryModule<T>) this.modules.get(typeClass);
        if (module == null) {
            throw new IllegalArgumentException("Unknown catalog type: " + typeClass.getName());
        }
        return module.getById(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends CatalogType> Collection<T> getAllOf(Class<T> typeClass) {
        CatalogRegistryModule<T> module = (CatalogRegistryModule<T>) this.modules.get(typeClass);
        if (module == null) {
            throw new IllegalArgumentException("Unknown catalog type: " + typeClass.getName());
        }
        return module.getAll();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends CatalogType> Collection<T> getAllFor(String pluginId, Class<T> typeClass) {
        CatalogRegistryModule<T> module = (CatalogRegistryModule<T>) this.modules.get(typeClass);
        if (module == null) {
            throw new IllegalArgumentException("Unknown catalog type: " + typeClass.getName());
        }
        return module.getAll().stream().filter((t) -> t.getId().startsWith(pluginId + ":")).collect(Collectors.toList());
    }

    @Override
    public <T extends CatalogType> GameRegistry registerModule(Class<T> catalogClass, CatalogRegistryModule<T> registryModule)
            throws IllegalArgumentException, RegistryModuleAlreadyRegisteredException {
        CatalogRegistryModule<?> module = this.modules.get(catalogClass);
        if (module != null) {
            throw new RegistryModuleAlreadyRegisteredException("Module for catalog type " + catalogClass.getName() + " was already registered",
                    module);
        }
        this.modules.put(catalogClass, registryModule);
        return this;
    }

    @Override
    public GameRegistry registerModule(RegistryModule module) throws RegistryModuleAlreadyRegisteredException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> GameRegistry registerBuilderSupplier(Class<T> builderClass, Supplier<? extends T> supplier) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends ResettableBuilder<?, ? super T>> T createBuilder(Class<T> builderClass) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends CatalogType> T register(Class<T> type, T obj) throws IllegalArgumentException, CatalogTypeAlreadyRegisteredException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> getDefaultGameRules() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<EntityStatistic> getEntityStatistic(StatisticGroup statisticGroup, EntityType entityType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ItemStatistic> getItemStatistic(StatisticGroup statisticGroup, ItemType itemType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<BlockStatistic> getBlockStatistic(StatisticGroup statisticGroup, BlockType blockType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<TeamStatistic> getTeamStatistic(StatisticGroup statisticGroup, TextColor teamColor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Statistic> getStatistics(StatisticGroup statisticGroup) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Rotation> getRotationFromDegree(int degrees) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Favicon loadFavicon(String raw) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Favicon loadFavicon(Path path) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Favicon loadFavicon(URL url) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Favicon loadFavicon(InputStream in) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Favicon loadFavicon(BufferedImage image) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public RecipeRegistry getRecipeRegistry() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ResourcePack> getResourcePackById(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<DisplaySlot> getDisplaySlotForColor(TextColor color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public AITaskType registerAITaskType(Object plugin, String id, String name, Class<? extends AbstractAITask<? extends Agent>> aiClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ExtentBufferFactory getExtentBufferFactory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ValueFactory getValueFactory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public VillagerRegistry getVillagerRegistry() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextSerializerFactory getTextSerializerFactory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SelectorFactory getSelectorFactory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Locale getLocale(String locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Translation> getTranslationById(String id) {
        throw new UnsupportedOperationException();
    }

    public void registerGameModules() {
        this.modules.put(BlockType.class, BlockTypeRegistryModule.instance);
        this.modules.put(EntityType.class, EntityTypeRegistryModule.instance);
        this.modules.put(TileEntityType.class, TileEntityTypeRegistryModule.instance);
        this.modules.put(Difficulty.class, DifficultyRegistryModule.instance);
    }

    public void doPreInit() {
        BlockTypeRegistryModule.instance.doRegistrations();
        EntityTypeRegistryModule.instance.doRegistrations();
        TileEntityTypeRegistryModule.instance.doRegistrations();
        DifficultyRegistryModule.instance.doRegistrations();
    }

}
