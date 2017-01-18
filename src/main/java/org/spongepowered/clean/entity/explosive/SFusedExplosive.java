package org.spongepowered.clean.entity.explosive;

import org.spongepowered.api.entity.explosive.FusedExplosive;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.clean.world.SWorld;

public class SFusedExplosive extends SExplosive implements FusedExplosive {

    public SFusedExplosive(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isPrimed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void prime(Cause cause) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void defuse(Cause cause) {
        // TODO Auto-generated method stub
        
    }

}
