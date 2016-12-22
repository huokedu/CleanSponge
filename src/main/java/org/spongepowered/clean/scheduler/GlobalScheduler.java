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
package org.spongepowered.clean.scheduler;

import org.spongepowered.api.scheduler.Scheduler;
import org.spongepowered.api.scheduler.SpongeExecutorService;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.scheduler.Task.Builder;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class GlobalScheduler implements Scheduler {

    public GlobalScheduler() {

    }

    public void syncTick() {

    }

    @Override
    public Builder createTaskBuilder() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Task> getTaskById(UUID id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Task> getTasksByName(String pattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Task> getScheduledTasks() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Task> getScheduledTasks(boolean async) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Task> getScheduledTasks(Object plugin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPreferredTickInterval() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SpongeExecutorService createSyncExecutor(Object plugin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SpongeExecutorService createAsyncExecutor(Object plugin) {
        throw new UnsupportedOperationException();
    }

}
