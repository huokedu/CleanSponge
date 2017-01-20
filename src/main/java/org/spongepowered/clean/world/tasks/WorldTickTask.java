package org.spongepowered.clean.world.tasks;

import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.scheduler.condition.TasksCompleteCondition;
import org.spongepowered.clean.world.SChunk;
import org.spongepowered.clean.world.SWorld;

public class WorldTickTask extends Task {

    private final SWorld world;

    public WorldTickTask(SWorld world) {
        this.world = world;
    }

    @Override
    public void execute() {
        for (SChunk chunk : this.world.getSChunks()) {
            chunk.serialUpdate();
        }

        // TODO use more tasks
        TasksCompleteCondition condition = new TasksCompleteCondition();
        ChunkParallelTickTask task = new ChunkParallelTickTask(this.world.getSChunks(), 0, this.world.getSChunks().size());
        condition.addTask(task);
        CoreScheduler.addHighTask(task);
        CoreScheduler.addHighTask(new WorldTickFinishTask(), condition);
    }

    private class WorldTickFinishTask extends Task {

        @Override
        protected void execute() {
            WorldTickTask.this.world.getMutex().release();
        }

    }

}
