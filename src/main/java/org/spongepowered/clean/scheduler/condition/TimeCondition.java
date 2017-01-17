package org.spongepowered.clean.scheduler.condition;

public class TimeCondition implements TaskCondition {

    private long time;

    public TimeCondition(long time) {
        this.time = time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean check() {
        return System.currentTimeMillis() > this.time;
    }

}
