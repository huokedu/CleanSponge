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
package org.spongepowered.clean.entity.player;

import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SGameMode extends AbstractCatalogType implements GameMode {

    public static GameMode getById(int id) {
        if (id == 0) {
            return GameModes.SURVIVAL;
        } else if (id == 1) {
            return GameModes.CREATIVE;
        } else if (id == 2) {
            return GameModes.ADVENTURE;
        } else if (id == 3) {
            return GameModes.SPECTATOR;
        }
        return GameModes.NOT_SET;
    }

    public static int getId(GameMode gm) {
        if (gm == GameModes.CREATIVE) {
            return 1;
        } else if (gm == GameModes.ADVENTURE) {
            return 2;
        } else if (gm == GameModes.SPECTATOR) {
            return 3;
        }
        return 0;
    }

    public SGameMode(String id, String name) {
        super(id, name);
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void registerTypes(FixedCatalogRegistryModule<GameMode> registry) {
        registry.register(new SGameMode("minecraft:survival", "Survival"));
        registry.register(new SGameMode("minecraft:creative", "Creative"));
        registry.register(new SGameMode("minecraft:adventure", "Adventure"));
        registry.register(new SGameMode("minecraft:spectator", "Spectator"));
        registry.register(new SGameMode("minecraft:not_set", "Not Set"));
    }

}
