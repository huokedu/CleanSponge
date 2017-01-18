package org.spongepowered.clean.entity.living.monster;

import org.spongepowered.api.entity.living.monster.Enderman;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.type.CarriedInventory;
import org.spongepowered.clean.world.SWorld;

public class SEnderman extends SMonster implements Enderman {

    public SEnderman(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public CarriedInventory<? extends Carrier> getInventory() {
        // TODO Auto-generated method stub
        return null;
    }

}
