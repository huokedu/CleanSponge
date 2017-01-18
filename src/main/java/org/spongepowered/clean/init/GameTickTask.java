package org.spongepowered.clean.init;

import org.spongepowered.api.GameState;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.World;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.scheduler.condition.AndCondition;
import org.spongepowered.clean.scheduler.condition.ResourceCondition;
import org.spongepowered.clean.scheduler.condition.TasksCompleteCondition;
import org.spongepowered.clean.scheduler.condition.TimeCondition;
import org.spongepowered.clean.world.SWorld;

public class GameTickTask extends Task {

    private long last = System.currentTimeMillis();
    private GameTickReturnTask return_task = new GameTickReturnTask();
    private TasksCompleteCondition task_condition = new TasksCompleteCondition();
    private TimeCondition time_condition = new TimeCondition(0);
    private AndCondition full_condition = new AndCondition(this.task_condition, this.time_condition);

    @Override
    public void execute() {
        this.task_condition.clear();
        for (World world : Sponge.getServer().getWorlds()) {
            SWorld sp_world = (SWorld) world;
            this.task_condition.addTask(sp_world.getTickTask());
            CoreScheduler.addHighTask(sp_world.getTickTask(), new ResourceCondition(sp_world.getMutex()));
        }
        this.time_condition.setTime(this.last + 50);
        CoreScheduler.addHighTask(this.return_task, this.full_condition);
    }

    private class GameTickReturnTask extends Task {

        @Override
        protected void execute() {
            GameTickTask.this.last = System.currentTimeMillis();
            if (SGame.game.getState() != GameState.SERVER_STARTED || getTaskCount() > 20) {
                CoreScheduler.addHighTask(new ShutdownTask());
            } else {
                CoreScheduler.addHighTask(GameTickTask.this);
            }
        }

    }

}
