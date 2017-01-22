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
package org.spongepowered.clean.network.packet;

import static org.spongepowered.clean.network.packet.ProtocolState.HANDSHAKING;
import static org.spongepowered.clean.network.packet.ProtocolState.LOGIN;
import static org.spongepowered.clean.network.packet.ProtocolState.PLAY;
import static org.spongepowered.clean.network.packet.ProtocolState.STATUS;

import org.spongepowered.clean.SGame;
import org.spongepowered.clean.network.NetworkConnection;
import org.spongepowered.clean.network.packet.handshake.HandshakePacket;
import org.spongepowered.clean.network.packet.login.EncryptionResponsePacket;
import org.spongepowered.clean.network.packet.login.LoginStartPacket;
import org.spongepowered.clean.network.packet.play.serverbound.AnimationPacket;
import org.spongepowered.clean.network.packet.play.serverbound.ChatMessagePacket;
import org.spongepowered.clean.network.packet.play.serverbound.ClickWindowPacket;
import org.spongepowered.clean.network.packet.play.serverbound.ClientSettingsPacket;
import org.spongepowered.clean.network.packet.play.serverbound.ClientStatusPacket;
import org.spongepowered.clean.network.packet.play.serverbound.CloseWindowPacket;
import org.spongepowered.clean.network.packet.play.serverbound.ConfirmTransactionPacket;
import org.spongepowered.clean.network.packet.play.serverbound.CreativeInventoryActionPacket;
import org.spongepowered.clean.network.packet.play.serverbound.EnchantItemPacket;
import org.spongepowered.clean.network.packet.play.serverbound.EntityActionPacket;
import org.spongepowered.clean.network.packet.play.serverbound.HeldItemChangePacket;
import org.spongepowered.clean.network.packet.play.serverbound.KeepAlivePacket;
import org.spongepowered.clean.network.packet.play.serverbound.PlayerAbilitiesPacket;
import org.spongepowered.clean.network.packet.play.serverbound.PlayerBlockPlacementPacket;
import org.spongepowered.clean.network.packet.play.serverbound.PlayerDiggingPacket;
import org.spongepowered.clean.network.packet.play.serverbound.PlayerLookPacket;
import org.spongepowered.clean.network.packet.play.serverbound.PlayerPacket;
import org.spongepowered.clean.network.packet.play.serverbound.PlayerPositionAndLookPacket;
import org.spongepowered.clean.network.packet.play.serverbound.PlayerPositionPacket;
import org.spongepowered.clean.network.packet.play.serverbound.PluginMessagePacket;
import org.spongepowered.clean.network.packet.play.serverbound.ResourcePackStatusPacket;
import org.spongepowered.clean.network.packet.play.serverbound.SpectatePacket;
import org.spongepowered.clean.network.packet.play.serverbound.SteerBoatPacket;
import org.spongepowered.clean.network.packet.play.serverbound.SteerVehiclePacket;
import org.spongepowered.clean.network.packet.play.serverbound.TabCompletePacket;
import org.spongepowered.clean.network.packet.play.serverbound.TeleportConfirmPacket;
import org.spongepowered.clean.network.packet.play.serverbound.UpdateSignPacket;
import org.spongepowered.clean.network.packet.play.serverbound.UseEntityPacket;
import org.spongepowered.clean.network.packet.play.serverbound.UseItemPacket;
import org.spongepowered.clean.network.packet.play.serverbound.VehicleMovePacket;
import org.spongepowered.clean.network.packet.status.PingPacket;
import org.spongepowered.clean.network.packet.status.RequestPacket;

import java.util.EnumMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public enum InboundPackets {

    HANDSHAKE(HANDSHAKING, 0x00, () -> new HandshakePacket(), InboundPacketHandlers.HANDSHAKE),
    STATUS_REQUEST(STATUS, 0x00, () -> new RequestPacket(), InboundPacketHandlers.STATUS_REQUEST),
    STATUS_PING(STATUS, 0x01, () -> new PingPacket(), InboundPacketHandlers.STATUS_PING),
    LOGIN_START(LOGIN, 0x00, () -> new LoginStartPacket(), InboundPacketHandlers.LOGIN_START),
    LOGIN_ENCRYPTION_RESPONSE(LOGIN, 0x00, () -> new EncryptionResponsePacket(), InboundPacketHandlers.LOGIN_ENCRYPTION_RESPONSE),
    TELEPORT_CONFIRM(PLAY, 0x00, () -> new TeleportConfirmPacket(), InboundPacketHandlers.TELEPORT_CONFIRM),
    TAB_COMPLETE(PLAY, 0x01, () -> new TabCompletePacket(), InboundPacketHandlers.TAB_COMPLETE),
    CHAT_MESSAGE(PLAY, 0x02, () -> new ChatMessagePacket(), InboundPacketHandlers.CHAT_MESSAGE),
    CLIENT_STATUS(PLAY, 0x03, () -> new ClientStatusPacket(), InboundPacketHandlers.CLIENT_STATUS),
    CLIENT_SETTINGS(PLAY, 0x04, () -> new ClientSettingsPacket(), InboundPacketHandlers.CLIENT_SETTINGS),
    CONFIRM_TRANSACTION(PLAY, 0x05, () -> new ConfirmTransactionPacket(), InboundPacketHandlers.CONFIRM_TRANSACTION),
    ENCHANT_ITEM(PLAY, 0x06, () -> new EnchantItemPacket(), InboundPacketHandlers.ENCHANT_ITEM),
    CLICK_WINDOW(PLAY, 0x07, () -> new ClickWindowPacket(), InboundPacketHandlers.CLICK_WINDOW),
    CLOSE_WINDOW(PLAY, 0x08, () -> new CloseWindowPacket(), InboundPacketHandlers.CLOSE_WINDOW),
    PLUGIN_MESSAGE(PLAY, 0x09, () -> new PluginMessagePacket(), InboundPacketHandlers.PLUGIN_MESSAGE),
    USE_ENTITY(PLAY, 0x0A, () -> new UseEntityPacket(), InboundPacketHandlers.USE_ENTITY),
    KEEP_ALIVE(PLAY, 0x0B, () -> new KeepAlivePacket(), InboundPacketHandlers.KEEP_ALIVE),
    PLAYER_POSITION(PLAY, 0x0C, () -> new PlayerPositionPacket(), InboundPacketHandlers.PLAYER_POSITION),
    PLAYER_POSITION_AND_LOOK(PLAY, 0x0D, () -> new PlayerPositionAndLookPacket(), InboundPacketHandlers.PLAYER_POSITION_AND_LOOK),
    PLAYER_LOOK(PLAY, 0x0E, () -> new PlayerLookPacket(), InboundPacketHandlers.PLAYER_LOOK),
    PLAYER(PLAY, 0x0F, () -> new PlayerPacket(), InboundPacketHandlers.PLAYER),
    VEHICLE_MOVE(PLAY, 0x10, () -> new VehicleMovePacket(), InboundPacketHandlers.VEHICLE_MOVE),
    STEER_BOAT(PLAY, 0x11, () -> new SteerBoatPacket(), InboundPacketHandlers.STEER_BOAT),
    PLAYER_ABILITIES(PLAY, 0x12, () -> new PlayerAbilitiesPacket(), InboundPacketHandlers.PLAYER_ABILITIES),
    PLAYER_DIGGING(PLAY, 0x13, () -> new PlayerDiggingPacket(), InboundPacketHandlers.PLAYER_DIGGING),
    ENTITY_ACTION(PLAY, 0x14, () -> new EntityActionPacket(), InboundPacketHandlers.ENTITY_ACTION),
    STEER_VEHICLE(PLAY, 0x15, () -> new SteerVehiclePacket(), InboundPacketHandlers.STEER_VEHICLE),
    RESOURCE_PACK_STATUS(PLAY, 0x16, () -> new ResourcePackStatusPacket(), InboundPacketHandlers.RESOURCE_PACK_STATUS),
    HELD_ITEM_CHANGE(PLAY, 0x17, () -> new HeldItemChangePacket(), InboundPacketHandlers.HELD_ITEM_CHANGE),
    CREATIVE_INVENTORY_ACTION(PLAY, 0x18, () -> new CreativeInventoryActionPacket(), InboundPacketHandlers.CREATIVE_INVENTORY_ACTION),
    UPDATE_SIGN(PLAY, 0x19, () -> new UpdateSignPacket(), InboundPacketHandlers.UPDATE_SIGN),
    ANIMATION(PLAY, 0x1A, () -> new AnimationPacket(), InboundPacketHandlers.ANIMATION),
    SPECTATE(PLAY, 0x1B, () -> new SpectatePacket(), InboundPacketHandlers.SPECTATE),
    PLAYER_BLOCK_PLACEMENT(PLAY, 0x1C, () -> new PlayerBlockPlacementPacket(), InboundPacketHandlers.PLAYER_BLOCK_PLACEMENT),
    USE_ITEM(PLAY, 0x1D, () -> new UseItemPacket(), InboundPacketHandlers.USE_ITEM);

    private ProtocolState state;
    private int id;
    private Supplier<Packet> builder;
    private BiConsumer<Packet, NetworkConnection> handler;

    InboundPackets(ProtocolState state, int id, Supplier<Packet> builder, BiConsumer<Packet, NetworkConnection> handler) {
        this.state = state;
        this.id = id;
        this.builder = builder;
        this.handler = handler;
    }

    public ProtocolState getState() {
        return this.state;
    }

    public int getId() {
        return this.id;
    }

    public Supplier<Packet> getBuilder() {
        return this.builder;
    }

    public BiConsumer<Packet, NetworkConnection> getHandler() {
        return this.handler;
    }

    private static final EnumMap<ProtocolState, InboundPackets[]> states = new EnumMap<>(ProtocolState.class);

    static {
        InboundPackets[] handshaking_packets = new InboundPackets[1];
        handshaking_packets[0] = InboundPackets.HANDSHAKE;
        states.put(HANDSHAKING, handshaking_packets);
        // TODO add handler for legacy ping
        InboundPackets[] status_packets = new InboundPackets[2];
        status_packets[0] = InboundPackets.STATUS_REQUEST;
        status_packets[1] = InboundPackets.STATUS_PING;
        states.put(STATUS, status_packets);
        InboundPackets[] login_packets = new InboundPackets[2];
        login_packets[0] = InboundPackets.LOGIN_START;
        login_packets[1] = InboundPackets.LOGIN_ENCRYPTION_RESPONSE;
        states.put(LOGIN, login_packets);
        InboundPackets[] play_packets = new InboundPackets[30];
        play_packets[0] = InboundPackets.TELEPORT_CONFIRM;
        play_packets[1] = InboundPackets.TAB_COMPLETE;
        play_packets[2] = InboundPackets.CHAT_MESSAGE;
        play_packets[3] = InboundPackets.CLIENT_STATUS;
        play_packets[4] = InboundPackets.CLIENT_SETTINGS;
        play_packets[5] = InboundPackets.CONFIRM_TRANSACTION;
        play_packets[6] = InboundPackets.ENCHANT_ITEM;
        play_packets[7] = InboundPackets.CLICK_WINDOW;
        play_packets[8] = InboundPackets.CLOSE_WINDOW;
        play_packets[9] = InboundPackets.PLUGIN_MESSAGE;
        play_packets[10] = InboundPackets.USE_ENTITY;
        play_packets[11] = InboundPackets.KEEP_ALIVE;
        play_packets[12] = InboundPackets.PLAYER_POSITION;
        play_packets[13] = InboundPackets.PLAYER_POSITION_AND_LOOK;
        play_packets[14] = InboundPackets.PLAYER_LOOK;
        play_packets[15] = InboundPackets.PLAYER;
        play_packets[16] = InboundPackets.VEHICLE_MOVE;
        play_packets[17] = InboundPackets.STEER_BOAT;
        play_packets[18] = InboundPackets.PLAYER_ABILITIES;
        play_packets[19] = InboundPackets.PLAYER_DIGGING;
        play_packets[20] = InboundPackets.ENTITY_ACTION;
        play_packets[21] = InboundPackets.STEER_VEHICLE;
        play_packets[22] = InboundPackets.RESOURCE_PACK_STATUS;
        play_packets[23] = InboundPackets.HELD_ITEM_CHANGE;
        play_packets[24] = InboundPackets.CREATIVE_INVENTORY_ACTION;
        play_packets[25] = InboundPackets.UPDATE_SIGN;
        play_packets[26] = InboundPackets.ANIMATION;
        play_packets[27] = InboundPackets.SPECTATE;
        play_packets[28] = InboundPackets.PLAYER_BLOCK_PLACEMENT;
        play_packets[29] = InboundPackets.USE_ITEM;
        states.put(PLAY, play_packets);
    }

    public static InboundPackets get(ProtocolState state, int id) {
        InboundPackets[] packets = states.get(state);
        if (id > packets.length) {
            SGame.getLogger().error("Unknown packet id {} while in state {}", id, state.name());
            return null;
        }
        return states.get(state)[id];
    }

}
