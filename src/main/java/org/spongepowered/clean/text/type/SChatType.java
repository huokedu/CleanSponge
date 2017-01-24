package org.spongepowered.clean.text.type;

import org.spongepowered.api.text.chat.ChatType;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SChatType extends AbstractCatalogType implements ChatType {

    public SChatType(String id, String name) {
        super(id, name);
    }

    public static void registerTypes(FixedCatalogRegistryModule<ChatType> registry) {
        registry.register(new SChatType("minecraft:action_bar", ""));
        registry.register(new SChatType("minecraft:chat", ""));
        registry.register(new SChatType("minecraft:system", ""));
    }

}
