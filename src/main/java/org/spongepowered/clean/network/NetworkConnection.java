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

import com.flowpowered.math.vector.Vector3i;
import com.google.common.base.Charsets;
import com.google.common.collect.Queues;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.AttributeKey;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.clean.Constants;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.SServer;
import org.spongepowered.clean.entity.player.SPlayer;
import org.spongepowered.clean.network.netty.PacketCompressor;
import org.spongepowered.clean.network.netty.PacketDecompressor;
import org.spongepowered.clean.network.packet.InboundPackets;
import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.network.packet.ProtocolState;
import org.spongepowered.clean.network.packet.login.LoginSuccessPacket;
import org.spongepowered.clean.network.packet.login.SetCompressionPacket;
import org.spongepowered.clean.network.packet.play.clientbound.ChatMessagePacket;
import org.spongepowered.clean.network.packet.play.clientbound.ChunkDataPacket;
import org.spongepowered.clean.network.packet.play.clientbound.HeldItemChangePacket;
import org.spongepowered.clean.network.packet.play.clientbound.JoinGamePacket;
import org.spongepowered.clean.network.packet.play.clientbound.KeepAlivePacket;
import org.spongepowered.clean.network.packet.play.clientbound.PlayerAbilitiesPacket;
import org.spongepowered.clean.network.packet.play.clientbound.PlayerPositionAndLookPacket;
import org.spongepowered.clean.network.packet.play.clientbound.PluginMessagePacket;
import org.spongepowered.clean.network.packet.play.clientbound.ServerDifficultyPacket;
import org.spongepowered.clean.network.packet.play.clientbound.SetExperiencePacket;
import org.spongepowered.clean.network.packet.play.clientbound.SpawnPositionPacket;
import org.spongepowered.clean.network.packet.play.clientbound.TimeUpdatePacket;
import org.spongepowered.clean.network.packet.play.clientbound.UnloadChunkPacket;
import org.spongepowered.clean.network.packet.play.clientbound.UpdateHealthPacket;
import org.spongepowered.clean.network.packet.play.clientbound.WindowItemsPacket;
import org.spongepowered.clean.world.SChunk;
import org.spongepowered.clean.world.SWorld;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

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
    private SPlayer player = null;
    private byte[] verify_token;

    private ConnectionState conn_state;
    private LongSet chunks = new LongOpenHashSet();
    private LongSet chunks_b = new LongOpenHashSet();
    private int viewDistance = 8;
    private int timeout = 0;
    // TODO track keepalives sent and timeout if not returned

    private ConcurrentLinkedQueue<Packet> queue = Queues.newConcurrentLinkedQueue();

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

    public void queuePacket(Packet packet) {
        this.queue.offer(packet);
    }

    public void update() {
        this.timeout++;
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
            this.player = new SPlayer(world, this.uid, this);
            world.addJoiningPlayer(this.player);
        } else if (this.conn_state == ConnectionState.PLAYING) {
            if (this.timeout == 15) {
                int val = (int) System.currentTimeMillis();
                sendPacket(new KeepAlivePacket(val));
            }
            Packet packet = this.queue.poll();
            while (packet != null) {
                InboundPackets type = InboundPackets.get(this.state, packet.id);
//                System.out.println("Packet in: " + Integer.toHexString(packet.id));
                type.getHandler().accept(packet, this);
                packet = this.queue.poll();
            }
        }
        // TODO timeout if too long authenticating
    }

    public ChannelFuture sendPacket(Packet packet) {
        this.timeout = 0;
        if (this.channel.isOpen()) {
            return this.channel.writeAndFlush(packet);
        }
        return null;
    }

    public void close() {
        if (this.channel != null && this.channel.isOpen()) {
            this.channel.close();
        }
    }

    public SPlayer getPlayerEntity() {
        return this.player;
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

    public void joinGame() {
        updateConnState(ConnectionState.PLAYING);
        SWorld world = (SWorld) this.player.getWorld();
        // TODO use actual values
        this.player.sendPacket(new JoinGamePacket(this.player.getEntityId(), GameModes.CREATIVE, 0, world.getProperties().getDifficulty(),
                (byte) Sponge.getServer().getMaxPlayers(), "default", false));
        this.player.sendPacket(PluginMessagePacket.createBrandPacket(Constants.SERVER_BRAND));
        this.player.sendPacket(new ServerDifficultyPacket(world.getDifficulty()));
        this.player.sendPacket(new PlayerAbilitiesPacket((byte) 0, 1, 0));
        this.player.sendPacket(new HeldItemChangePacket((byte) 0));
        this.player.sendPacket(new ChatMessagePacket("{\"text\":\"Welcome!\"}", ChatMessagePacket.Position.CHAT));
        updateChunks();
        Vector3i spawnpos = world.getSpawnLocation().getBlockPosition();
        this.player.sendPacket(new PlayerPositionAndLookPacket(spawnpos.getX(), spawnpos.getY(), spawnpos.getZ(), 0, 0, (byte) 0, 1));
        this.player.sendPacket(new TimeUpdatePacket(world.getProperties().getTotalTime(), world.getProperties().getWorldTime()));
        this.player.sendPacket(new SpawnPositionPacket(5, 10, 5));
        this.player.sendPacket(new WindowItemsPacket((byte) 0, (short) 46));
        updateHealth();
        updateExperience();
    }

    public void updateChunks() {
        SWorld world = (SWorld) this.player.getWorld();
        this.chunks_b.clear();
        this.chunks_b.addAll(this.chunks);
        for (int x = this.player.getChunkX() - this.viewDistance; x <= this.player.getChunkX() + this.viewDistance; x++) {
            for (int z = this.player.getChunkZ() + this.viewDistance; z >= this.player.getChunkZ() - this.viewDistance; z--) {
                long key = ((x & 0xFFFFFFFFL) << 32) | (z & 0xFFFFFFFFL);
//                System.out.println("Checking chunk " + x + " " + z + " " + key);
                if (!this.chunks.contains(key)) {
                    SChunk c = world.getOrLoadChunk(x, z);
                    this.player.sendPacket(new ChunkDataPacket(c, true, 0));
//                    System.out.println("Sending chunk " + x + " " + z);
//                    System.out.println("wout " + (c.getBlockMin().getX() >> 4) + " " + (c.getBlockMin().getZ() >> 4) + " " + c.getBlockMin());
                    c.addViewer();
                    this.chunks.add(key);
                } else {
                    this.chunks_b.rem(key);
                }
            }
        }
        for (long rem : this.chunks_b) {
            int x = (int) ((rem >>> 32) & 0xFFFFFFFF);
            int z = (int) (rem & 0xFFFFFFFF);
            SChunk c = world.getLoadedChunk(x, z);
            if (c != null) {
                c.removeViewer();
            }
//            System.out.println("Unloading chunk " + x + " " + z);
            this.player.sendPacket(new UnloadChunkPacket(x, z));
            this.chunks.rem(rem);
        }
    }

    public void updateHealth() {
        this.player.sendPacket(new UpdateHealthPacket(20, 20, 4));
    }

    public void updateExperience() {
        this.player.sendPacket(new SetExperiencePacket(0, 0, 0));
    }

}
