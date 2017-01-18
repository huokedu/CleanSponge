package org.spongepowered.clean.world.palette;

import java.util.function.Supplier;

import org.spongepowered.api.world.schematic.BlockPalette;
import org.spongepowered.api.world.schematic.BlockPaletteType;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SBlockPaletteType extends AbstractCatalogType implements BlockPaletteType {

    public static void registerTypes(FixedCatalogRegistryModule<BlockPaletteType> registry) {
        registry.register(new SBlockPaletteType("global", "Global", () -> GlobalPalette.instance));
        registry.register(new SBlockPaletteType("local", "Local", LocalBlockPalette::new));
    }

    private final Supplier<BlockPalette> builder;

    public SBlockPaletteType(String id, String name, Supplier<BlockPalette> builder) {
        super(id, name);
        this.builder = builder;
    }

    @Override
    public BlockPalette create() {
        return this.builder.get();
    }

}
