package org.spongepowered.clean.entity.living;

import java.util.Optional;

import org.spongepowered.api.entity.living.Humanoid;
import org.spongepowered.api.entity.living.Villager;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.type.CarriedInventory;
import org.spongepowered.clean.entity.living.passive.SAgeable;
import org.spongepowered.clean.world.SWorld;

public class SVillager extends SAgeable implements Villager {

    public SVillager(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Optional<Humanoid> getCustomer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCustomer(Humanoid humanoid) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public CarriedInventory<? extends Carrier> getInventory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isTrading() {
        // TODO Auto-generated method stub
        return false;
    }

}
