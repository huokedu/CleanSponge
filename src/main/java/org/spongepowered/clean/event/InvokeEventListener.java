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
package org.spongepowered.clean.event;

import java.lang.reflect.Method;

import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.EventListener;

public class InvokeEventListener implements EventListener<Event> {

    private final Object owner;
    private final Method method;

    public InvokeEventListener(Object owner, Method method) {
        this.owner = owner;
        this.method = method;
    }

    @Override
    public void handle(Event event) throws Exception {
        this.method.invoke(this.owner, event);
    }

    @Override
    public String toString() {
        return this.method.toGenericString();
    }

    public static class Factory implements EventListenerFactory {

        @Override
        public EventListener<?> create(Object owner, Method method) {
            if (method.getParameterCount() > 1) {
                throw new IllegalArgumentException("Event filters not supported currently");
            }
            return new InvokeEventListener(owner, method);
        }

    }

}
