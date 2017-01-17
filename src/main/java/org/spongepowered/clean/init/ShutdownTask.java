package org.spongepowered.clean.init;

import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;

public class ShutdownTask extends Task {

    @Override
    public void execute() {
        System.out.println("Shutdown");

        CoreScheduler.shutdown();
    }

}
