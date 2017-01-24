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

import org.spongepowered.api.data.type.BigMushroomType;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SBigMushroomType extends AbstractCatalogType implements BigMushroomType {

    public SBigMushroomType(String id, String name) {
        super(id, name);
    }

    public static void registerTypes(FixedCatalogRegistryModule<BigMushroomType> registry) {
        registry.register(new SBigMushroomType("minecraft:all_inside", "all_inside"));
        registry.register(new SBigMushroomType("minecraft:all_outside", "all_outside"));
        registry.register(new SBigMushroomType("minecraft:all_stem", "all_stem"));
        registry.register(new SBigMushroomType("minecraft:center", "center"));
        registry.register(new SBigMushroomType("minecraft:east", "east"));
        registry.register(new SBigMushroomType("minecraft:north", "north"));
        registry.register(new SBigMushroomType("minecraft:north_east", "north_east"));
        registry.register(new SBigMushroomType("minecraft:north_west", "north_west"));
        registry.register(new SBigMushroomType("minecraft:south", "south"));
        registry.register(new SBigMushroomType("minecraft:south_east", "south_east"));
        registry.register(new SBigMushroomType("minecraft:south_west", "south_west"));
        registry.register(new SBigMushroomType("minecraft:stem", "stem"));
        registry.register(new SBigMushroomType("minecraft:west", "west"));
    }

}
