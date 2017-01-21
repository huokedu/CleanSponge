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
package org.spongepowered.clean.statistic;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Optional;

import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.scoreboard.critieria.Criterion;
import org.spongepowered.api.statistic.achievement.Achievement;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.registry.AbstractCatalogType;

public class SAchievement extends AbstractCatalogType implements Achievement {

    public SAchievement(String id, String name) {
        super(id, name);
    }

    @Override
    public Optional<Criterion> getCriterion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NumberFormat getFormat() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Translation getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Achievement> getParent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Achievement> getChildren() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ItemStackSnapshot> getItemStackSnapshot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isSpecial() {
        // TODO Auto-generated method stub
        return false;
    }

    // TODO register achievements

}