/*
 * This file is part of SpongeClean, licensed under the MIT License (MIT).
 *
 * Copyright (c) The VoxelBox <http://thevoxelbox.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.clean.entity.vehicle;

import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.EntityTypes;
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

    @Override
    public EntityType getType() {
        return EntityTypes.BOAT;
    }

}
