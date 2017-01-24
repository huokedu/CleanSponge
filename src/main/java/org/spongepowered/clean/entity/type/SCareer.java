package org.spongepowered.clean.entity.type;

import org.spongepowered.api.data.type.Career;
import org.spongepowered.api.data.type.Profession;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SCareer extends AbstractCatalogType implements Career {

    public SCareer(String id, String name) {
        super(id, name);
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Profession getProfession() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void registerTypes(FixedCatalogRegistryModule<Career> registry) {
        registry.register(new SCareer("minecraft:armorer", ""));
        registry.register(new SCareer("minecraft:butcher", ""));
        registry.register(new SCareer("minecraft:cleric", ""));
        registry.register(new SCareer("minecraft:farmer", ""));
        registry.register(new SCareer("minecraft:fisherman", ""));
        registry.register(new SCareer("minecraft:fletcher", ""));
        registry.register(new SCareer("minecraft:leatherworker", ""));
        registry.register(new SCareer("minecraft:librarian", ""));
        registry.register(new SCareer("minecraft:shepherd", ""));
        registry.register(new SCareer("minecraft:tool_smith", ""));
        registry.register(new SCareer("minecraft:weapon_smith", ""));
    }

}
