package org.spongepowered.clean.entity.vehicle.minecart;

import org.spongepowered.api.entity.vehicle.minecart.FurnaceMinecart;
import org.spongepowered.clean.world.SWorld;

public class SFurnaceMinecart extends SMinecart implements FurnaceMinecart {

    public SFurnaceMinecart(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getFuel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setFuel(int fuel) {
        // TODO Auto-generated method stub
        
    }

}
