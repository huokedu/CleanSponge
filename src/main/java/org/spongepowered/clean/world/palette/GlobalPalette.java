package org.spongepowered.clean.world.palette;

import java.util.Collection;
import java.util.Optional;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.world.schematic.BlockPalette;
import org.spongepowered.api.world.schematic.BlockPaletteType;
import org.spongepowered.api.world.schematic.BlockPaletteTypes;

public class GlobalPalette implements BlockPalette {

    public static final GlobalPalette instance = new GlobalPalette();

    private GlobalPalette() {
    }

    @Override
    public BlockPaletteType getType() {
        return BlockPaletteTypes.GLOBAL;
    }

    @Override
    public int getHighestId() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Optional<Integer> get(BlockState state) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getOrAssign(BlockState state) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Optional<BlockState> get(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(BlockState state) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<BlockState> getEntries() {
        // TODO Auto-generated method stub
        return null;
    }

}
