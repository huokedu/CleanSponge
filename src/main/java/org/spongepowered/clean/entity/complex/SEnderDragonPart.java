package org.spongepowered.clean.entity.complex;

import org.spongepowered.api.entity.living.complex.EnderDragon;
import org.spongepowered.api.entity.living.complex.EnderDragonPart;
import org.spongepowered.clean.world.SWorld;

public class SEnderDragonPart extends SComplexLivingPart implements EnderDragonPart {

    public SEnderDragonPart(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public EnderDragon getParent() {
        // TODO Auto-generated method stub
        return null;
    }

}
