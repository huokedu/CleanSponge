package org.spongepowered.clean.entity.living.monster;

import org.spongepowered.api.entity.living.monster.Spider;
import org.spongepowered.clean.world.SWorld;

public class SSpider extends SMonster implements Spider {

    public SSpider(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isClimbing() {
        // TODO Auto-generated method stub
        return false;
    }

}
