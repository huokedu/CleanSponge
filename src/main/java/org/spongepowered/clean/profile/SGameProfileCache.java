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
