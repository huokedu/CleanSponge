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

import javax.crypto.Cipher;

public class PacketEncryptor extends MessageToByteEncoder<ByteBuf> {

    private final Cipher cipher;
    private byte[]       dataBuffer   = new byte[1024];
    private byte[]       cipherBuffer = new byte[1024];

    public PacketEncryptor(Cipher cipher) {
        this.cipher = cipher;

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
//        System.out.println("encrypting");
        int length = msg.readableBytes();
        if (this.dataBuffer.length < length) {
            this.dataBuffer = new byte[length];
        }
        msg.readBytes(this.dataBuffer, 0, length);
        int size = this.cipher.getOutputSize(length);
        if (this.cipherBuffer.length < size) {
            this.cipherBuffer = new byte[size];
        }
        int actual = this.cipher.update(this.dataBuffer, 0, length, this.cipherBuffer);
        out.writeBytes(this.cipherBuffer, 0, actual);
    }

}
