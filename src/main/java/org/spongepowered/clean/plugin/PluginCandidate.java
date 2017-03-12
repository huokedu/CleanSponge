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

import org.spongepowered.plugin.meta.PluginMetadata;

import java.util.Map;

public class PluginCandidate {

    private String pluginClass;
    private Map<String, Object> annotationValues;
    private PluginMetadata metadata;

    public PluginCandidate(String name, Map<String, Object> plugin) {
        this.pluginClass = name;
        this.annotationValues = plugin;

    }

    public String getPluginClass() {
        return this.pluginClass;
    }

    public Map<String, Object> getAnnotationValues() {
        return this.annotationValues;
    }

    public String getId() {
        return (String) this.annotationValues.get("id");
    }

    public PluginMetadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(PluginMetadata meta) {
        this.metadata = meta;
    }

}
