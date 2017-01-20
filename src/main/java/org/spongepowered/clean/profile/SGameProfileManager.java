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
