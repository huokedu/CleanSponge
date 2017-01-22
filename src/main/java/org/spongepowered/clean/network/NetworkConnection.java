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
package org.spongepowered.clean.network;

import com.google.common.base.Charsets;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.AttributeKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.SServer;
import org.spongepowered.clean.entity.player.SPlayer;
import org.spongepowered.clean.network.netty.PacketCompressor;
import org.spongepowered.clean.network.netty.PacketDecompressor;
import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.network.packet.ProtocolState;
import org.spongepowered.clean.network.packet.login.LoginSuccessPacket;
import org.spongepowered.clean.network.packet.login.SetCompressionPacket;
import org.spongepowered.clean.world.SWorld;

import java.util.UUID;

public class NetworkConnection {

    public static enum ConnectionState {
        HANDSHAKING,

        AUTHENTICATING,
        COMPLETE_LOGIN,

        JOINING,
        PLAYING,
    }

    public static final AttributeKey<ProtocolState> PROTOCOL_STATE_KEY = AttributeKey.valueOf("protocol_state");

    private Channel channel;
    private ProtocolState state;

    private String name;
    private UUID uid;
    private SPlayer playerEntity = null;
    private byte[] verify_token;

    private ConnectionState conn_state;

    public NetworkConnection() {
        this.state = ProtocolState.HANDSHAKING;
        this.conn_state = ConnectionState.HANDSHAKING;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
        this.channel.attr(PROTOCOL_STATE_KEY).set(this.state);
    }

    public ProtocolState getProtocolState() {
        return this.state;
    }

    public void changeState(ProtocolState state) {
        this.state = state;
        this.channel.attr(PROTOCOL_STATE_KEY).set(this.state);
    }

    public ConnectionState getConnectionState() {
        return this.conn_state;
    }

    public void updateConnState(ConnectionState state) {
        this.conn_state = state;
    }

    public void update() {
        if (this.conn_state == ConnectionState.COMPLETE_LOGIN) {
            if (this.uid == null) {
                // offline mode
                this.uid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + this.name).getBytes(Charsets.UTF_8));
            }
            // TODO check bans
            // TODO check whitelist
            // TODO check server full
            int compress_threshold = ((SServer) Sponge.getServer()).getServerProperties().network_compression_threshold;
            if (compress_threshold > 0) {
                sendPacket(new SetCompressionPacket(compress_threshold)).addListener(new ChannelFutureListener() {

                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        System.out.println("compressing");
                        NetworkConnection.this.channel.pipeline().addBefore("decoder", "decompressor", new PacketDecompressor());
                        NetworkConnection.this.channel.pipeline().addBefore("encoder", "compressor", new PacketCompressor(compress_threshold));
                        System.out.println(NetworkConnection.this.channel.pipeline());
                    }
                });
            }
            sendPacket(new LoginSuccessPacket(this.uid, this.name));
            changeState(ProtocolState.PLAY);
            this.conn_state = ConnectionState.JOINING;

            System.out.println("Joining world");
            SWorld world = (SWorld) Sponge.getServer().getWorld(Sponge.getServer().getDefaultWorldName()).get();
            this.playerEntity = new SPlayer(world, this.uid, this);
            world.addJoiningPlayer(this.playerEntity);
        }
        // TODO timeout if too long authenticating
    }

    public ChannelFuture sendPacket(Packet packet) {
        if (this.channel.isOpen()) {
            return this.channel.writeAndFlush(packet);
        }
        throw new IllegalStateException("channel closed");
    }

    public void close() {
        if (this.channel != null && this.channel.isOpen()) {
            this.channel.close();
        }
    }

    public SPlayer getPlayerEntity() {
        return this.playerEntity;
    }

    public byte[] getVerifyToken() {
        if (this.verify_token == null) {
            this.verify_token = SGame.game.getNetworkManager().generateVerifyToken();
        }
        return this.verify_token;
    }

    public void setName(String name) {
        System.out.println("Set name to " + name);
        this.name = name;
    }

}
