package org.spongepowered.clean.entity;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.registry.AbstractCatalogType;

public class SEntityType extends AbstractCatalogType implements EntityType {

    private int entityid;

    public SEntityType(String id, String name, int entityid) {
        super(id, name);
        this.entityid = entityid;
    }

    public int getEntityId() {
        return this.entityid;
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<? extends Entity> getEntityClass() {
        // TODO Auto-generated method stub
        return null;
    }

}
