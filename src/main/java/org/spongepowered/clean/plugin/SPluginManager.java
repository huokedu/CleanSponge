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
package org.spongepowered.clean.plugin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.plugin.PluginManager;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.plugin.inject.PluginModule;
import org.spongepowered.clean.plugin.inject.RootModule;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SPluginManager implements PluginManager {

    private Map<String, PluginContainer> pluginsById = ImmutableMap.of();
    private Map<Object, PluginContainer> pluginsByInstance = ImmutableMap.of();

    private List<PluginCandidate> candidates = new ArrayList<>();

    public SPluginManager() {
    }

    public void addCandidate(PluginCandidate candidate) {
        synchronized (this.candidates) {
            this.candidates.add(candidate);
        }
    }

    public void doLoad() {
        synchronized (this.candidates) {
            SGame.getLogger().info("Loading " + this.candidates.size() + " plugins.");
            ImmutableMap.Builder<String, PluginContainer> newPlugins = ImmutableMap.builder();
            ImmutableMap.Builder<Object, PluginContainer> newPluginInstances = ImmutableMap.builder();
            Injector rootInjector = Guice.createInjector(new RootModule());
            for (PluginCandidate candidate : this.candidates) {
                try {
                    SGame.getLogger().info("Loading plugin " + candidate.getId());
                    Class<?> main = Class.forName(candidate.getPluginClass());
                    SPluginContainer container = new SPluginContainer(candidate.getId());
                    Injector injector = rootInjector.createChildInjector(new PluginModule(container));
                    Object instance = injector.getInstance(main);
                    Sponge.getEventManager().registerListeners(container, instance);
                    newPlugins.put(candidate.getId(), container);
                    newPluginInstances.put(instance, container);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
            this.pluginsById = newPlugins.build();
            this.pluginsByInstance = newPluginInstances.build();
        }
    }

    @Override
    public Optional<PluginContainer> fromInstance(Object instance) {
        if (instance instanceof PluginContainer) {
            return Optional.of((PluginContainer) instance);
        }
        return Optional.ofNullable(this.pluginsByInstance.get(instance));
    }

    @Override
    public Optional<PluginContainer> getPlugin(String id) {
        return Optional.ofNullable(this.pluginsById.get(id));
    }

    @Override
    public Collection<PluginContainer> getPlugins() {
        return this.pluginsById.values();
    }

    @Override
    public boolean isLoaded(String id) {
        return this.pluginsById.containsKey(id);
    }

    public List<File> discoverPlugins() {
        File dir = SGame.game.getPluginsDir().toFile();
        if (!dir.exists() || !dir.isDirectory()) {
            return Collections.emptyList();
        }
        List<File> jars = Lists.newArrayList();
        for (File f : dir.listFiles()) {
            if (f.isFile() && f.getName().endsWith(".jar")) {
                jars.add(f);
            }
        }
        return jars;
    }

}
