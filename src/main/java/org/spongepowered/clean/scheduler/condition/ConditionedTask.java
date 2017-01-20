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
package org.spongepowered.clean.scheduler.condition;

import org.spongepowered.clean.scheduler.Task;

public class ConditionedTask extends Task implements Comparable<ConditionedTask> {

    private Task task;
    private TaskCondition condition;
    private long priority;

    public ConditionedTask(Task task, TaskCondition condition, long prior) {
        this.task = task;
        this.condition = condition;
        this.priority = prior;
    }

    public Task getTask() {
        return this.task;
    }

    public TaskCondition getCondition() {
        return this.condition;
    }

    @Override
    protected void execute() {
        this.task.run();
    }

    @Override
    public int getTaskCount() {
        return this.task.getTaskCount();
    }

    @Override
    public int compareTo(ConditionedTask o) {
        return Integer.signum((int) (o.priority - this.priority));
    }

    // TODO: pool instances?

}
