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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.spongepowered.api.text.LiteralText;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextParseException;
import org.spongepowered.api.text.serializer.TextSerializer;
import org.spongepowered.clean.registry.AbstractCatalogType;

public class JsonTextSerializer extends AbstractCatalogType implements TextSerializer {

    public JsonTextSerializer() {
        super("sponge:json", "Json");
    }

    @Override
    public String serialize(Text text) {
        return toJson(text).toString();
    }

    private JsonObject toJson(Text text) {
        JsonObject obj = new JsonObject();
        if (text instanceof LiteralText) {
            LiteralText t = (LiteralText) text;
            if (t.getFormat().getColor() != TextColors.NONE) {
                obj.addProperty("color", t.getFormat().getColor().getId());
            }
            obj.addProperty("text", t.getContent());
            if(!text.getChildren().isEmpty()) {
                JsonArray extra = new JsonArray();
                for (Text child : text.getChildren()) {
                    extra.add(toJson(child));
                }
                obj.add("extra", extra);
            }
        }
        return obj;
    }

    @Override
    public Text deserialize(String input) throws TextParseException {
        throw new UnsupportedOperationException();
    }

}
