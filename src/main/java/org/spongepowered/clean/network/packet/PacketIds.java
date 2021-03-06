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
package org.spongepowered.clean.network.packet;

import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.world.difficulty.Difficulties;
import org.spongepowered.api.world.difficulty.Difficulty;

public class PacketIds {

    public static byte getGameModeId(GameMode gamemode) {
        if (gamemode == GameModes.SURVIVAL) {
            return 0;
        } else if (gamemode == GameModes.CREATIVE) {
            return 1;
        } else if (gamemode == GameModes.ADVENTURE) {
            return 2;
        } else if (gamemode == GameModes.SPECTATOR) {
            return 3;
        }
        return 0;
    }

    public static byte getDifficultyId(Difficulty difficulty) {
        if (difficulty == Difficulties.PEACEFUL) {
            return 0;
        } else if (difficulty == Difficulties.EASY) {
            return 1;
        } else if (difficulty == Difficulties.NORMAL) {
            return 2;
        } else if (difficulty == Difficulties.HARD) {
            return 3;
        }
        return 0;
    }
}
