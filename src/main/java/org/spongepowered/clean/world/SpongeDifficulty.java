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
package org.spongepowered.clean.world;

import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.modules.world.DifficultyRegistryModule;

public class SpongeDifficulty extends AbstractCatalogType implements Difficulty {

    public static final SpongeDifficulty PEACEFUL = new SpongeDifficulty("minecraft:peaceful", "Peaceful");
    public static final SpongeDifficulty EASY     = new SpongeDifficulty("minecraft:esay", "Easy");
    public static final SpongeDifficulty NORMAL   = new SpongeDifficulty("minecraft:normal", "Normal");
    public static final SpongeDifficulty HARD     = new SpongeDifficulty("minecraft:hard", "Hard");

    public SpongeDifficulty(String id, String name) {
        super(id, name);
    }

    @Override
    public Translation getTranslation() {
        throw new UnsupportedOperationException();
    }

    public static void register(DifficultyRegistryModule registry) {
        registry.registerInternal("minecraft:peaceful", PEACEFUL);
        registry.registerInternal("minecraft:easy", EASY);
        registry.registerInternal("minecraft:normal", NORMAL);
        registry.registerInternal("minecraft:hard", HARD);
    }

}
