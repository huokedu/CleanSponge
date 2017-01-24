package org.spongepowered.clean.boss;

import org.spongepowered.api.boss.BossBarColor;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SBossBarColor extends AbstractCatalogType implements BossBarColor {

    public SBossBarColor(String id, String name) {
        super(id, name);
    }

    public static void registerTypes(FixedCatalogRegistryModule<BossBarColor> registry) {
        registry.register(new SBossBarColor("minecraft:blue", ""));
        registry.register(new SBossBarColor("minecraft:green", ""));
        registry.register(new SBossBarColor("minecraft:pink", ""));
        registry.register(new SBossBarColor("minecraft:purple", ""));
        registry.register(new SBossBarColor("minecraft:red", ""));
        registry.register(new SBossBarColor("minecraft:white", ""));
        registry.register(new SBossBarColor("minecraft:yellow", ""));
    }

}
