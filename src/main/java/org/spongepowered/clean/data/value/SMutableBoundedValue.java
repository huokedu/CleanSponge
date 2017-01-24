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
package org.spongepowered.clean.data.value;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableBoundedValue;
import org.spongepowered.api.data.value.mutable.MutableBoundedValue;
import org.spongepowered.api.data.value.mutable.Value;

public abstract class SMutableBoundedValue<E> implements MutableBoundedValue<E> {

    private final E min;
    private final E max;
    private final E def;

    public SMutableBoundedValue(E min, E max, E def) {
        this.min = min;
        this.max = max;
        this.def = def;
    }

    @Override
    public E getMinValue() {
        return this.min;
    }

    @Override
    public E getMaxValue() {
        return this.max;
    }

    @Override
    public Comparator<E> getComparator() {
        // TODO Auto-generated method stub
        return null;
    }

    protected abstract E getIfExists();

    @Override
    public E get() {
        E val = getIfExists();
        if (val == null) {
            return this.def;
        }
        return val;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public E getDefault() {
        return this.def;
    }

    @Override
    public Optional<E> getDirect() {
        return Optional.ofNullable(getIfExists());
    }

    @Override
    public Key<? extends BaseValue<E>> getKey() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Value<E> transform(Function<E, E> function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableBoundedValue<E> asImmutable() {
        // TODO Auto-generated method stub
        return null;
    }

}
