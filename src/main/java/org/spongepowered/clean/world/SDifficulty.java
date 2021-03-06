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
import org.spongepowered.api.world.difficulty.Difficulties;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SDifficulty extends AbstractCatalogType implements Difficulty {

    public static Difficulty getById(byte id) {
        if (id == 0) {
            return Difficulties.PEACEFUL;
        } else if (id == 1) {
            return Difficulties.EASY;
        } else if (id == 2) {
            return Difficulties.NORMAL;
        } else if (id == 3) {
            return Difficulties.HARD;
        }
        return Difficulties.NORMAL;
    }

    public static int getId(Difficulty dif) {
        if (dif == Difficulties.EASY) {
            return 1;
        } else if (dif == Difficulties.NORMAL) {
            return 2;
        } else if (dif == Difficulties.HARD) {
            return 3;
        }
        return 0;
    }

    public SDifficulty(String id, String name) {
        super(id, name);
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void registerTypes(FixedCatalogRegistryModule<Difficulty> registry) {
        registry.register(new SDifficulty("minecraft:peaceful", "Peaceful"));
        registry.register(new SDifficulty("minecraft:easy", "Easy"));
        registry.register(new SDifficulty("minecraft:normal", "Normal"));
        registry.register(new SDifficulty("minecraft:hard", "Hard"));
    }

}
