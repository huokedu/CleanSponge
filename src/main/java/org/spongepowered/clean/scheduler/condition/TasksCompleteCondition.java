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
