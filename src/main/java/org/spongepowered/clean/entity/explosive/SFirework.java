package org.spongepowered.clean.entity.explosive;

import org.spongepowered.api.entity.projectile.Firework;
import org.spongepowered.api.entity.projectile.source.ProjectileSource;
import org.spongepowered.clean.world.SWorld;

public class SFirework extends SFusedExplosive implements Firework {

    public SFirework(SWorld world) {
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

}
