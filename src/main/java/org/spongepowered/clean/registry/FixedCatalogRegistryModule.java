package org.spongepowered.clean.registry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.registry.CatalogRegistryModule;

public class FixedCatalogRegistryModule<T extends CatalogType> implements CatalogRegistryModule<T> {

    private final Map<String, T> types = new HashMap<>();
    private final Consumer<FixedCatalogRegistryModule<T>> registration;
    private boolean closed = false;

    public FixedCatalogRegistryModule(Consumer<FixedCatalogRegistryModule<T>> reg) {
        this.registration = reg;
    }

    @Override
    public void registerDefaults() {
        this.registration.accept(this);
        this.closed = true;
    }

    public void register(T type) {
        if (this.closed) {
            throw new IllegalStateException("Cannot register to fixed catalog registry outside of registration time.");
        }
        this.types.put(type.getId(), type);
    }

    @Override
    public Optional<T> getById(String id) {
        return Optional.ofNullable(this.types.get(id));
    }

    @Override
    public Collection<T> getAll() {
        return this.types.values();
    }

}
