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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.spongepowered.api.Sponge;
import org.spongepowered.clean.Constants;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.network.NetworkConnection;
import org.spongepowered.clean.network.NetworkConnection.ConnectionState;
import org.spongepowered.clean.network.packet.handshake.HandshakePacket;
import org.spongepowered.clean.network.packet.login.EncryptionRequestPacket;
import org.spongepowered.clean.network.packet.login.EncryptionResponsePacket;
import org.spongepowered.clean.network.packet.login.LoginStartPacket;
import org.spongepowered.clean.network.packet.status.PingPacket;
import org.spongepowered.clean.network.packet.status.PongPacket;
import org.spongepowered.clean.network.packet.status.RequestPacket;
import org.spongepowered.clean.network.packet.status.ResponsePacket;

import java.security.PrivateKey;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import javax.crypto.Cipher;

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
            if(!Arrays.equals(c.getVerifyToken(), returned_token)) {
                c.close();
                return;
            }
            // TODO get session data from mojang
        } catch (Exception e) {
            e.printStackTrace();
        }
    });

    private ProtocolState                         state;
    private int                                   id;
    private Supplier<Packet>                      builder;
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
