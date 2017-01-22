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
package org.spongepowered.clean.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.zip.Deflater;

import org.spongepowered.clean.util.ByteBufUtil;

public class PacketCompressor extends MessageToByteEncoder<ByteBuf> {

    private final Deflater deflator   = new Deflater();
    private final byte[]   buffer     = new byte[4096];
    private byte[]         dataBuffer = new byte[1024];
    private int            threshold;

    public PacketCompressor(int threshold) {
        this.threshold = threshold;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        int length = msg.readableBytes();
        if (msg.readableBytes() < this.threshold) {
            ByteBufUtil.writeVarInt(out, 0);
            out.writeBytes(msg);
            return;
        }
        System.out.println("compressing");
        if (length > this.dataBuffer.length) {
            this.dataBuffer = new byte[length];
        }
        msg.readBytes(this.dataBuffer, 0, length);
        ByteBufUtil.writeVarInt(out, length);
        this.deflator.setInput(this.dataBuffer, 0, length);
        this.deflator.finish();
        while (!this.deflator.finished()) {
            int actual = this.deflator.deflate(this.buffer);
            out.writeBytes(this.buffer, 0, actual);
        }
        this.deflator.reset();
    }

}
