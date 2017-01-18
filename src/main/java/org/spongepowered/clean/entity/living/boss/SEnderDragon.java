package org.spongepowered.clean.entity.living.boss;

import java.util.Optional;
import java.util.Set;

import org.spongepowered.api.entity.EnderCrystal;
import org.spongepowered.api.entity.living.complex.EnderDragon;
import org.spongepowered.api.entity.living.complex.EnderDragonPart;
import org.spongepowered.clean.entity.living.SAerial;
import org.spongepowered.clean.world.SWorld;

public class SEnderDragon extends SAerial implements EnderDragon {

    public SEnderDragon(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Set<EnderDragonPart> getParts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<EnderCrystal> getHealingCrystal() {
        // TODO Auto-generated method stub
        return null;
    }

}
