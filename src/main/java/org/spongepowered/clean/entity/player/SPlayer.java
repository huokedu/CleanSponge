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
import io.netty.channel.ChannelFuture;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.data.type.HandType;
import org.spongepowered.api.data.type.SkinPart;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.sound.SoundCategory;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.entity.living.player.tab.TabList;
import org.spongepowered.api.entity.projectile.Projectile;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.message.MessageChannelEvent.Chat;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.equipment.EquipmentType;
import org.spongepowered.api.item.inventory.type.CarriedInventory;
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
import org.spongepowered.api.text.chat.ChatTypes;
import org.spongepowered.api.text.chat.ChatVisibility;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.text.title.Title;
import org.spongepowered.api.util.Tristate;
import org.spongepowered.clean.entity.living.SLiving;
import org.spongepowered.clean.network.NetworkConnection;
import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.network.packet.play.clientbound.ChatMessagePacket;
import org.spongepowered.clean.world.SChunk;
import org.spongepowered.clean.world.SWorld;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class SPlayer extends SLiving implements Player {

    private final NetworkConnection connection;
    private GameMode gamemode = GameModes.SURVIVAL;
    private float yaw, pitch;
    private Locale locale = Locale.US;

    public SPlayer(SWorld world, UUID uid, NetworkConnection conn) {
        super(world);
        this.connection = conn;
    }

    public NetworkConnection getNetConnection() {
        return this.connection;
    }

    public ChannelFuture sendPacket(Packet p) {
        return this.connection.sendPacket(p);
    }

    public GameMode getGameMode() {
        return this.gamemode;
    }

    public void updatePosition(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        updatePosition(x, y, z, onGround);
        updateLook(yaw, pitch);
    }

    public void updateLook(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public void updatePosition(double x, double y, double z, boolean onGround) {
        double dx = this.x - x;
        double dy = this.y - y;
        double dz = this.z - z;
        double dist = dx * dx + dy * dy + dz * dz;
        double expected = this.vx * this.vx + this.vy * this.vy + this.vz * this.vz;
        if (dist > expected + 100) {
            // TODO moved too quickly
        }
        this.x = x;
        this.y = y;
        this.z = z;
        if (!onGround && this.vy > -1) {
            this.vy -= 0.1;
        } else if (onGround) {
            this.vy = 0;
        }
    }

    @Override
    public void serialUpdate() {
        this.connection.update();
        int px = this.bx >> 4;
        int pz = this.bz >> 4;

        this.x += this.vx;
        this.y += this.vy;
        this.z += this.vz;

        this.bx = (int) Math.floor(this.x);
        this.by = (int) Math.floor(this.y);
        this.bz = (int) Math.floor(this.z);

        if ((this.bx >> 4) != px || (this.bz >> 4) != pz) {
            this.chunk.removeEntity(this);
            SChunk chunk = this.world.getOrLoadChunk(this.bx >> 4, this.bz >> 4);
            this.chunk = chunk;
            this.chunk.addEntity(this);
            this.connection.updateChunks();
        }
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public <T extends Projectile> Optional<T> launchProjectile(Class<T> projectileClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Projectile> Optional<T> launchProjectile(Class<T> projectileClass, Vector3d velocity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ItemStack> getHelmet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<ItemStack> getChestplate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<ItemStack> getLeggings() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<ItemStack> getBoots() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBoots(ItemStack boots) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<ItemStack> getItemInHand(HandType handType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setItemInHand(HandType hand, ItemStack itemInHand) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canEquip(EquipmentType type) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canEquip(EquipmentType type, ItemStack equipment) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<ItemStack> getEquipped(EquipmentType type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean equip(EquipmentType type, ItemStack equipment) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public CarriedInventory<? extends Carrier> getInventory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        return this.connection.getName();
    }

    @Override
    public GameProfile getProfile() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isOnline() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<Player> getPlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<CommandSource> getCommandSource() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SubjectCollection getContainingCollection() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SubjectData getSubjectData() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SubjectData getTransientSubjectData() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Tristate getPermissionValue(Set<Context> contexts, String permission) {
        return Tristate.TRUE;
    }

    @Override
    public boolean isChildOf(Set<Context> contexts, Subject parent) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Subject> getParents(Set<Context> contexts) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<String> getOption(Set<Context> contexts, String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getIdentifier() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Context> getActiveContexts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MessageChannel getMessageChannel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setMessageChannel(MessageChannel channel) {
        // TODO Auto-generated method stub

    }

    @Override
    public void spawnParticles(ParticleEffect particleEffect, Vector3d position) {
        // TODO Auto-generated method stub

    }

    @Override
    public void spawnParticles(ParticleEffect particleEffect, Vector3d position, int radius) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume, double pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSound(SoundType sound, SoundCategory category, Vector3d position, double volume, double pitch, double minVolume) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendTitle(Title title) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendBookView(BookView bookView) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendBlockChange(int x, int y, int z, BlockState state) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetBlockChange(int x, int y, int z) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendMessage(Text message) {
        sendMessage(ChatTypes.CHAT, message);
    }

    @Override
    public void sendMessage(ChatType type, Text message) {
        String json = TextSerializers.JSON.serialize(message);
        this.connection.sendPacket(new ChatMessagePacket(json, type));
    }

    @Override
    public Optional<Container> getOpenInventory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Container> openInventory(Inventory inventory, Cause cause) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean closeInventory(Cause cause) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getViewDistance() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ChatVisibility getChatVisibility() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isChatColorsEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<SkinPart> getDisplayedSkinParts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlayerConnection getConnection() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendResourcePack(ResourcePack pack) {
        // TODO Auto-generated method stub

    }

    @Override
    public TabList getTabList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void kick() {
        // TODO Auto-generated method stub

    }

    @Override
    public void kick(Text reason) {
        // TODO Auto-generated method stub

    }

    @Override
    public Scoreboard getScoreboard() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isSleepingIgnored() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setSleepingIgnored(boolean sleepingIgnored) {
        // TODO Auto-generated method stub

    }

    @Override
    public Inventory getEnderChestInventory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean respawnPlayer() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setCurrentChunk(SChunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public EntityType getType() {
        return EntityTypes.PLAYER;
    }

    @Override
    public Chat simulateChat(Text message, Cause cause) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Entity> getSpectatorTarget() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSpectatorTarget(Entity entity) {
        // TODO Auto-generated method stub
        
    }

}
