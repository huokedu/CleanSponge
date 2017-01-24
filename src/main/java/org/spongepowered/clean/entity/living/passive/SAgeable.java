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
package org.spongepowered.clean.entity.living.passive;

import org.spongepowered.api.data.value.mutable.MutableBoundedValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.entity.living.Ageable;
import org.spongepowered.clean.data.value.SMutableBoundedValue;
import org.spongepowered.clean.entity.living.SCreature;
import org.spongepowered.clean.world.SWorld;

public abstract class SAgeable extends SCreature implements Ageable {

    private final MutableBoundedValue<Integer> ageValue;

    private int age;

    public SAgeable(SWorld world) {
        super(world);
        this.age = 0;
        // TODO add param for max age
        this.ageValue = new SMutableBoundedValue<Integer>(0, 1, 0) {

            @Override
            public Value<Integer> set(Integer value) {
                SAgeable.this.age = value;
                return this;
            }

            @Override
            protected Integer getIfExists() {
                return SAgeable.this.age;
            }

        };
    }

    @Override
    public void setScaleForAge() {
        // TODO Auto-generated method stub

    }

    @Override
    public MutableBoundedValue<Integer> age() {
        return this.ageValue;
    }

}
