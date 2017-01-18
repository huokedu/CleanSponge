package org.spongepowered.clean.entity.living.monster;

import java.util.Optional;

import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.monster.Guardian;
import org.spongepowered.clean.entity.living.SAquatic;
import org.spongepowered.clean.world.SWorld;

public class SGuardian extends SAquatic implements Guardian {

    public SGuardian(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Optional<Living> getBeamTarget() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBeamTarget(Living entity) {
        // TODO Auto-generated method stub
        
    }

}
