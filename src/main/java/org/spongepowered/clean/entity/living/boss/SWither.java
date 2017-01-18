package org.spongepowered.clean.entity.living.boss;

import java.util.List;
import java.util.Optional;

import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.monster.Wither;
import org.spongepowered.api.entity.projectile.Projectile;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.clean.entity.living.SAerial;
import org.spongepowered.clean.world.SWorld;

import com.flowpowered.math.vector.Vector3d;

public class SWither extends SAerial implements Wither {

    public SWither(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public <T extends Projectile> Optional<T> launchProjectile(Class<T> projectileClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Projectile> Optional<T> launchProjectile(Class<T> projectileClass, Vector3d velocity) {
        // TODO Auto-generated method stub
        return null;
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

    @Override
    public List<Living> getTargets() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setTargets(List<Living> targets) {
        // TODO Auto-generated method stub
        
    }

}
