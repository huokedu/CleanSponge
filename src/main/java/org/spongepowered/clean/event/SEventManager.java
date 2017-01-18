package org.spongepowered.clean.event;

import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Order;

public class SEventManager implements EventManager {

    @Override
    public void registerListeners(Object plugin, Object obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T extends Event> void registerListener(Object plugin, Class<T> eventClass, EventListener<? super T> listener) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T extends Event> void registerListener(Object plugin, Class<T> eventClass, Order order, EventListener<? super T> listener) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T extends Event> void registerListener(Object plugin, Class<T> eventClass, Order order, boolean beforeModifications,
            EventListener<? super T> listener) {
        // TODO Auto-generated method stub
        
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
    public boolean post(Event event) {
        // TODO Auto-generated method stub
        return false;
    }

}
