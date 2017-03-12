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
package org.spongepowered.clean.text.format;

import org.spongepowered.api.text.Text.Builder;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.util.Color;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class STextColor extends AbstractCatalogType implements TextColor {

    public STextColor(String id, String name) {
        super(id, name);
    }

    @Override
    public void applyTo(Builder builder) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Color getColor() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static void registerTypes(FixedCatalogRegistryModule<TextColor> registry) {
        registry.setDefaultNamespace("");
        registry.register(new STextColor("aqua", "Aqua"));
        registry.register(new STextColor("black", "Black"));
        registry.register(new STextColor("blue", "Blue"));
        registry.register(new STextColor("dark_aqua", "Dark Aqua"));
        registry.register(new STextColor("dark_blue", "Dark Blue"));
        registry.register(new STextColor("dark_gray", "Dark Gray"));
        registry.register(new STextColor("dark_green", "Dark Green"));
        registry.register(new STextColor("dark_purple", "Dark Purple"));
        registry.register(new STextColor("dark_red", "Dark Red"));
        registry.register(new STextColor("gold", "Gold"));
        registry.register(new STextColor("gray", "Gray"));
        registry.register(new STextColor("green", "Green"));
        registry.register(new STextColor("light_purple", "Light Purple"));
        registry.register(new STextColor("red", "Red"));
        registry.register(new STextColor("reset", "Reset"));
        registry.register(new STextColor("white", "White"));
        registry.register(new STextColor("yellow", "Yellow"));
    }

}
