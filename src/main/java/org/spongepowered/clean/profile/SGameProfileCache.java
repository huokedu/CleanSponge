package org.spongepowered.clean.profile;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.profile.GameProfileCache;

public class SGameProfileCache implements GameProfileCache {

    @Override
    public boolean add(GameProfile profile, boolean overwrite, Date expiry) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean remove(GameProfile profile) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<GameProfile> remove(Iterable<GameProfile> profiles) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<GameProfile> getById(UUID uniqueId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<UUID, Optional<GameProfile>> getByIds(Iterable<UUID> uniqueIds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<GameProfile> lookupById(UUID uniqueId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<UUID, Optional<GameProfile>> lookupByIds(Iterable<UUID> uniqueIds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<GameProfile> getOrLookupById(UUID uniqueId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<UUID, Optional<GameProfile>> getOrLookupByIds(Iterable<UUID> uniqueIds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<GameProfile> getByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Optional<GameProfile>> getByNames(Iterable<String> names) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<GameProfile> lookupByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Optional<GameProfile>> lookupByNames(Iterable<String> names) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<GameProfile> getOrLookupByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Optional<GameProfile>> getOrLookupByNames(Iterable<String> names) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<GameProfile> fillProfile(GameProfile profile, boolean signed) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<GameProfile> getProfiles() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<GameProfile> match(String name) {
        // TODO Auto-generated method stub
        return null;
    }

}
