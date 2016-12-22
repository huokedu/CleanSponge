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

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.reflect.TypeToken;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Order;

import java.lang.reflect.Method;
import java.util.Set;

public class SpongeEventManager implements EventManager {

    private final Multimap<Class<?>, EventListener<?>> handlers = HashMultimap.create();

    @Override
    public void registerListeners(Object plugin, Object obj) {
        for (Method m : obj.getClass().getMethods()) {
            if (m.getParameterCount() == 1 && Event.class.isAssignableFrom(m.getParameterTypes()[0])) {
                this.handlers.put(m.getParameterTypes()[0], new InvokeEventHandler<>(obj, m));
            }
        }
    }

    @Override
    public <T extends Event> void registerListener(Object plugin, Class<T> eventClass, EventListener<? super T> listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Event> void registerListener(Object plugin, Class<T> eventClass, Order order, EventListener<? super T> listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Event> void registerListener(Object plugin, Class<T> eventClass, Order order, boolean beforeModifications,
            EventListener<? super T> listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unregisterListeners(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unregisterPluginListeners(Object plugin) {
        throw new UnsupportedOperationException();
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean post(Event event) {
        // TODO bake
        Set<Class<?>> types = (Set) TypeToken.of(event.getClass()).getTypes().rawTypes();
        for(Class<?> c: types) {
            if(Event.class.isAssignableFrom(c)) {
                for (EventListener l : this.handlers.get(c)) {
                    try {
                        l.handle(event);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return (event instanceof Cancellable) ? ((Cancellable) event).isCancelled() : false;
    }

}
