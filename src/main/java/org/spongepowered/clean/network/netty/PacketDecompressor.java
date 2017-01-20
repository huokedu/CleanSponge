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
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.zip.Inflater;

public class PacketDecompressor extends ByteToMessageDecoder {

    private final Inflater inflator    = new Inflater();
    private byte[]         inputBuffer = new byte[1024];

    public PacketDecompressor() {
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() == 0) {
            return;
        }
        int length = ByteBufUtil.readVarInt(in);
        if (length == 0) {
            // not compressed
            out.add(in.readBytes(in.readableBytes()));
            return;
        }
        int inputLength = in.readableBytes();
        if (this.inputBuffer.length < inputLength) {
            this.inputBuffer = new byte[inputLength];
        }
        in.readBytes(this.inputBuffer, 0, inputLength);
        byte[] data = new byte[length];

        this.inflator.setInput(this.inputBuffer, 0, inputLength);
        this.inflator.inflate(data);
        this.inflator.reset();
        // can't use a cached array for the data as the packets will likely be handled asynchronously
        out.add(Unpooled.wrappedBuffer(data));
    }

}
