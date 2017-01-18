package org.spongepowered.clean.entity.hanging;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.hanging.LeashHitch;
import org.spongepowered.clean.world.SWorld;

public class SLeashHitch extends SHanging implements LeashHitch {

    public SLeashHitch(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Entity getLeashedEntity() {
        // TODO Auto-generated method stub
        return null;
    }

}
