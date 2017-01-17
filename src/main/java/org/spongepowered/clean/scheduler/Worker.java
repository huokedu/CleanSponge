package org.spongepowered.clean.scheduler;

public class Worker extends Thread {

    private final int id;
    private boolean running = true;

    Worker(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (this.running) {
            try {
                Task next = CoreScheduler.fetchTask(this.id);
                next.run();
            } catch (InterruptedException e) {
                if (this.running) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stopWorker() {
        this.running = false;
        this.interrupt();
    }

}
