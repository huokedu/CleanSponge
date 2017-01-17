package org.spongepowered.clean.scheduler.condition;

public class ResourceCondition implements TaskCondition {

    private ResourceMutex mutex;

    public ResourceCondition(ResourceMutex mutex) {
        this.mutex = mutex;
    }

    @Override
    public boolean check() {
        return this.mutex.aquire();
    }

}
