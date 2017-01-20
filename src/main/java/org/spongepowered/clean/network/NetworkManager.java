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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.entity.player.SPlayer;
import org.spongepowered.clean.network.netty.PacketDecoder;
import org.spongepowered.clean.network.netty.PacketEncoder;
import org.spongepowered.clean.network.netty.PacketHandler;
import org.spongepowered.clean.network.netty.PacketLengthAppender;
import org.spongepowered.clean.network.netty.PacketSplitter;
import org.spongepowered.clean.scheduler.CoreScheduler;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NetworkManager {

    private final List<NetworkConnection> activeConnections = Collections.synchronizedList(Lists.newArrayList());
    private ImmutableList<SPlayer> players = null;

    private ChannelFuture channel;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Random rand = new Random();
    private KeyPair serverkeys;

    public NetworkManager() {

    }

    public KeyPair getServerKeyPair() {
        return this.serverkeys;
    }

    public byte[] generateVerifyToken() {
        byte[] token = new byte[4];
        this.rand.nextBytes(token);
        return token;
    }

    public void startListening(int port) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            this.serverkeys = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            CoreScheduler.emergencyShutdown(e);
        }

        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(this.bossGroup, this.workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast("splitter", new PacketSplitter());
                ch.pipeline().addLast("decoder", new PacketDecoder());
                ch.pipeline().addLast("length_appender", new PacketLengthAppender());
                ch.pipeline().addLast("encoder", new PacketEncoder());
                NetworkConnection conn = new NetworkConnection();
                NetworkManager.this.activeConnections.add(conn);
                ch.pipeline().addLast("handler", new PacketHandler(conn));
            }
        }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

        this.channel = b.bind(port).syncUninterruptibly();
        SGame.getLogger().info("Now listening on port " + port);
    }

    public void shutdown() {
        try {
            this.bossGroup.shutdownGracefully().sync();
            this.workerGroup.shutdownGracefully().sync();
            this.channel.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public Collection<SPlayer> getConnectedPlayers() {
        if (this.players == null) {
            ImmutableList.Builder<SPlayer> bld = ImmutableList.builder();
            for (NetworkConnection conn : this.activeConnections) {
                if (conn.getPlayerEntity() != null) {
                    bld.add(conn.getPlayerEntity());
                }
            }
            this.players = bld.build();
        }
        return this.players;
    }

    public Collection<NetworkConnection> getActiveConnections() {
        return this.activeConnections;
    }

}
