package org.spongepowered.clean.init;

import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;

public class StartupTask extends Task {

    @Override
    public void run() {
        System.out.println("Startup");

        CoreScheduler.addHighTask(new GameTickTask());
    }

}
