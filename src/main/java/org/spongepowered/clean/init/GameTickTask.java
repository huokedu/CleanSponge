/*
 * This file is part of SpongeClean, licensed under the MIT License (MIT).
 *
 * Copyright (c) The VoxelBox <http://thevoxelbox.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
        SGame.game.getNetworkManager().update();
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
            if (SGame.game.getState() != GameState.SERVER_STARTED) {
                CoreScheduler.addHighTask(new ShutdownTask());
            } else {
                CoreScheduler.addHighTask(GameTickTask.this);
            }
        }

    }

}
