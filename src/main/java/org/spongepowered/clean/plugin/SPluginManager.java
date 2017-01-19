package org.spongepowered.clean.plugin;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.plugin.PluginManager;
import org.spongepowered.clean.SGame;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SPluginManager implements PluginManager {

    private final Map<String, PluginContainer> pluginsById = Maps.newHashMap();
    private final Map<Object, PluginContainer> pluginsByInstance = Maps.newHashMap();

    public SPluginManager() {

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
