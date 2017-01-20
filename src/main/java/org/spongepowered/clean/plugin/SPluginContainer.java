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

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

public class SPluginContainer implements PluginContainer {

    private final String id;
    private String name;
    private Optional<String> version = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<String> url = Optional.empty();
    private List<String> authors = Lists.newArrayList();
    private Object instance;
    private Logger logger;

    public SPluginContainer(String id) {
        this.id = id;
        this.name = id;
        this.logger = LoggerFactory.getLogger(getId());
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Optional<String> getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = Optional.ofNullable(version);
    }

    @Override
    public Optional<String> getDescription() {
        return this.description;
    }

    public void setDescription(String desc) {
        this.description = Optional.ofNullable(desc);
    }

    @Override
    public Optional<String> getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = Optional.ofNullable(url);
    }

    @Override
    public List<String> getAuthors() {
        return this.authors;
    }

    public void addAuthor(String author) {
        this.authors.add(author);
    }

    public void clearAuthors() {
        this.authors.clear();
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
        return Optional.ofNullable(this.instance);
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

}
