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
