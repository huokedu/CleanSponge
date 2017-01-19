package org.spongepowered.clean.world.palette;

import java.util.Collection;
import java.util.Optional;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.world.schematic.BlockPalette;
import org.spongepowered.api.world.schematic.BlockPaletteType;
import org.spongepowered.api.world.schematic.BlockPaletteTypes;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

public class GlobalPalette implements BlockPalette {

    public static final GlobalPalette instance = new GlobalPalette();

    private final Int2ObjectOpenHashMap<BlockState> idsToState = new Int2ObjectOpenHashMap<>();
    private final Object2IntOpenHashMap<BlockState> stateToId = new Object2IntOpenHashMap<>();
    private int highest = 0;

    public GlobalPalette() {
        this.stateToId.defaultReturnValue(0);
    }

    public void set(int id, BlockState state) {
        this.idsToState.put(id, state);
        this.stateToId.put(state, id);
    }

    @Override
    public BlockPaletteType getType() {
        return BlockPaletteTypes.GLOBAL;
    }

    @Override
    public int getHighestId() {
        return this.highest;
    }

    @Override
    public Optional<Integer> get(BlockState state) {
        return Optional.ofNullable(this.stateToId.getInt(state));
    }

    @Override
    public int getOrAssign(BlockState state) {
        return this.stateToId.getInt(state);
    }

    @Override
    public Optional<BlockState> get(int id) {
        return Optional.ofNullable(this.idsToState.get(id));
    }

    @Override
    public boolean remove(BlockState state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<BlockState> getEntries() {
        return this.stateToId.keySet();
    }

}
