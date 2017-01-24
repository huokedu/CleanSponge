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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.clean.SGame;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;

public class SEventManager implements EventManager {

    @SuppressWarnings("rawtypes") private final Map<Class, List<RegisteredListener>> listeners = new HashMap<>();
    private final EventListenerFactory factory = new InvokeEventListener.Factory();
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void registerListeners(Object plugin, Object obj) {
        for (Method method : obj.getClass().getMethods()) {
            if (!method.isAnnotationPresent(Listener.class)) {
                continue;
            }
            Listener anno = method.getAnnotation(Listener.class);
            if (method.getReturnType() == null && method.getParameterCount() >= 1 && Event.class.isAssignableFrom(method.getParameterTypes()[0])) {
                registerListener(plugin, (Class) method.getParameterTypes()[0], anno.order(), obj, (EventListener) this.factory.create(obj, method));
            } else {
                SGame.getLogger().error("Event listener: " + method.toGenericString() + " must return void with an event as the first parameter.");
                continue;
            }
        }
    }

    @Override
    public <T extends Event> void registerListener(Object plugin, Class<T> eventClass, EventListener<? super T> listener) {
        registerListener(plugin, eventClass, Order.DEFAULT, listener);
    }

    @Override
    public <T extends Event> void registerListener(Object plugin, Class<T> eventClass, Order order, boolean beforeModifications,
            EventListener<? super T> listener) {
        registerListener(plugin, eventClass, order, listener);
    }

    @Override
    public <T extends Event> void registerListener(Object plugin, Class<T> eventClass, Order order, EventListener<? super T> listener) {
        registerListener(plugin, eventClass, order, listener, listener);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public <T extends Event> void registerListener(Object plugin, Class<T> eventClass, Order order, Object owner, EventListener<?> listener) {
        PluginContainer pl = Sponge.getPluginManager().fromInstance(plugin).get();
        RegisteredListener reg = new RegisteredListener(pl, listener, owner, order);
        Set<Class<? super T>> types = TypeToken.of(eventClass).getTypes().rawTypes();
        for (Class<?> type : types) {
            if (Event.class.isAssignableFrom(type)) {
                registerSpecific((Class) type, order, reg);
            }
        }
    }

    private void registerSpecific(Class<? extends Event> eventClass, Order order, RegisteredListener listener) {
        try {
            this.lock.lock();
            List<RegisteredListener> listeners = this.listeners.get(eventClass);
            if (listeners == null) {
                this.listeners.put(eventClass, ImmutableList.of(listener));
                return;
            }
            ImmutableList.Builder<RegisteredListener> builder = ImmutableList.builder();
            boolean found = false;
            for (int i = 0; i < listeners.size(); i++) {
                RegisteredListener reg = listeners.get(i);
                if (reg.getOrder().ordinal() >= order.ordinal()) {
                    builder.add(listener);
                    found = true;
                }
                builder.add(reg);
            }
            if (!found) {
                builder.add(listener);
            }
            this.listeners.put(eventClass, builder.build());
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public void unregisterListeners(Object obj) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unregisterPluginListeners(Object plugin) {
        // TODO Auto-generated method stub

    }

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean post(Event event) {
        List<RegisteredListener> listeners = null;
        try {
            this.lock.lock();
            // Only lock around getting the listener list as the list is
            // immutable and we don't want to lock through the entire time of
            // running the events
            listeners = this.listeners.get(event.getClass());
        } finally {
            this.lock.unlock();
        }
        if (listeners == null || listeners.isEmpty()) {
            return event instanceof Cancellable ? ((Cancellable) event).isCancelled() : false;
        }
        for (RegisteredListener listener : listeners) {
            try {
                listener.getListener().handle(event);
            } catch (Exception e) {
                SGame.getLogger().error("Error posting " + event.toString() + " to " + listener.toString());
                e.printStackTrace();
                // TODO drop listener if it errors more than N times in T time
            }
        }
        return event instanceof Cancellable ? ((Cancellable) event).isCancelled() : false;
    }

}
