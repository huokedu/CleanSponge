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
package org.spongepowered.clean.item;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.text.translation.Translation;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class SpongeItemStack implements ItemStack {

    private final ItemType type;
    private int quantity;

    public SpongeItemStack(ItemType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getProperty(Class<T> propertyClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Property<?, ?>> getApplicableProperties() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> get(Class<T> containerClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> getOrCreate(Class<T> containerClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean supports(Class<? extends DataManipulator<?, ?>> holderClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> DataTransactionResult offer(Key<? extends BaseValue<E>> key, E value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> DataTransactionResult offer(Key<? extends BaseValue<E>> key, E value, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult offer(DataManipulator<?, ?> valueContainer, MergeFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult offer(DataManipulator<?, ?> valueContainer, MergeFunction function, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult remove(Class<? extends DataManipulator<?, ?>> containerClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult remove(Key<?> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult undo(DataTransactionResult result) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult copyFrom(DataHolder that, MergeFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<DataManipulator<?, ?>> getContainers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> Optional<E> get(Key<? extends BaseValue<E>> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E, V extends BaseValue<E>> Optional<V> getValue(Key<V> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean supports(Key<?> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Key<?>> getKeys() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<ImmutableValue<?>> getValues() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Translation getTranslation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemType getItem() {
        return this.type;
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public void setQuantity(int quantity) throws IllegalArgumentException {
        this.quantity = quantity;
    }

    @Override
    public int getMaxStackQuantity() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemStackSnapshot createSnapshot() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equalTo(ItemStack that) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemStack copy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean validateRawData(DataView container) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRawData(DataView container) throws InvalidDataException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getContentVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataContainer toContainer() {
        throw new UnsupportedOperationException();
    }

}
