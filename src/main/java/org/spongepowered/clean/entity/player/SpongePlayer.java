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
package org.spongepowered.clean.entity.player;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.data.type.SkinPart;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.sound.SoundCategory;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.tab.TabList;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.network.PlayerConnection;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.resourcepack.ResourcePack;
import org.spongepowered.api.scoreboard.Scoreboard;
import org.spongepowered.api.service.context.Context;
import org.spongepowered.api.service.permission.Subject;
import org.spongepowered.api.service.permission.SubjectCollection;
import org.spongepowered.api.service.permission.SubjectData;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.chat.ChatType;
import org.spongepowered.api.text.chat.ChatVisibility;
import org.spongepowered.api.text.title.Title;
import org.spongepowered.api.util.Tristate;
import org.spongepowered.clean.entity.SpongeEntityType;
import org.spongepowered.clean.network.NetworkConnection;
import org.spongepowered.clean.world.SpongeWorld;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class SpongePlayer extends SpongeHumanoid implements Player {

    private final NetworkConnection connection;

    public SpongePlayer(SpongeWorld world, UUID uid, NetworkConnection conn) {
        super(world);
        this.uuid = uid;
        this.connection = conn;
    }

    public NetworkConnection getNetConnection() {
        return this.connection;
    }

    @Override
    public GameProfile getProfile() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isOnline() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Player> getPlayer() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<CommandSource> getCommandSource() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SubjectCollection getContainingCollection() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SubjectData getSubjectData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SubjectData getTransientSubjectData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tristate getPermissionValue(Set<Context> contexts, String permission) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isChildOf(Set<Context> contexts, Subject parent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Subject> getParents(Set<Context> contexts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<String> getOption(Set<Context> contexts, String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getIdentifier() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Context> getActiveContexts() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendMessage(Text message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MessageChannel getMessageChannel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMessageChannel(MessageChannel channel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void spawnParticles(ParticleEffect particleEffect, Vector3d position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void spawnParticles(ParticleEffect particleEffect, Vector3d position, int radius) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume, double pitch) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume, double pitch, double minVolume) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendTitle(Title title) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendBookView(BookView bookView) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendBlockChange(int x, int y, int z, BlockState state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetBlockChange(int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendMessage(ChatType type, Text message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Container> getOpenInventory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Container> openInventory(Inventory inventory, Cause cause) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean closeInventory(Cause cause) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getViewDistance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ChatVisibility getChatVisibility() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isChatColorsEnabled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<SkinPart> getDisplayedSkinParts() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PlayerConnection getConnection() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendResourcePack(ResourcePack pack) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TabList getTabList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void kick() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void kick(Text reason) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Scoreboard getScoreboard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSleepingIgnored() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSleepingIgnored(boolean sleepingIgnored) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Inventory getEnderChestInventory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean respawnPlayer() {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityType getType() {
        return SpongeEntityType.PLAYER;
    }

}
