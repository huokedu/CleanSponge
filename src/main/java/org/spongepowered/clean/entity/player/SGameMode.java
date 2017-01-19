package org.spongepowered.clean.entity.player;

import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SGameMode extends AbstractCatalogType implements GameMode {

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
