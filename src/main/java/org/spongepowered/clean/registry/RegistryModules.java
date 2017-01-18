package org.spongepowered.clean.registry;

import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.schematic.BlockPaletteType;
import org.spongepowered.clean.world.palette.SBlockPaletteType;

public class RegistryModules {

    public static void registerModules() {
        GameRegistry reg = Sponge.getRegistry();
        reg.registerModule(BlockPaletteType.class, new FixedCatalogRegistryModule<>(SBlockPaletteType::registerTypes));
    }

}
