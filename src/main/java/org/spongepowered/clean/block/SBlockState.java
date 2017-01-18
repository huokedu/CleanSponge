package org.spongepowered.clean.block;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.trait.BlockTrait;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.util.Cycleable;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class SBlockState implements BlockState {

    private final SBlockType type;
    private final String id;
    private final String name;

    public SBlockState(SBlockType type, String name) {
        this.type = type;
        this.id = generateId();
        this.name = name;
    }

    private String generateId() {
        return this.type.getId() + "[]";
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public BlockType getType() {
        return this.type;
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
    public <E> Optional<BlockState> transform(Key<? extends BaseValue<E>> key, Function<E, E> function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <E> Optional<BlockState> with(Key<? extends BaseValue<E>> key, E value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockState> with(BaseValue<?> value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockState> with(ImmutableDataManipulator<?, ?> valueContainer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockState> with(Iterable<ImmutableDataManipulator<?, ?>> valueContainers) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockState> without(Class<? extends ImmutableDataManipulator<?, ?>> containerClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockState merge(BlockState that) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockState merge(BlockState that, MergeFunction function) {
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
    public BlockState copy() {
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
    public <T extends Property<?, ?>> Optional<T> getProperty(Direction direction, Class<T> clazz) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockState withExtendedProperties(Location<World> location) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockState cycleValue(Key<? extends BaseValue<? extends Cycleable<?>>> key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockSnapshot snapshotFor(Location<World> location) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Comparable<T>> Optional<T> getTraitValue(BlockTrait<T> blockTrait) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockTrait<?>> getTrait(String blockTrait) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockState> withTrait(BlockTrait<?> trait, Object value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<BlockTrait<?>> getTraits() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<?> getTraitValues() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<BlockTrait<?>, ?> getTraitMap() {
        // TODO Auto-generated method stub
        return null;
    }

}
