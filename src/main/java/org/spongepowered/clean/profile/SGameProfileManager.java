package org.spongepowered.clean.profile;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.profile.GameProfileCache;
import org.spongepowered.api.profile.GameProfileManager;
import org.spongepowered.api.profile.property.ProfileProperty;

public class SGameProfileManager implements GameProfileManager {

    @Override
    public GameProfile createProfile(UUID uniqueId, String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProfileProperty createProfileProperty(String name, String value, String signature) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompletableFuture<GameProfile> get(UUID uniqueId, boolean useCache) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompletableFuture<Collection<GameProfile>> getAllById(Iterable<UUID> uniqueIds, boolean useCache) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompletableFuture<GameProfile> get(String name, boolean useCache) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompletableFuture<Collection<GameProfile>> getAllByName(Iterable<String> names, boolean useCache) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompletableFuture<GameProfile> fill(GameProfile profile, boolean signed, boolean useCache) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameProfileCache getCache() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCache(GameProfileCache cache) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public GameProfileCache getDefaultCache() {
        // TODO Auto-generated method stub
        return null;
    }

}
