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
