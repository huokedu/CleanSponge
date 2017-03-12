/*
 * This file is part of SpongeClean, licensed under the MIT License (MIT).
 *
 * Copyright (c) The VoxelBox <http://thevoxelbox.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.clean.registry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.registry.CatalogRegistryModule;
import org.spongepowered.api.util.annotation.CatalogedBy;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.scheduler.CoreScheduler;

public class FixedCatalogRegistryModule<T extends CatalogType> implements CatalogRegistryModule<T> {

    private final Map<String, T> types = new HashMap<>();
    private final Map<String, String> aliases = new HashMap<>();
    private final Consumer<FixedCatalogRegistryModule<T>> registration;
    private final Class<T> type;
    private boolean closed = false;
    private Class<?>[] catalog;
    private String defaultNamespace = "minecraft";
    private boolean defaultsRegistered = false;
    private boolean requiresNamespace = true;

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
        if (ns.isEmpty()) {
            this.requiresNamespace = false;
        }
    }

    @Override
    public void registerDefaults() {
        if (this.defaultsRegistered) {
            return;
        }
        this.registration.accept(this);
        this.closed = true;
        fillCatalog();
        this.defaultsRegistered = true;
    }

    public void register(T type) {
        if (this.closed) {
            throw new IllegalStateException("Cannot register to fixed catalog registry outside of registration time.");
        }
        String id = type.getId();
        if (!id.contains(":") && this.requiresNamespace) {
            throw new IllegalArgumentException("catalog type id had no namespace");
        }
        this.types.put(id, type);
    }

    public void registerAlias(String alias, String id) {
        if (!this.types.containsKey(id)) {
            throw new IllegalArgumentException("Cannot add alias to non-existant type " + id);
        }
        this.aliases.put(alias, id);
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
                    Optional<T> type = getById(f.getName().toLowerCase());
                    if (!type.isPresent() && !this.defaultNamespace.isEmpty()) {
                        type = getById(this.defaultNamespace + ":" + f.getName().toLowerCase());
                    }
                    // TODO attempt get any regardless of namespace
                    if (!type.isPresent()) {
                        SGame.getLogger().warn("No type found for field " + f.getName() + " in catalog " + c.getName());
                        continue;
                    }
                    set(f, type.get());
                }
            }
        }
    }

    @Override
    public Optional<T> getById(String id) {
        String alias = this.aliases.get(id);
        if (alias != null) {
            return Optional.ofNullable(this.types.get(alias));
        }
        return Optional.ofNullable(this.types.get(id));
    }

    @Override
    public Collection<T> getAll() {
        return this.types.values();
    }

    public Collection<T> getAllFor(String plugin) {
        List<T> types = new ArrayList<>();
        String prefix = plugin + ":";
        for (Map.Entry<String, T> e : this.types.entrySet()) {
            if (e.getKey().startsWith(prefix)) {
                types.add(e.getValue());
            }
        }
        return types;
    }

}
