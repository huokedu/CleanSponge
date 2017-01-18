package org.spongepowered.clean.entity.vehicle.minecart;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import org.spongepowered.api.entity.vehicle.minecart.ContainerMinecart;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetype;
import org.spongepowered.api.item.inventory.InventoryProperty;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.transaction.InventoryTransactionResult;
import org.spongepowered.api.item.inventory.type.CarriedInventory;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.world.SWorld;

public class SContainerMinecart<M extends ContainerMinecart<M>> extends SMinecart implements ContainerMinecart<M> {

    public SContainerMinecart(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Inventory parent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Inventory> Iterable<T> slots() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Inventory> T first() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Inventory> T next() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ItemStack> poll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ItemStack> poll(int limit) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ItemStack> peek() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ItemStack> peek(int limit) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InventoryTransactionResult offer(ItemStack stack) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InventoryTransactionResult set(ItemStack stack) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int totalItems() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int capacity() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean hasChildren() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(ItemStack stack) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAny(ItemStack stack) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(ItemType type) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getMaxStackSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setMaxStackSize(int size) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <T extends InventoryProperty<?, ?>> Collection<T> getProperties(Inventory child, Class<T> property) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends InventoryProperty<?, ?>> Collection<T> getProperties(Class<T> property) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends InventoryProperty<?, ?>> Optional<T> getProperty(Inventory child, Class<T> property, Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends InventoryProperty<?, ?>> Optional<T> getProperty(Class<T> property, Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Inventory> T query(Class<?>... types) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Inventory> T query(ItemType... types) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Inventory> T query(ItemStack... types) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Inventory> T queryAny(ItemStack... types) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Inventory> T query(InventoryProperty<?, ?>... props) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Inventory> T query(Translation... names) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Inventory> T query(String... names) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Inventory> T query(Object... args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PluginContainer getPlugin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InventoryArchetype getArchetype() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Inventory> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Translation getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CarriedInventory<M> getInventory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<M> getCarrier() {
        // TODO Auto-generated method stub
        return null;
    }

}
