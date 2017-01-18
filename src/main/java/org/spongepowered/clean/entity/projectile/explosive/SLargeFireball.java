package org.spongepowered.clean.entity.projectile.explosive;

import org.spongepowered.api.entity.projectile.explosive.fireball.LargeFireball;
import org.spongepowered.api.entity.projectile.source.ProjectileSource;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.clean.world.SWorld;

public class SLargeFireball extends SFireball implements LargeFireball {

    public SLargeFireball(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ProjectileSource getShooter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setShooter(ProjectileSource shooter) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void detonate(Cause cause) {
        // TODO Auto-generated method stub
        
    }

}
