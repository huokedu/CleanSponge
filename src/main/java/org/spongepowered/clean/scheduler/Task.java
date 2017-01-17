package org.spongepowered.clean.scheduler;

public abstract class Task implements Runnable {

    private int run_count = 0;

    public Task() {

    }

    public int getTaskCount() {
        return this.run_count;
    }

    protected abstract void execute();

    @Override
    public final void run() {
        execute();
        this.run_count++;
    }

}
