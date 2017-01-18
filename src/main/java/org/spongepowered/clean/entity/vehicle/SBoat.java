package org.spongepowered.clean.entity.vehicle;

import org.spongepowered.api.entity.vehicle.Boat;
import org.spongepowered.clean.entity.SEntity;
import org.spongepowered.clean.world.SWorld;

public class SBoat extends SEntity implements Boat {

    public SBoat(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isInWater() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double getMaxSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setMaxSpeed(double maxSpeed) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean canMoveOnLand() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setMoveOnLand(boolean moveOnLand) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public double getOccupiedDeceleration() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setOccupiedDeceleration(double occupiedDeceleration) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public double getUnoccupiedDeceleration() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setUnoccupiedDeceleration(double unoccupiedDeceleration) {
        // TODO Auto-generated method stub
        
    }

}
