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
package org.spongepowered.clean.entity.living;

import java.util.Optional;

import org.spongepowered.api.data.type.HandType;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.Human;
import org.spongepowered.api.entity.projectile.Projectile;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.equipment.EquipmentType;
import org.spongepowered.api.item.inventory.type.CarriedInventory;
import org.spongepowered.clean.world.SWorld;

import com.flowpowered.math.vector.Vector3d;

public class SHuman extends SCreature implements Human {

    public SHuman(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
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

    @Override
    public Optional<ItemStack> getHelmet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<ItemStack> getChestplate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<ItemStack> getLeggings() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<ItemStack> getBoots() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBoots(ItemStack boots) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<ItemStack> getItemInHand(HandType handType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setItemInHand(HandType hand, ItemStack itemInHand) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean canEquip(EquipmentType type) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canEquip(EquipmentType type, ItemStack equipment) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<ItemStack> getEquipped(EquipmentType type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean equip(EquipmentType type, ItemStack equipment) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public CarriedInventory<? extends Carrier> getInventory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EntityType getType() {
        return EntityTypes.HUMAN;
    }

}
