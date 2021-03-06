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
package org.spongepowered.clean.text.serializer;

import org.spongepowered.api.text.LiteralText;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.SafeTextSerializer;
import org.spongepowered.api.text.serializer.TextParseException;
import org.spongepowered.clean.registry.AbstractCatalogType;

public class PlainTextSerializer extends AbstractCatalogType implements SafeTextSerializer {

    public PlainTextSerializer() {
        super("sponge:plain", "Plain");
    }

    @Override
    public String serialize(Text text) {
        StringBuilder builder = new StringBuilder();
        toPlain(text, builder);
        return builder.toString();
    }

    private void toPlain(Text text, StringBuilder builder) {
        if (text instanceof LiteralText) {
            builder.append(((LiteralText) text).getContent());
            if (!text.getChildren().isEmpty()) {
                for (Text child : text.getChildren()) {
                    toPlain(child, builder);
                }
            }
        }
    }

    @Override
    public Text deserialize(String input) throws TextParseException {
        return Text.of(input);
    }

}
