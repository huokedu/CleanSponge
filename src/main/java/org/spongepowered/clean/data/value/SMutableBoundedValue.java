package org.spongepowered.clean.data.value;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableBoundedValue;
import org.spongepowered.api.data.value.mutable.MutableBoundedValue;
import org.spongepowered.api.data.value.mutable.Value;

public abstract class SMutableBoundedValue<E> implements MutableBoundedValue<E> {

    private final E min;
    private final E max;
    private final E def;

    public SMutableBoundedValue(E min, E max, E def) {
        this.min = min;
        this.max = max;
        this.def = def;
    }

    @Override
    public E getMinValue() {
        return this.min;
    }

    @Override
    public E getMaxValue() {
        return this.max;
    }

    @Override
    public Comparator<E> getComparator() {
        // TODO Auto-generated method stub
        return null;
    }

    protected abstract E getIfExists();

    @Override
    public E get() {
        E val = getIfExists();
        if (val == null) {
            return this.def;
        }
        return val;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public E getDefault() {
        return this.def;
    }

    @Override
    public Optional<E> getDirect() {
        return Optional.ofNullable(getIfExists());
    }

    @Override
    public Key<? extends BaseValue<E>> getKey() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Value<E> transform(Function<E, E> function) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableBoundedValue<E> asImmutable() {
        // TODO Auto-generated method stub
        return null;
    }

}
