package org.spongepowered.clean.entity.complex;

import org.spongepowered.api.entity.living.complex.ComplexLiving;
import org.spongepowered.api.entity.living.complex.ComplexLivingPart;
import org.spongepowered.clean.entity.SEntity;
import org.spongepowered.clean.world.SWorld;

public class SComplexLivingPart extends SEntity implements ComplexLivingPart {

    public SComplexLivingPart(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ComplexLiving getParent() {
        // TODO Auto-generated method stub
        return null;
    }

}
