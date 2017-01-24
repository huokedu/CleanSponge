package org.spongepowered.clean.entity.projectile.arrow;

import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.projectile.arrow.SpectralArrow;
import org.spongepowered.clean.entity.projectile.SDamagingProjectile;
import org.spongepowered.clean.world.SWorld;

public class SSpectralArrow extends SDamagingProjectile implements SpectralArrow {

    public SSpectralArrow(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public EntityType getType() {
        return EntityTypes.SPECTRAL_ARROW;
    }

}
