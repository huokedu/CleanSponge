package org.spongepowered.clean.entity.living.passive.horse;

import org.spongepowered.api.entity.living.animal.Horse;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.type.CarriedInventory;
import org.spongepowered.clean.entity.living.passive.SAgeable;
import org.spongepowered.clean.world.SWorld;

public class SHorse extends SAgeable implements Horse {

    public SHorse(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public CarriedInventory<? extends Carrier> getInventory() {
        // TODO Auto-generated method stub
        return null;
    }

}
