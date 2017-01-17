package org.spongepowered.clean.scheduler.condition;

import java.util.concurrent.atomic.AtomicInteger;

public class ResourceMutex {

    private final AtomicInteger mutex = new AtomicInteger(0);

    public boolean aquire() {
        return this.mutex.compareAndSet(0, 1);
    }

    public void release() {
        this.mutex.decrementAndGet();
    }

}
