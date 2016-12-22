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
package org.spongepowered.clean.data;

import org.spongepowered.api.data.DataManager;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.ImmutableDataBuilder;
import org.spongepowered.api.data.ImmutableDataHolder;
import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.persistence.DataBuilder;
import org.spongepowered.api.data.persistence.DataContentUpdater;
import org.spongepowered.api.data.persistence.DataTranslator;

import java.util.Optional;

public class SpongeDataManager implements DataManager {

    public SpongeDataManager() {

    }

    @Override
    public <T extends DataSerializable> void registerBuilder(Class<T> clazz, DataBuilder<T> builder) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataSerializable> void registerContentUpdater(Class<T> clazz, DataContentUpdater updater) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataSerializable> Optional<DataContentUpdater> getWrappedContentUpdater(Class<T> clazz, int fromVersion, int toVersion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataSerializable> Optional<DataBuilder<T>> getBuilder(Class<T> clazz) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataSerializable> Optional<T> deserialize(Class<T> clazz, DataView dataView) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends ImmutableDataHolder<T>, B extends ImmutableDataBuilder<T, B>> void register(Class<T> holderClass, B builder) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataManipulator<T, I>, I extends ImmutableDataManipulator<I, T>> void register(Class<? extends T> manipulatorClass,
            Class<? extends I> immutableManipulatorClass, DataManipulatorBuilder<T, I> builder) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends ImmutableDataHolder<T>, B extends ImmutableDataBuilder<T, B>> Optional<B> getImmutableBuilder(Class<T> holderClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataManipulator<T, I>, I extends ImmutableDataManipulator<I, T>> Optional<DataManipulatorBuilder<T, I>> getManipulatorBuilder(
            Class<T> manipulatorClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataManipulator<T, I>, I extends ImmutableDataManipulator<I, T>> Optional<DataManipulatorBuilder<T, I>> getImmutableManipulatorBuilder(
            Class<I> immutableManipulatorClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void registerTranslator(Class<T> objectClass, DataTranslator<T> translator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Optional<DataTranslator<T>> getTranslator(Class<T> objectclass) {
        throw new UnsupportedOperationException();
    }

}
