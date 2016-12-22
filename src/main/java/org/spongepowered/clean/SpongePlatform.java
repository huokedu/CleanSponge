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
package org.spongepowered.clean;

import org.spongepowered.api.MinecraftVersion;
import org.spongepowered.api.Platform;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.Map;

public class SpongePlatform implements Platform {

    private final MinecraftVersion mcVersion = new SpongeMinecraftVersion();

    public SpongePlatform() {

    }

    @Override
    public Type getType() {
        return Type.SERVER;
    }

    @Override
    public Type getExecutionType() {
        return Type.SERVER;
    }

    @Override
    public PluginContainer getContainer(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MinecraftVersion getMinecraftVersion() {
        return this.mcVersion;
    }

    @Override
    public Map<String, Object> asMap() {
        throw new UnsupportedOperationException();
    }

    public static class SpongeMinecraftVersion implements MinecraftVersion {

        @Override
        public int compareTo(MinecraftVersion arg0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getName() {
            return SpongeConstants.GAME_VERSION;
        }

        @Override
        public boolean isLegacy() {
            return false;
        }

    }

}
