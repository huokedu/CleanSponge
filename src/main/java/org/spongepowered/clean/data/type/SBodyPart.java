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

import org.spongepowered.api.data.type.BodyPart;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SBodyPart extends AbstractCatalogType implements BodyPart {

    public SBodyPart(String id, String name) {
        super(id, name);
    }

    public static void registerTypes(FixedCatalogRegistryModule<BodyPart> registry) {
        registry.register(new SBodyPart("minecraft:chest", "Chest"));
        registry.register(new SBodyPart("minecraft:head", "Head"));
        registry.register(new SBodyPart("minecraft:left_arm", "Left Arm"));
        registry.register(new SBodyPart("minecraft:left_leg", "Left Leg"));
        registry.register(new SBodyPart("minecraft:right_arm", "Right Arm"));
        registry.register(new SBodyPart("minecraft:right_leg", "Right Leg"));
    }

}
