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
import org.spongepowered.clean.world.SChunk;
import org.spongepowered.clean.world.SWorld;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public abstract class SEntity implements Entity {

    protected UUID uuid;
    protected int entityId;
    protected SWorld world;
    protected SChunk chunk;
    protected double x, y, z;
    protected int bx, by, bz;
    protected double vx, vy, vz;
    protected double yaw, pitch, roll;
    protected Vector3d scale = Vector3d.ONE;

    public SEntity(SWorld world) {
        this.uuid = UUID.randomUUID();
        this.world = world;
    }

    public void serialUpdate() {
        int px = this.bx >> 4;
        int pz = this.bz >> 4;

        this.x += this.vx;
        this.y += this.vy;
        this.z += this.vz;

        this.bx = (int) Math.floor(this.x);
        this.by = (int) Math.floor(this.y);
        this.bz = (int) Math.floor(this.z);

        if ((this.bx >> 4) != px || (this.bz >> 4) != pz) {
            this.chunk.removeEntity(this);
            SChunk chunk = (SChunk) this.world.loadChunk(this.bx >> 4, 0, this.bz >> 4, true).get();
            this.chunk = chunk;
            this.chunk.addEntity(this);
        }
    }

    public void parallelUpdate() {

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

    public int getBlockX() {
        return this.bx;
    }

    public int getBlockY() {
        return this.by;
    }

    public int getBlockZ() {
        return this.bz;
    }

    public int getChunkX() {
        return this.bx >> 4;
    }

    public int getChunkZ() {
        return this.bz >> 4;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public void setEntityId(int id) {
        this.entityId = id;
    }

    @Override
    public UUID getUniqueId() {
        return this.uuid;
    }

    @Override
    public Location<World> getLocation() {
        return new Location<World>(this.world, this.x, this.y, this.z);
    }

    @Override
    public boolean validateRawData(DataView container) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setRawData(DataView container) throws InvalidDataException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getContentVersion() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public DataContainer toContainer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getProperty(Class<T> propertyClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Property<?, ?>> getApplicableProperties() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> get(Class<T> containerClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends DataManipulator<?, ?>> Optional<T> getOrCreate(Class<T> containerClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(Class<? extends DataManipulator<?, ?>> holderClass) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <E> DataTransactionResult offer(Key<? extends BaseValue<E>> key, E value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> DataTransactionResult offer(Key<? extends BaseValue<E>> key, E value, Cause cause) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult offer(DataManipulator<?, ?> valueContainer, MergeFunction function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult offer(DataManipulator<?, ?> valueContainer, MergeFunction function, Cause cause) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult remove(Class<? extends DataManipulator<?, ?>> containerClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult remove(Key<?> key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult undo(DataTransactionResult result) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult copyFrom(DataHolder that, MergeFunction function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<DataManipulator<?, ?>> getContainers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> Optional<E> get(Key<? extends BaseValue<E>> key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E, V extends BaseValue<E>> Optional<V> getValue(Key<V> key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(Key<?> key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public DataHolder copy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Key<?>> getKeys() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<ImmutableValue<?>> getValues() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EntitySnapshot createSnapshot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Random getRandom() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean setLocation(Location<World> location) {
        if (location.getExtent() != this.world) {
            return transferToWorld(location.getExtent(), location.getPosition());
        }
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        return true;
    }

    public void setPosition(Vector3d position) {
        this.x = position.getX();
        this.y = position.getY();
        this.z = position.getZ();
    }

    @Override
    public Vector3d getRotation() {
        return new Vector3d(this.pitch, this.yaw, this.roll);
    }

    @Override
    public void setRotation(Vector3d rotation) {
        this.pitch = rotation.getX();
        this.yaw = rotation.getY();
        // Roll is ignored
    }

    @Override
    public boolean setLocationAndRotation(Location<World> location, Vector3d rotation) {
        setLocation(location);
        setRotation(rotation);
        return true;
    }

    @Override
    public boolean setLocationAndRotation(Location<World> location, Vector3d rotation, EnumSet<RelativePositions> relativePositions) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Vector3d getScale() {
        return this.scale;
    }

    @Override
    public void setScale(Vector3d scale) {
        this.scale = scale;
    }

    @Override
    public Transform<World> getTransform() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean setTransform(Transform<World> transform) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean transferToWorld(World world, Vector3d position) {
        if (this.world == world) {
            setPosition(position);
            return true;
        }
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<AABB> getBoundingBox() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Entity> getPassengers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addPassenger(Entity entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasPassenger(Entity entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removePassenger(Entity entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public void clearPassengers() {
        // TODO Auto-generated method stub
    }

    @Override
    public Optional<Entity> getVehicle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean setVehicle(Entity entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Entity getBaseVehicle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isOnGround() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isRemoved() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isLoaded() {
        return true;
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean damage(double damage, DamageSource damageSource, Cause cause) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<UUID> getCreator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<UUID> getNotifier() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCreator(UUID uuid) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setNotifier(UUID uuid) {
        // TODO Auto-generated method stub

    }

    @Override
    public EntityArchetype createArchetype() {
        // TODO Auto-generated method stub
        return null;
    }

}
