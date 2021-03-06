/*
 * This file is part of SpongeClean, licensed under the MIT License (MIT).
 *
 * Copyright (c) The VoxelBox <http://thevoxelbox.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.clean.world.tasks;

import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.scheduler.condition.TasksCompleteCondition;
import org.spongepowered.clean.world.SChunk;
import org.spongepowered.clean.world.SWorld;

public class WorldTickTask extends Task {

    private final SWorld world;
    private final WorldTickFinishTask finish = new WorldTickFinishTask();

    public WorldTickTask(SWorld world) {
        this.world = world;
    }

    @Override
    public void execute() {
        for (SChunk chunk : this.world.getSChunks()) {
            chunk.serialUpdate();
        }
        this.world.serialUpdate();

        // TODO use more tasks
        TasksCompleteCondition condition = new TasksCompleteCondition();
        ChunkParallelTickTask task = new ChunkParallelTickTask(this.world.getSChunks(), 0, this.world.getSChunks().size());
        condition.addTask(task);
        CoreScheduler.addHighTask(task);
        CoreScheduler.addHighTask(this.finish, condition);
    }

    private class WorldTickFinishTask extends Task {

        @Override
        protected void execute() {
            WorldTickTask.this.world.getMutex().release();
        }

    }

}
