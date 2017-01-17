package org.spongepowered.clean.scheduler.condition;

public class AndCondition implements TaskCondition {

    private TaskCondition a;
    private TaskCondition b;

    public AndCondition(TaskCondition a, TaskCondition b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean check() {
        return this.a.check() && this.b.check();
    }

}
