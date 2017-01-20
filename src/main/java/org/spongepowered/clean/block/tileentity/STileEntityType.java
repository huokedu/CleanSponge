package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class STileEntityType extends AbstractCatalogType implements TileEntityType {

    private Class<? extends TileEntity> type;

    public STileEntityType(String id, String name, Class<? extends TileEntity> type) {
        super(id, name);
        this.type = type;
    }

    @Override
    public Class<? extends TileEntity> getTileEntityType() {
        return this.type;
    }

    public static void registerTypes(FixedCatalogRegistryModule<TileEntityType> registry) {
        registry.register(new STileEntityType("minecraft:banner", "Banner", SBanner.class));
        registry.register(new STileEntityType("minecraft:beacon", "Beacon", SBeacon.class));
        registry.register(new STileEntityType("minecraft:brewing_stand", "Brewing Stand", SBrewingStand.class));
        registry.register(new STileEntityType("minecraft:chest", "Chest", SChest.class));
        registry.register(new STileEntityType("minecraft:command_block", "Command Block", SCommandBlock.class));
        registry.register(new STileEntityType("minecraft:comparator", "Comparator", SComparator.class));
        registry.register(new STileEntityType("minecraft:daylight_detector", "Daylight Detector", SDaylightDetector.class));
        registry.register(new STileEntityType("minecraft:dispenser", "Dispenser", SDispenser.class));
        registry.register(new STileEntityType("minecraft:dropper", "Dropper", SDropper.class));
        registry.register(new STileEntityType("minecraft:enchantment_table", "Enchantment Table", SEnchantmentTable.class));
        registry.register(new STileEntityType("minecraft:ender_chest", "Ender Chest", SEnderChest.class));
        registry.register(new STileEntityType("minecraft:end_gateway", "End Gateway", SEndGateway.class));
        registry.register(new STileEntityType("minecraft:end_portal", "End Portal", SEndPortal.class));
        registry.register(new STileEntityType("minecraft:flower_pot", "Flower Pot", SFlowerPot.class));
        registry.register(new STileEntityType("minecraft:furnace", "Furnace", SFurnace.class));
        registry.register(new STileEntityType("minecraft:hopper", "Hopper", SHopper.class));
        registry.register(new STileEntityType("minecraft:jukebox", "Jukebox", SJukebox.class));
        registry.register(new STileEntityType("minecraft:mob_spawner", "Mob Spawner", SMobSpawner.class));
        registry.register(new STileEntityType("minecraft:note", "Noteblock", SNote.class));
        registry.register(new STileEntityType("minecraft:piston", "Piston", SPiston.class));
        registry.register(new STileEntityType("minecraft:shulker_box", "Shulker Box", SShulkerBox.class));
        registry.register(new STileEntityType("minecraft:sign", "Sign", SSign.class));
        registry.register(new STileEntityType("minecraft:skull", "Skull", SSkull.class));
        registry.register(new STileEntityType("minecraft:structure", "Structure", SStructure.class));
    }

}
