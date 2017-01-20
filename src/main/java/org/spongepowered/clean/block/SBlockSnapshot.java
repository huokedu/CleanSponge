package org.spongepowered.clean.block;

import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.tileentity.TileEntityArchetype;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.world.BlockChangeFlag;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

public class SBlockSnapshot implements BlockSnapshot {

    @Override
    public UUID getWorldUniqueId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector3i getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Location<World>> getLocation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockSnapshot withLocation(Location<World> location) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ImmutableDataManipulator<?, ?>> getManipulators() {
        // TODO Auto-generated method stub
        return null;
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
    public <T extends ImmutableDataManipulator<?, ?>> Optional<T> get(Class<T> containerClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends ImmutableDataManipulator<?, ?>> Optional<T> getOrCreate(Class<T> containerClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(Class<? extends ImmutableDataManipulator<?, ?>> containerClass) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <E> Optional<BlockSnapshot> transform(Key<? extends BaseValue<E>> key, Function<E, E> function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> Optional<BlockSnapshot> with(Key<? extends BaseValue<E>> key, E value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockSnapshot> with(BaseValue<?> value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockSnapshot> with(ImmutableDataManipulator<?, ?> valueContainer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockSnapshot> with(Iterable<ImmutableDataManipulator<?, ?>> valueContainers) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockSnapshot> without(Class<? extends ImmutableDataManipulator<?, ?>> containerClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockSnapshot merge(BlockSnapshot that) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockSnapshot merge(BlockSnapshot that, MergeFunction function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ImmutableDataManipulator<?, ?>> getContainers() {
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
    public BlockSnapshot copy() {
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
    public BlockState getState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockState getExtendedState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockSnapshot withState(BlockState blockState) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockSnapshot withContainer(DataContainer container) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean restore(boolean force, BlockChangeFlag flag) {
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
    public Optional<TileEntityArchetype> createArchetype() {
        // TODO Auto-generated method stub
        return null;
    }

}
