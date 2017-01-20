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

import java.security.PrivateKey;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import javax.crypto.Cipher;

import org.spongepowered.api.Sponge;
import org.spongepowered.clean.Constants;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.network.NetworkConnection;
import org.spongepowered.clean.network.NetworkConnection.ConnectionState;
import org.spongepowered.clean.network.packet.handshake.HandshakePacket;
import org.spongepowered.clean.network.packet.login.EncryptionRequestPacket;
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
import org.spongepowered.clean.network.packet.play.serverbound.VehicleMovePacket;
import org.spongepowered.clean.network.packet.status.PingPacket;
import org.spongepowered.clean.network.packet.status.PongPacket;
import org.spongepowered.clean.network.packet.status.RequestPacket;
import org.spongepowered.clean.network.packet.status.ResponsePacket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public enum InboundPackets {

    HANDSHAKE(HANDSHAKING, 0x00, () -> new HandshakePacket(), (p, c) -> {
        HandshakePacket packet = (HandshakePacket) p;
        if (packet.protocol_version != Constants.PROTOCOL_VERSION) {
            c.close();
        }
        if (packet.next_state == HandshakePacket.NextState.STATUS) {
            c.changeState(STATUS);
        } else if (packet.next_state == HandshakePacket.NextState.LOGIN) {
            c.changeState(LOGIN);
        }
    }),

    STATUS_REQUEST(STATUS, 0x00, () -> new RequestPacket(), (p, c) -> {
        JsonObject json = new JsonObject();
        JsonObject version = new JsonObject();
        version.addProperty("name", Constants.MC_VERSION);
        version.addProperty("protocol", Constants.PROTOCOL_VERSION);
        json.add("version", version);
        JsonObject players = new JsonObject();
        players.addProperty("max", Sponge.getServer().getMaxPlayers());
        players.addProperty("online", Sponge.getServer().getOnlinePlayers().size());
        json.add("players", players);
        JsonObject desc = new JsonObject();
        // TODO use text serializer once implemented
        desc.addProperty("text", "A cleanroom sponge server");
        json.add("description", desc);
        Gson gson = new Gson();
        c.sendPacket(new ResponsePacket(gson.toJson(json)));
    }),

    STATUS_PING(STATUS, 0x01, () -> new PingPacket(), (p, c) -> {
        PongPacket response = (PongPacket) p;
        response.nonce = ((PingPacket) p).nonce;
        c.sendPacket(response);
        c.close();
    }),

    LOGIN_START(LOGIN, 0x00, () -> new LoginStartPacket(), (p, c) -> {
        LoginStartPacket packet = (LoginStartPacket) p;
        c.setName(packet.name);
        if (Sponge.getServer().getOnlineMode()) {
            c.sendPacket(new EncryptionRequestPacket("", SGame.game.getNetworkManager().getServerKeyPair().getPublic(), c.getVerifyToken()));
            c.updateConnState(ConnectionState.AUTHENTICATING);
        } else {
            c.updateConnState(ConnectionState.COMPLETE_LOGIN);
        }
    }),

    LOGIN_ENCRYPTION_RESPONSE(LOGIN, 0x00, () -> new EncryptionResponsePacket(), (p, c) -> {
        EncryptionResponsePacket packet = (EncryptionResponsePacket) p;
        PrivateKey key = SGame.game.getNetworkManager().getServerKeyPair().getPrivate();
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(2, key);
            byte[] returned_token = cipher.doFinal(packet.verify_token);
            if (!Arrays.equals(c.getVerifyToken(), returned_token)) {
                c.close();
                return;
            }
            // TODO get session data from mojang
        } catch (Exception e) {
            e.printStackTrace();
        }
    }),

    TELEPORT_CONFIRM(PLAY, 0x00, () -> new TeleportConfirmPacket(), (p, c) -> {
    }),

    TAB_COMPLETE(PLAY, 0x01, () -> new TabCompletePacket(), (p, c) -> {
    }),

    CHAT_MESSAGE(PLAY, 0x02, () -> new ChatMessagePacket(), (p, c) -> {
    }),

    CLIENT_STATUS(PLAY, 0x03, () -> new ClientStatusPacket(), (p, c) -> {
    }),

    CLIENT_SETTINGS(PLAY, 0x04, () -> new ClientSettingsPacket(), (p, c) -> {
    }),

    CONFIRM_TRANSACTION(PLAY, 0x05, () -> new ConfirmTransactionPacket(), (p, c) -> {
    }),

    ENCHANT_ITEM(PLAY, 0x06, () -> new EnchantItemPacket(), (p, c) -> {
    }),

    CLICK_WINDOW(PLAY, 0x07, () -> new ClickWindowPacket(), (p, c) -> {
    }),

    CLOSE_WINDOW(PLAY, 0x08, () -> new CloseWindowPacket(), (p, c) -> {
    }),

    PLUGIN_MESSAGE(PLAY, 0x09, () -> new PluginMessagePacket(), (p, c) -> {
    }),

    USE_ENTITY(PLAY, 0x0A, () -> new UseEntityPacket(), (p, c) -> {
    }),

    KEEP_ALIVE(PLAY, 0x0B, () -> new KeepAlivePacket(), (p, c) -> {
    }),

    PLAYER_POSITION(PLAY, 0x0C, () -> new PlayerPositionPacket(), (p, c) -> {
    }),

    PLAYER_POSITION_AND_LOOK(PLAY, 0x0D, () -> new PlayerPositionAndLookPacket(), (p, c) -> {
    }),

    PLAYER_LOOK(PLAY, 0x0E, () -> new PlayerLookPacket(), (p, c) -> {
    }),

    PLAYER(PLAY, 0x0F, () -> new PlayerPacket(), (p, c) -> {
    }),

    VEHICLE_MOVE(PLAY, 0x10, () -> new VehicleMovePacket(), (p, c) -> {
    }),

    STEER_BOAT(PLAY, 0x11, () -> new SteerBoatPacket(), (p, c) -> {
    }),

    PLAYER_ABILITIES(PLAY, 0x12, () -> new PlayerAbilitiesPacket(), (p, c) -> {
    }),

    PLAYER_DIGGING(PLAY, 0x13, () -> new PlayerDiggingPacket(), (p, c) -> {
    }),

    ENTITY_ACTION(PLAY, 0x14, () -> new EntityActionPacket(), (p, c) -> {
    }),

    STEER_VEHICLE(PLAY, 0x15, () -> new SteerVehiclePacket(), (p, c) -> {
    }),

    RESOURCE_PACK_STATUS(PLAY, 0x16, () -> new ResourcePackStatusPacket(), (p, c) -> {
    }),

    HELD_ITEM_CHANGE(PLAY, 0x17, () -> new HeldItemChangePacket(), (p, c) -> {
    }),

    CREATIVE_INVENTORY_ACTION(PLAY, 0x18, () -> new CreativeInventoryActionPacket(), (p, c) -> {
    }),

    UPDATE_SIGN(PLAY, 0x19, () -> new UpdateSignPacket(), (p, c) -> {
    }),

    ANIMATION(PLAY, 0x1A, () -> new AnimationPacket(), (p, c) -> {
    }),

    SPECTATE(PLAY, 0x1B, () -> new SpectatePacket(), (p, c) -> {
    }),

    PLAYER_BLOCK_PLACEMENT(PLAY, 0x1C, () -> new PlayerBlockPlacementPacket(), (p, c) -> {
    });

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
        InboundPackets[] play_packets = new InboundPackets[0];
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
