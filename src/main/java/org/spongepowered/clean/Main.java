package org.spongepowered.clean;

import org.spongepowered.clean.init.StartupTask;
import org.spongepowered.clean.scheduler.CoreScheduler;

public class Main {

    public static void main(String[] args) {
        CoreScheduler.init(1, new StartupTask());
        try {
            CoreScheduler.waitForShutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CoreScheduler.stopWorkers();
    }
}
