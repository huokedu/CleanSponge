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
package org.spongepowered.clean.block.state;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.trait.BlockTrait;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.util.Cycleable;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.clean.block.SpongeBlockType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class SpongeBlockState implements BlockState {

    private final SpongeBlockType block;
    private final BlockProperty<?>[] properties;

    private int                   internal_id;
    private String                id;
    private String                name;

    public SpongeBlockState(SpongeBlockType block, BlockProperty<?>[] properties) {
        this.block = block;
        this.properties = properties;
    }

    @Override
    public List<ImmutableDataManipulator<?, ?>> getManipulators() {
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

    @Override
    public <T extends Property<?, ?>> Optional<T> getProperty(Class<T> propertyClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Property<?, ?>> getApplicableProperties() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends ImmutableDataManipulator<?, ?>> Optional<T> get(Class<T> containerClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends ImmutableDataManipulator<?, ?>> Optional<T> getOrCreate(Class<T> containerClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean supports(Class<? extends ImmutableDataManipulator<?, ?>> containerClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> Optional<BlockState> transform(Key<? extends BaseValue<E>> key, Function<E, E> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> Optional<BlockState> with(Key<? extends BaseValue<E>> key, E value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<BlockState> with(BaseValue<?> value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<BlockState> with(ImmutableDataManipulator<?, ?> valueContainer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<BlockState> with(Iterable<ImmutableDataManipulator<?, ?>> valueContainers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<BlockState> without(Class<? extends ImmutableDataManipulator<?, ?>> containerClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BlockState merge(BlockState that) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BlockState merge(BlockState that, MergeFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ImmutableDataManipulator<?, ?>> getContainers() {
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
    public BlockState copy() {
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
    public <T extends Property<?, ?>> Optional<T> getProperty(Direction direction, Class<T> clazz) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public BlockType getType() {
        return this.block;
    }

    @Override
    public BlockState withExtendedProperties(Location<World> location) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BlockState cycleValue(Key<? extends BaseValue<? extends Cycleable<?>>> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BlockSnapshot snapshotFor(Location<World> location) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Comparable<T>> Optional<T> getTraitValue(BlockTrait<T> blockTrait) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<BlockTrait<?>> getTrait(String blockTrait) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<BlockState> withTrait(BlockTrait<?> trait, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<BlockTrait<?>> getTraits() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<?> getTraitValues() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<BlockTrait<?>, ?> getTraitMap() {
        throw new UnsupportedOperationException();
    }

    public int getInternalId() {
        return this.internal_id;
    }

    public void setInternalId(int i) {
        this.internal_id = i;
    }

}
