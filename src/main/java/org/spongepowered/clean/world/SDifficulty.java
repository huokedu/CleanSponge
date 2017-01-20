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
