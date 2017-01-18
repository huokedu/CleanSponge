package org.spongepowered.clean.entity.vehicle.minecart;

import org.spongepowered.api.entity.vehicle.minecart.Minecart;
import org.spongepowered.clean.entity.SEntity;
import org.spongepowered.clean.world.SWorld;

import com.flowpowered.math.vector.Vector3d;

public class SMinecart extends SEntity implements Minecart {

    public SMinecart(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isOnRail() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double getSwiftness() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setSwiftness(double swiftness) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public double getPotentialMaxSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean doesSlowWhenEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setSlowWhenEmpty(boolean slowWhenEmpty) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Vector3d getAirborneVelocityMod() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setAirborneVelocityMod(Vector3d airborneVelocityMod) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Vector3d getDerailedVelocityMod() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDerailedVelocityMod(Vector3d derailedVelocityMod) {
        // TODO Auto-generated method stub
        
    }

}
