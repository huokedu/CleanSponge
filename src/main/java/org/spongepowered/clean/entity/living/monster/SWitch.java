package org.spongepowered.clean.entity.living.monster;

import java.util.Optional;

import org.spongepowered.api.entity.living.monster.Witch;
import org.spongepowered.api.entity.projectile.Projectile;
import org.spongepowered.clean.world.SWorld;

import com.flowpowered.math.vector.Vector3d;

public class SWitch extends SMonster implements Witch {

    public SWitch(SWorld world) {
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

}
