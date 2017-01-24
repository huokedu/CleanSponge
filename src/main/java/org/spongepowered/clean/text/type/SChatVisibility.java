package org.spongepowered.clean.text.type;

import org.spongepowered.api.text.chat.ChatType;
import org.spongepowered.api.text.chat.ChatVisibility;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SChatVisibility extends AbstractCatalogType implements ChatVisibility {

    public SChatVisibility(String id, String name) {
        super(id, name);
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isVisible(ChatType chatType) {
        // TODO Auto-generated method stub
        return false;
    }

    public static void registerTypes(FixedCatalogRegistryModule<ChatVisibility> registry) {
        registry.register(new SChatVisibility("minecraft:full", ""));
        registry.register(new SChatVisibility("minecraft:hidden", ""));
        registry.register(new SChatVisibility("minecraft:system", ""));
    }

}
