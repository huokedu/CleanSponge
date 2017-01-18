package org.spongepowered.clean.entity;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

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
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.Transform;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.api.util.AABB;
import org.spongepowered.api.util.RelativePositions;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.clean.world.SWorld;

import com.flowpowered.math.vector.Vector3d;

public class SEntity implements Entity {

    private UUID uuid;
    private SWorld world;
    private double x, y, z;

    public SEntity(SWorld world) {
        this.uuid = UUID.randomUUID();
        this.world = world;
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
    public EntityType getType() {
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Vector3d getRotation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setRotation(Vector3d rotation) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean setLocationAndRotation(Location<World> location, Vector3d rotation) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean setLocationAndRotation(Location<World> location, Vector3d rotation, EnumSet<RelativePositions> relativePositions) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Vector3d getScale() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setScale(Vector3d scale) {
        // TODO Auto-generated method stub

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
    public DataTransactionResult addPassenger(Entity entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult removePassenger(Entity entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult clearPassengers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Entity> getVehicle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataTransactionResult setVehicle(Entity entity) {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return false;
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
