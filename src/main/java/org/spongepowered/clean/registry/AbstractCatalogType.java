package org.spongepowered.clean.registry;

import org.spongepowered.api.CatalogType;

public class AbstractCatalogType implements CatalogType {

    protected final String id;
    protected final String name;

    public AbstractCatalogType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public final String getId() {
        return this.id;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !getClass().isInstance(o)) {
            return false;
        }
        return this.id.equals(((AbstractCatalogType) o).getId());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
