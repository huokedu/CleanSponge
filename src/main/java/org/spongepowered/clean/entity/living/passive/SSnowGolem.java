package org.spongepowered.clean.entity.living.passive;

import java.util.Optional;

import org.spongepowered.api.entity.living.golem.SnowGolem;
import org.spongepowered.api.entity.projectile.Projectile;
import org.spongepowered.clean.entity.living.SCreature;
import org.spongepowered.clean.world.SWorld;

import com.flowpowered.math.vector.Vector3d;

public class SSnowGolem extends SCreature implements SnowGolem {

    public SSnowGolem(SWorld world) {
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
