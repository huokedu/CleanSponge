package org.spongepowered.clean.service;

import java.util.Optional;

import org.spongepowered.api.service.ProviderRegistration;
import org.spongepowered.api.service.ProvisioningException;
import org.spongepowered.api.service.ServiceManager;

public class SServiceManager implements ServiceManager {

    @Override
    public <T> void setProvider(Object plugin, Class<T> service, T provider) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T> Optional<T> provide(Class<T> service) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> Optional<ProviderRegistration<T>> getRegistration(Class<T> service) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T provideUnchecked(Class<T> service) throws ProvisioningException {
        // TODO Auto-generated method stub
        return null;
    }

}
