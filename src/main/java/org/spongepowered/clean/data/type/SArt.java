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
package org.spongepowered.clean.data.type;

import org.spongepowered.api.data.type.Art;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SArt extends AbstractCatalogType implements Art {

    public SArt(String id, String name) {
        super(id, name);
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getWidth() {
        // TODO Auto-generated method stub
        return 0;
    }

    public static void registerTypes(FixedCatalogRegistryModule<Art> registry) {
        registry.register(new SArt("minecraft:alban", "Alban"));
        registry.register(new SArt("minecraft:aztec", "Aztec"));
        registry.register(new SArt("minecraft:aztec_2", "Aztec 2"));
        registry.register(new SArt("minecraft:bomb", "Bomb"));
        registry.register(new SArt("minecraft:burning_skull", "Burning Skull"));
        registry.register(new SArt("minecraft:bust", "Bust"));
        registry.register(new SArt("minecraft:courbet", "Courbet"));
        registry.register(new SArt("minecraft:creebet", "Creebet"));
        registry.register(new SArt("minecraft:donkey_kong", "Donkey Kong"));
        registry.register(new SArt("minecraft:fighters", "Fighters"));
        registry.register(new SArt("minecraft:graham", "Graham"));
        registry.register(new SArt("minecraft:kebab", "Kebab"));
        registry.register(new SArt("minecraft:match", "Match"));
        registry.register(new SArt("minecraft:pigscene", "Pigscene"));
        registry.register(new SArt("minecraft:plant", "Plant"));
        registry.register(new SArt("minecraft:pointer", "Pointer"));
        registry.register(new SArt("minecraft:pool", "Pool"));
        registry.register(new SArt("minecraft:sea", "Sea"));
        registry.register(new SArt("minecraft:skeleton", "Skeleton"));
        registry.register(new SArt("minecraft:skull_and_roses", "Skul and Roses"));
        registry.register(new SArt("minecraft:stage", "Stage"));
        registry.register(new SArt("minecraft:sunset", "Sunset"));
        registry.register(new SArt("minecraft:void", "Void"));
        registry.register(new SArt("minecraft:wanderer", "Wanderer"));
        registry.register(new SArt("minecraft:wasteland", "Wasteland"));
        registry.register(new SArt("minecraft:wither", "Wither"));
    }

}
