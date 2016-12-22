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
package org.spongepowered.clean.entity.player;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.data.type.HandType;
import org.spongepowered.api.entity.living.Humanoid;
import org.spongepowered.api.entity.projectile.Projectile;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.equipment.EquipmentType;
import org.spongepowered.api.item.inventory.type.CarriedInventory;
import org.spongepowered.clean.entity.SpongeAgent;
import org.spongepowered.clean.world.SpongeWorld;

import java.util.Optional;

public abstract class SpongeHumanoid extends SpongeAgent implements Humanoid {

    public SpongeHumanoid(SpongeWorld world) {
        super(world);
    }

    @Override
    public <T extends Projectile> Optional<T> launchProjectile(Class<T> projectileClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Projectile> Optional<T> launchProjectile(Class<T> projectileClass, Vector3d velocity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ItemStack> getHelmet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ItemStack> getChestplate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ItemStack> getLeggings() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ItemStack> getBoots() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBoots(ItemStack boots) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ItemStack> getItemInHand(HandType handType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setItemInHand(HandType hand, ItemStack itemInHand) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canEquip(EquipmentType type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canEquip(EquipmentType type, ItemStack equipment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ItemStack> getEquipped(EquipmentType type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equip(EquipmentType type, ItemStack equipment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CarriedInventory<? extends Carrier> getInventory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException();
    }

}
