package org.spongepowered.clean.init;

import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;

public class GameTickTask extends Task {

    @Override
    public void run() {
        System.out.println("GameTick");

        CoreScheduler.addHighTask(new ShutdownTask());
    }

}
