package org.spongepowered.clean.profile;

import java.util.Optional;
import java.util.UUID;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.profile.property.ProfileProperty;

import com.google.common.collect.Multimap;

public class SGameProfile implements GameProfile {

    @Override
    public UUID getUniqueId() {
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
    public Optional<String> getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Multimap<String, ProfileProperty> getPropertyMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isFilled() {
        // TODO Auto-generated method stub
        return false;
    }

}
