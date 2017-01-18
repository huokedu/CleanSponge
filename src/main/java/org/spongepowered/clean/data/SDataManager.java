package org.spongepowered.clean.data;

import java.util.Optional;

import org.spongepowered.api.data.DataManager;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.ImmutableDataBuilder;
import org.spongepowered.api.data.ImmutableDataHolder;
import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.persistence.DataBuilder;
import org.spongepowered.api.data.persistence.DataContentUpdater;
import org.spongepowered.api.data.persistence.DataTranslator;

public class SDataManager implements DataManager {

    @Override
    public <T extends DataSerializable> void registerBuilder(Class<T> clazz, DataBuilder<T> builder) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T extends DataSerializable> void registerContentUpdater(Class<T> clazz, DataContentUpdater updater) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T extends DataSerializable> Optional<DataContentUpdater> getWrappedContentUpdater(Class<T> clazz, int fromVersion, int toVersion) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends DataSerializable> Optional<DataBuilder<T>> getBuilder(Class<T> clazz) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends DataSerializable> Optional<T> deserialize(Class<T> clazz, DataView dataView) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends ImmutableDataHolder<T>, B extends ImmutableDataBuilder<T, B>> void register(Class<T> holderClass, B builder) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T extends DataManipulator<T, I>, I extends ImmutableDataManipulator<I, T>> void register(Class<? extends T> manipulatorClass,
            Class<? extends I> immutableManipulatorClass, DataManipulatorBuilder<T, I> builder) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T extends ImmutableDataHolder<T>, B extends ImmutableDataBuilder<T, B>> Optional<B> getImmutableBuilder(Class<T> holderClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends DataManipulator<T, I>, I extends ImmutableDataManipulator<I, T>> Optional<DataManipulatorBuilder<T, I>>
            getManipulatorBuilder(Class<T> manipulatorClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends DataManipulator<T, I>, I extends ImmutableDataManipulator<I, T>> Optional<DataManipulatorBuilder<T, I>>
            getImmutableManipulatorBuilder(Class<I> immutableManipulatorClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> void registerTranslator(Class<T> objectClass, DataTranslator<T> translator) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T> Optional<DataTranslator<T>> getTranslator(Class<T> objectclass) {
        // TODO Auto-generated method stub
        return null;
    }

}
