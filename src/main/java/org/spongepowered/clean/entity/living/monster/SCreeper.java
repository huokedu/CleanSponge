package org.spongepowered.clean.entity.living.monster;

import org.spongepowered.api.entity.living.monster.Creeper;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.clean.world.SWorld;

public class SCreeper extends SMonster implements Creeper {

    public SCreeper(SWorld world) {
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

    @Override
    public void detonate(Cause cause) {
        // TODO Auto-generated method stub
        
    }

}
