package org.spongepowered.clean.registry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.registry.CatalogRegistryModule;
import org.spongepowered.api.util.annotation.CatalogedBy;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.scheduler.CoreScheduler;

public class FixedCatalogRegistryModule<T extends CatalogType> implements CatalogRegistryModule<T> {

    private final Map<String, T> types = new HashMap<>();
    private final Consumer<FixedCatalogRegistryModule<T>> registration;
    private final Class<T> type;
    private boolean closed = false;
    private Class<?>[] catalog;
    private String defaultNamespace = "minecraft";

    public FixedCatalogRegistryModule(Class<T> type, Consumer<FixedCatalogRegistryModule<T>> reg) {
        this.type = type;
        this.registration = reg;
        if (type.isAnnotationPresent(CatalogedBy.class)) {
            CatalogedBy anno = type.getAnnotation(CatalogedBy.class);
            this.catalog = anno.value();
        } else {
            SGame.getLogger().debug("No catalog found for type " + type.getName());
        }
    }

    public void setDefaultNamespace(String ns) {
        this.defaultNamespace = ns;
    }

    @Override
    public void registerDefaults() {
        this.registration.accept(this);
        this.closed = true;
        fillCatalog();
    }

    public void register(T type) {
        if (this.closed) {
            throw new IllegalStateException("Cannot register to fixed catalog registry outside of registration time.");
        }
        String id = type.getId();
        if (!id.contains(":")) {
            throw new IllegalArgumentException("catalog type id had no namespace");
        }
        this.types.put(id, type);
    }

    private static void set(Field field, Object type) {
        try {
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(null, type);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            CoreScheduler.emergencyShutdown(e);
        }
    }

    public void fillCatalog() {
        if (this.catalog == null) {
            return;
        }
        for (Class<?> c : this.catalog) {
            for (Field f : c.getDeclaredFields()) {
                if (f.getType().equals(this.type) && Modifier.isStatic(f.getModifiers())) {
                    T type = this.types.get(f.getName().toLowerCase());
                    if (type == null) {
                        type = this.types.get(this.defaultNamespace + ":" + f.getName().toLowerCase());
                    }
                    if (type == null) {
                        SGame.getLogger().warn("No type found for field " + f.getName() + " in catalog " + c.getName());
                        continue;
                    }
                    set(f, type);
                }
            }
        }
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
