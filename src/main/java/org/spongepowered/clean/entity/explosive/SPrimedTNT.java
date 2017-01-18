package org.spongepowered.clean.entity.explosive;

import java.util.Optional;

import org.spongepowered.api.entity.explosive.PrimedTNT;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.clean.world.SWorld;

public class SPrimedTNT extends SFusedExplosive implements PrimedTNT {

    public SPrimedTNT(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Optional<Living> getDetonator() {
        // TODO Auto-generated method stub
        return null;
    }

}
