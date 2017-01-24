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
package org.spongepowered.clean.world.gen.type;

import org.spongepowered.api.world.gen.PopulatorObject;
import org.spongepowered.api.world.gen.type.BiomeTreeType;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

import java.util.Optional;

public class SBiomeTreeType extends AbstractCatalogType implements BiomeTreeType {

    public SBiomeTreeType(String id, String name) {
        super(id, name);
    }

    @Override
    public PopulatorObject getPopulatorObject() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPopulatorObject(PopulatorObject object) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean hasLargeEquivalent() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<PopulatorObject> getLargePopulatorObject() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLargePopulatorObject(PopulatorObject object) {
        // TODO Auto-generated method stub

    }

    public static void registerTypes(FixedCatalogRegistryModule<BiomeTreeType> registry) {
        registry.register(new SBiomeTreeType("minecraft:birch", "Birch"));
        registry.register(new SBiomeTreeType("minecraft:canopy", "Canopy"));
        registry.register(new SBiomeTreeType("minecraft:jungle", "Jungle"));
        registry.register(new SBiomeTreeType("minecraft:jungle_bush", "Jungle Bush"));
        registry.register(new SBiomeTreeType("minecraft:oak", "Oak"));
        registry.register(new SBiomeTreeType("minecraft:pointy_taiga", "Pointy Taiga"));
        registry.register(new SBiomeTreeType("minecraft:savanna", "Savanna"));
        registry.register(new SBiomeTreeType("minecraft:swamp", "Swamp"));
        registry.register(new SBiomeTreeType("minecraft:tall_taiga", "Tall Taiga"));
    }

}
