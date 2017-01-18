package org.spongepowered.clean.plugin;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.asset.Asset;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.plugin.meta.PluginDependency;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class SPluginContainer implements PluginContainer {

    private final String id;

    public SPluginContainer(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return getId();
    }

    @Override
    public Optional<String> getVersion() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Optional<String> getDescription() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Optional<String> getUrl() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<String> getAuthors() {
        // TODO Auto-generated method stub
        return ImmutableList.of();
    }

    @Override
    public Set<PluginDependency> getDependencies() {
        // TODO Auto-generated method stub
        return ImmutableSet.of();
    }

    @Override
    public Optional<PluginDependency> getDependency(String id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Optional<Asset> getAsset(String name) {
        // TODO Auto-generated method stub
        return Sponge.getAssetManager().getAsset(this, name);
    }

    @Override
    public Optional<Path> getSource() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Optional<?> getInstance() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Logger getLogger() {
        // TODO Auto-generated method stub
        return LoggerFactory.getLogger(getId());
    }

}
