package org.spongepowered.clean.init;

import org.spongepowered.api.GameState;
import org.spongepowered.api.world.World;
import org.spongepowered.clean.SpongeGame;
import org.spongepowered.clean.SpongeServer;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.scheduler.condition.TasksCompleteCondition;
import org.spongepowered.clean.world.SpongeWorld;

public class GameTickTask extends Task {

    private long last = 0;
    private GameTickReturnTask return_task = new GameTickReturnTask();
    private TasksCompleteCondition condition = new TasksCompleteCondition();

    @Override
    public void execute() {
        System.out.println("GameTick");
        this.condition.clear();
        for (World world : SpongeServer.insn.getWorlds()) {
            SpongeWorld sp_world = (SpongeWorld) world;
            this.condition.addTask(sp_world.getTickTask());
            CoreScheduler.addHighTask(sp_world.getTickTask());
        }
        CoreScheduler.addHighTask(this.return_task, this.condition);
    }
    
    private class GameTickReturnTask extends Task {

        @Override
        protected void execute() {
            long next = System.currentTimeMillis();
            long delta = next - GameTickTask.this.last;
            if (delta < 50) {
                // Sleep for 50ms through scheduler
            }
            GameTickTask.this.last = next;
            if (SpongeGame.game.getState() != GameState.SERVER_STARTED || getTaskCount() > 20) {
                CoreScheduler.addHighTask(new ShutdownTask());
            } else {
                CoreScheduler.addHighTask(GameTickTask.this);
            }
        }
        
    }

}
