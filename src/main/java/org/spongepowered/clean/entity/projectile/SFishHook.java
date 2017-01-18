package org.spongepowered.clean.entity.projectile;

import java.util.Optional;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.projectile.FishHook;
import org.spongepowered.clean.world.SWorld;

public class SFishHook extends SProjectile implements FishHook {

    public SFishHook(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Optional<Entity> getHookedEntity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setHookedEntity(Entity entity) {
        // TODO Auto-generated method stub
        
    }

}
