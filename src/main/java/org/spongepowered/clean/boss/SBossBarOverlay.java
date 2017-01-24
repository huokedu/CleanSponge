package org.spongepowered.clean.boss;

import org.spongepowered.api.boss.BossBarOverlay;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SBossBarOverlay extends AbstractCatalogType implements BossBarOverlay {

    public SBossBarOverlay(String id, String name) {
        super(id, name);
    }

    public static void registerTypes(FixedCatalogRegistryModule<BossBarOverlay> registry) {
        registry.register(new SBossBarOverlay("minecraft:notched_6", ""));
        registry.register(new SBossBarOverlay("minecraft:notched_10", ""));
        registry.register(new SBossBarOverlay("minecraft:notched_12", ""));
        registry.register(new SBossBarOverlay("minecraft:notched_20", ""));
        registry.register(new SBossBarOverlay("minecraft:progress", ""));
    }

}
