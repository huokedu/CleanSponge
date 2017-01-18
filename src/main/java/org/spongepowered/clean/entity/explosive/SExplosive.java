package org.spongepowered.clean.entity.explosive;

import org.spongepowered.api.entity.explosive.Explosive;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.clean.entity.SEntity;
import org.spongepowered.clean.world.SWorld;

public class SExplosive extends SEntity implements Explosive {

    public SExplosive(SWorld world) {
        super(world);
    }

    @Override
    public void detonate(Cause cause) {
        
    }

}
