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
package org.spongepowered.clean.block;

import org.spongepowered.api.block.BlockSoundGroup;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.trait.BlockTrait;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.block.state.SpongeBlockState;
import org.spongepowered.clean.registry.AbstractCatalogType;

import java.util.Collection;
import java.util.Optional;

public abstract class SpongeBlockType extends AbstractCatalogType implements BlockType {

    protected SpongeBlockState defaultState;

    public SpongeBlockType(String id, String name) {
        super(id, name);
    }

    @Override
    public Translation getTranslation() {
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
    public BlockState getDefaultState() {
        return this.defaultState;
    }

    @Override
    public Optional<ItemType> getItem() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getTickRandomly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTickRandomly(boolean tickRandomly) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<BlockTrait<?>> getTraits() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<BlockTrait<?>> getTrait(String blockTrait) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BlockSoundGroup getSoundGroup() {
        throw new UnsupportedOperationException();
    }

    public abstract void registerStates();

}
