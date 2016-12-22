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
package org.spongepowered.clean.entity;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityArchetype;
import org.spongepowered.api.entity.EntitySnapshot;
import org.spongepowered.api.entity.Transform;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.api.util.AABB;
import org.spongepowered.api.util.RelativePositions;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.clean.world.SpongeWorld;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public abstract class SpongeEntity implements Entity {

    protected UUID        uuid;
    protected int         entity_id;

    protected SpongeWorld world;
    protected double      x, y, z;

    public SpongeEntity(SpongeWorld world) {
        this.world = world;
    }

    public int getEntityId() {
        return this.entity_id;
    }

    public void setEntityId(int id) {
        this.entity_id = id;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    @Override
    public UUID getUniqueId() {
        return this.uuid;
    }

    @Override
    public Location<World> getLocation() {
        return new Location<>(this.world, this.x, this.y, this.z);
    }

    @Override
    public boolean validateRawData(DataView container) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRawData(DataView container) throws InvalidDataException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getContentVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataContainer toContainer() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getProperty(Class<T> propertyClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Property<?, ?>> getApplicableProperties() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> get(Class<T> containerClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> getOrCreate(Class<T> containerClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean supports(Class<? extends DataManipulator<?, ?>> holderClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> DataTransactionResult offer(Key<? extends BaseValue<E>> key, E value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> DataTransactionResult offer(Key<? extends BaseValue<E>> key, E value, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult offer(DataManipulator<?, ?> valueContainer, MergeFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult offer(DataManipulator<?, ?> valueContainer, MergeFunction function, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult remove(Class<? extends DataManipulator<?, ?>> containerClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult remove(Key<?> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult undo(DataTransactionResult result) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult copyFrom(DataHolder that, MergeFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<DataManipulator<?, ?>> getContainers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> Optional<E> get(Key<? extends BaseValue<E>> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E, V extends BaseValue<E>> Optional<V> getValue(Key<V> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean supports(Key<?> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataHolder copy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Key<?>> getKeys() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<ImmutableValue<?>> getValues() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Translation getTranslation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntitySnapshot createSnapshot() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Random getRandom() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setLocation(Location<World> location) {
        if (location.getExtent() != this.world) {
            // TODO handle teleport
        }
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        return true;
    }

    @Override
    public Vector3d getRotation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRotation(Vector3d rotation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setLocationAndRotation(Location<World> location, Vector3d rotation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setLocationAndRotation(Location<World> location, Vector3d rotation, EnumSet<RelativePositions> relativePositions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vector3d getScale() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setScale(Vector3d scale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Transform<World> getTransform() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setTransform(Transform<World> transform) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean transferToWorld(World world, Vector3d position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<AABB> getBoundingBox() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Entity> getPassengers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult addPassenger(Entity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult removePassenger(Entity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult clearPassengers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Entity> getVehicle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataTransactionResult setVehicle(Entity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Entity getBaseVehicle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isOnGround() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isRemoved() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLoaded() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean damage(double damage, DamageSource damageSource, Cause cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<UUID> getCreator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<UUID> getNotifier() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCreator(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNotifier(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityArchetype createArchetype() {
        throw new UnsupportedOperationException();
    }

}
