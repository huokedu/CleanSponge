package org.spongepowered.clean.entity.living;

import java.util.Optional;

import org.spongepowered.api.data.type.HandType;
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

}
