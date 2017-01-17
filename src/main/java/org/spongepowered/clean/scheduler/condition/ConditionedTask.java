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
