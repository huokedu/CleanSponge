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

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

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

public class SItemStack implements ItemStack {

    private final SItemType type;
    private int quantity;

    public SItemStack(SItemType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
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
        // TODO validation
        this.quantity = quantity;
    }

    @Override
    public int getMaxStackQuantity() {
        return this.type.getMaxStackQuantity();
    }

    @Override
    public boolean validateRawData(DataView container) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setRawData(DataView container) throws InvalidDataException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getContentVersion() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public DataContainer toContainer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getProperty(Class<T> propertyClass) {
        // TODO support overriding properties on a specific stack?
        return this.type.getDefaultProperty(propertyClass);
    }

    @Override
    public Collection<Property<?, ?>> getApplicableProperties() {
        return this.type.getApplicableProperties();
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> get(Class<T> containerClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> getOrCreate(Class<T> containerClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(Class<? extends DataManipulator<?, ?>> holderClass) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <E> DataTransactionResult offer(Key<? extends BaseValue<E>> key, E value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> DataTransactionResult offer(Key<? extends BaseValue<E>> key, E value, Cause cause) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult offer(DataManipulator<?, ?> valueContainer, MergeFunction function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult offer(DataManipulator<?, ?> valueContainer, MergeFunction function, Cause cause) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult remove(Class<? extends DataManipulator<?, ?>> containerClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult remove(Key<?> key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult undo(DataTransactionResult result) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult copyFrom(DataHolder that, MergeFunction function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<DataManipulator<?, ?>> getContainers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> Optional<E> get(Key<? extends BaseValue<E>> key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E, V extends BaseValue<E>> Optional<V> getValue(Key<V> key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(Key<?> key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Key<?>> getKeys() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<ImmutableValue<?>> getValues() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemStackSnapshot createSnapshot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean equalTo(ItemStack that) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ItemStack copy() {
        return new SItemStack(this.type, this.quantity);
    }

}
