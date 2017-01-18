package org.spongepowered.clean.world.palette;

import java.util.Collection;
import java.util.Optional;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.schematic.BlockPalette;
import org.spongepowered.api.world.schematic.BlockPaletteType;
import org.spongepowered.api.world.schematic.BlockPaletteTypes;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

public class LocalBlockPalette implements BlockPalette {

    private final Int2ObjectOpenHashMap<BlockState> idsToState = new Int2ObjectOpenHashMap<>();
    private final Object2IntOpenHashMap<BlockState> stateToId = new Object2IntOpenHashMap<>();
    private int next = 1;

    public LocalBlockPalette() {
        this.stateToId.defaultReturnValue(0);
        this.stateToId.put(BlockTypes.AIR.getDefaultState(), 0);
        this.idsToState.put(0, BlockTypes.AIR.getDefaultState());
    }

    @Override
    public BlockPaletteType getType() {
        return BlockPaletteTypes.LOCAL;
    }

    @Override
    public int getHighestId() {
        return this.next - 1;
    }

    @Override
    public Optional<Integer> get(BlockState state) {
        // if the state is not present then the default value in returned, in
        // this case that is 0 (air)
        return Optional.ofNullable(this.stateToId.getInt(state));
    }

    @Override
    public int getOrAssign(BlockState state) {
        if (this.stateToId.containsKey(state)) {
            return this.stateToId.getInt(state);
        }
        this.stateToId.put(state, this.next);
        this.idsToState.put(this.next, state);
        return this.next++;
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
