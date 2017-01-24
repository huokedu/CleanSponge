package org.spongepowered.clean.scoreboard.type;

import org.spongepowered.api.scoreboard.CollisionRule;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SCollisionRule extends AbstractCatalogType implements CollisionRule {

    public SCollisionRule(String id, String name) {
        super(id, name);
    }

    public static void registerTypes(FixedCatalogRegistryModule<CollisionRule> registry) {
        registry.register(new SCollisionRule("minecraft:always", ""));
        registry.register(new SCollisionRule("minecraft:never", ""));
        registry.register(new SCollisionRule("minecraft:push_other_teams", ""));
        registry.register(new SCollisionRule("minecraft:push_own_team", ""));
    }

}
