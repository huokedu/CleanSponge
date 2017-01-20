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

import java.util.List;

import org.spongepowered.clean.scheduler.Task;

import com.google.common.collect.Lists;

public class TasksCompleteCondition implements TaskCondition {

    private final List<Task> waiting_for = Lists.newArrayList();
    private final List<Integer> current_task_counts = Lists.newArrayList();

    public TasksCompleteCondition() {

    }

    public void addTask(Task t) {
        this.waiting_for.add(t);
        this.current_task_counts.add(t.getTaskCount());
    }

    public void clear() {
        this.waiting_for.clear();
        this.current_task_counts.clear();
    }

    @Override
    public boolean check() {
        for (int i = 0; i < this.waiting_for.size(); i++) {
            if (this.waiting_for.get(i).getTaskCount() == this.current_task_counts.get(i)) {
                return false;
            }
        }
        return true;
    }

}
