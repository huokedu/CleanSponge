package org.spongepowered.clean.block.tileentity;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.block.tileentity.carrier.Dispenser;
import org.spongepowered.api.entity.projectile.Projectile;

import java.util.Optional;

public class SDispenser extends AbstractTileEntityCarrier implements Dispenser {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.DISPENSER;
    }

    @Override
    public <T extends Projectile> Optional<T> launchProjectile(Class<T> projectileClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Projectile> Optional<T> launchProjectile(Class<T> projectileClass, Vector3d velocity) {
        // TODO Auto-generated method stub
        return null;
    }

}
