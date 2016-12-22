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

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.plugin.PluginManager;
import org.spongepowered.clean.SpongeBootstrap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SpongePluginManager implements PluginManager {

    private final Map<String, PluginContainer> pluginsById     = new HashMap<>();
    private final Map<Object, PluginContainer> pluginsByObject = new HashMap<>();
    private PluginContainer                    spongeContainer = new SpongePluginContainer("sponge", SpongeBootstrap.game);

    private Injector                           injector;

    public SpongePluginManager() {
        this.pluginsById.put("sponge", this.spongeContainer);

        this.injector = Guice.createInjector(new SpongeGuiceModule());
    }

    @Override
    public Optional<PluginContainer> fromInstance(Object instance) {
        if (instance instanceof PluginContainer) {
            return Optional.of((PluginContainer) instance);
        }
        return Optional.ofNullable(this.pluginsByObject.get(instance));
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

    public void loadPlugin(String pluginClassName) {
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            Plugin plugin = pluginClass.getAnnotation(Plugin.class);
            SpongeBootstrap.logger.info("Loading plugin: " + plugin.id());
            Injector pluginInjector = this.injector.createChildInjector(new PluginGuiceModule(LogManager.getLogger(plugin.id())));
            Object pluginObj = pluginInjector.getInstance(pluginClass);
            SpongePluginContainer container = new SpongePluginContainer(plugin.id(), pluginObj);
            this.pluginsById.put(plugin.id(), container);
            this.pluginsByObject.put(pluginObj, container);
            Sponge.getEventManager().registerListeners(pluginObj, pluginObj);
        } catch (Exception e) {
            SpongeBootstrap.logger.error("Error loading plugin from " + pluginClassName);
            e.printStackTrace();
            return;
        }
    }

    public PluginContainer getSpongePluginContainer() {
        return this.spongeContainer;
    }

}
