package org.spongepowered.clean.entity.living;

import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.text.Text;
import org.spongepowered.clean.entity.SEntity;
import org.spongepowered.clean.world.SWorld;

import com.flowpowered.math.vector.Vector3d;

public class SLiving extends SEntity implements Living {

    public SLiving(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Text getTeamRepresentation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector3d getHeadRotation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setHeadRotation(Vector3d rotation) {
        // TODO Auto-generated method stub
        
    }

}
