package org.spongepowered.clean.entity.projectile;

import org.spongepowered.api.entity.projectile.Projectile;
import org.spongepowered.api.entity.projectile.source.ProjectileSource;
import org.spongepowered.clean.entity.SEntity;
import org.spongepowered.clean.world.SWorld;

public class SProjectile extends SEntity implements Projectile {

    public SProjectile(SWorld world) {
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
