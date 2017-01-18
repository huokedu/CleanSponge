package org.spongepowered.clean.scheduler.game;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.spongepowered.api.scheduler.Scheduler;
import org.spongepowered.api.scheduler.SpongeExecutorService;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.scheduler.Task.Builder;

public class SScheduler implements Scheduler {

    @Override
    public Builder createTaskBuilder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Task> getTaskById(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Task> getTasksByName(String pattern) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Task> getScheduledTasks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Task> getScheduledTasks(boolean async) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Task> getScheduledTasks(Object plugin) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getPreferredTickInterval() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public SpongeExecutorService createSyncExecutor(Object plugin) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SpongeExecutorService createAsyncExecutor(Object plugin) {
        // TODO Auto-generated method stub
        return null;
    }

}
