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

import static org.spongepowered.clean.network.packet.ProtocolState.LOGIN;
import static org.spongepowered.clean.network.packet.ProtocolState.STATUS;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.SpongeEventFactory;
import org.spongepowered.clean.Constants;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.network.NetworkConnection;
import org.spongepowered.clean.network.NetworkConnection.ConnectionState;
import org.spongepowered.clean.network.packet.handshake.HandshakePacket;
import org.spongepowered.clean.network.packet.login.EncryptionResponsePacket;
import org.spongepowered.clean.network.packet.login.LoginStartPacket;
import org.spongepowered.clean.network.packet.play.serverbound.ChatMessagePacket;
import org.spongepowered.clean.network.packet.play.serverbound.ClientSettingsPacket;
import org.spongepowered.clean.network.packet.play.serverbound.PlayerLookPacket;
import org.spongepowered.clean.network.packet.play.serverbound.PlayerPositionAndLookPacket;
import org.spongepowered.clean.network.packet.play.serverbound.PlayerPositionPacket;
import org.spongepowered.clean.network.packet.status.PingPacket;
import org.spongepowered.clean.network.packet.status.PongPacket;
import org.spongepowered.clean.network.packet.status.ResponsePacket;

import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.BiConsumer;

import javax.crypto.Cipher;

public class InboundPacketHandlers {

    private static final BiConsumer<Packet, NetworkConnection> UNKNOWN = (p, c) -> {
        System.out.println("Received unsupported packet " + p);
    };

    public static final BiConsumer<Packet, NetworkConnection> HANDSHAKE = (p, c) -> {
        HandshakePacket packet = (HandshakePacket) p;
        if (packet.next_state == HandshakePacket.NextState.STATUS) {
            c.changeState(STATUS);
        } else if (packet.next_state == HandshakePacket.NextState.LOGIN) {
            if (packet.protocol_version != Constants.PROTOCOL_VERSION) {
                c.close();
            } else {
                c.changeState(LOGIN);
            }
        }
    };

    public static final BiConsumer<Packet, NetworkConnection> STATUS_REQUEST = (p, c) -> {
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
    };

    public static final BiConsumer<Packet, NetworkConnection> STATUS_PING = (p, c) -> {
        PongPacket response = new PongPacket(((PingPacket) p).nonce);
        c.sendPacket(response);
        c.close();
    };

    public static final BiConsumer<Packet, NetworkConnection> LOGIN_START = (p, c) -> {
        LoginStartPacket packet = (LoginStartPacket) p;
        c.setName(packet.name);
        // TODO support online mode
//        if (Sponge.getServer().getOnlineMode()) {
//            c.sendPacket(new EncryptionRequestPacket("", SGame.game.getNetworkManager().getServerKeyPair().getPublic(), c.getVerifyToken()));
//            c.updateConnState(ConnectionState.AUTHENTICATING);
//        } else {
        c.updateConnState(ConnectionState.COMPLETE_LOGIN);
//        }
    };

    public static final BiConsumer<Packet, NetworkConnection> LOGIN_ENCRYPTION_RESPONSE = (p, c) -> {
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
    };

    public static final BiConsumer<Packet, NetworkConnection> TELEPORT_CONFIRM = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> TAB_COMPLETE = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> CHAT_MESSAGE = (p, c) -> {
        ChatMessagePacket pk = (ChatMessagePacket) p;
    };

    public static final BiConsumer<Packet, NetworkConnection> CLIENT_STATUS = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> CLIENT_SETTINGS = (p, c) -> {
        ClientSettingsPacket pk = (ClientSettingsPacket) p;
        c.getPlayerEntity().setLocale(new Locale(pk.locale));
        c.getPlayerEntity().getNetConnection().setViewDistance(pk.viewDistance);
        // TODO: support other packet fields
        // TODO: post PlayerChangeClientSettingsEvent
    };

    public static final BiConsumer<Packet, NetworkConnection> CONFIRM_TRANSACTION = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> ENCHANT_ITEM = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> CLICK_WINDOW = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> CLOSE_WINDOW = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> PLUGIN_MESSAGE = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> USE_ENTITY = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> KEEP_ALIVE = (p, c) -> {
        // TODO: need to pass to net connection so it can know to not timeout
        // the connection
    };

    public static final BiConsumer<Packet, NetworkConnection> PLAYER_POSITION = (p, c) -> {
        PlayerPositionPacket pk = (PlayerPositionPacket) p;
        c.getPlayerEntity().updatePosition(pk.x, pk.y, pk.z, pk.onGround);
    };

    public static final BiConsumer<Packet, NetworkConnection> PLAYER_POSITION_AND_LOOK = (p, c) -> {
        PlayerPositionAndLookPacket pk = (PlayerPositionAndLookPacket) p;
        c.getPlayerEntity().updatePosition(pk.x, pk.y, pk.z, pk.yaw, pk.pitch, pk.onGround);
    };

    public static final BiConsumer<Packet, NetworkConnection> PLAYER_LOOK = (p, c) -> {
        PlayerLookPacket pk = (PlayerLookPacket) p;
        c.getPlayerEntity().updateLook(pk.yaw, pk.pitch);
    };

    public static final BiConsumer<Packet, NetworkConnection> PLAYER = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> VEHICLE_MOVE = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> STEER_BOAT = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> PLAYER_ABILITIES = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> PLAYER_DIGGING = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> ENTITY_ACTION = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> STEER_VEHICLE = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> RESOURCE_PACK_STATUS = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> HELD_ITEM_CHANGE = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> CREATIVE_INVENTORY_ACTION = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> UPDATE_SIGN = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> ANIMATION = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> SPECTATE = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> PLAYER_BLOCK_PLACEMENT = UNKNOWN;

    public static final BiConsumer<Packet, NetworkConnection> USE_ITEM = UNKNOWN;

}
