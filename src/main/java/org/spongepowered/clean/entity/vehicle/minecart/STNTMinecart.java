package org.spongepowered.clean.entity.vehicle.minecart;

import org.spongepowered.api.entity.vehicle.minecart.TNTMinecart;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.clean.world.SWorld;

public class STNTMinecart extends SMinecart implements TNTMinecart {

    public STNTMinecart(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isPrimed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void prime(Cause cause) {
        // TODO Auto-generated method stub

    }

    @Override
    public void defuse(Cause cause) {
        // TODO Auto-generated method stub

    }

    @Override
    public void detonate(Cause cause) {
        // TODO Auto-generated method stub

    }

}
