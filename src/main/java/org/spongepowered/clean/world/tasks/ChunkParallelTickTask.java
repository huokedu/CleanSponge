package org.spongepowered.clean.world.tasks;

import java.util.List;

import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.world.SChunk;

public class ChunkParallelTickTask extends Task {

    private final List<SChunk> chunks;
    private int start, size;

    public ChunkParallelTickTask(List<SChunk> chunks, int start, int size) {
        this.chunks = chunks;
        this.start = start;
        this.size = size;
    }

    @Override
    protected void execute() {
        for (int i = this.start; i < this.start + this.size; i++) {
            SChunk chunk = this.chunks.get(i);
            chunk.parallelUpdate();
        }
    }

}
