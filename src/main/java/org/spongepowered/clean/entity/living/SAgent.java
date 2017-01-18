package org.spongepowered.clean.entity.living;

import java.util.Optional;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.ai.Goal;
import org.spongepowered.api.entity.ai.GoalType;
import org.spongepowered.api.entity.living.Agent;
import org.spongepowered.clean.world.SWorld;

public class SAgent extends SLiving implements Agent {

    public SAgent(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Optional<Entity> getTarget() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setTarget(Entity target) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T extends Agent> Optional<Goal<T>> getGoal(GoalType type) {
        // TODO Auto-generated method stub
        return null;
    }

}
