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
package org.spongepowered.clean.scheduler;

import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.spongepowered.clean.init.ShutdownTask;
import org.spongepowered.clean.scheduler.condition.ConditionedTask;
import org.spongepowered.clean.scheduler.condition.TaskCondition;

import com.google.common.collect.Queues;

public class CoreScheduler {

    private static boolean running;
    private static Worker[] workers = null;

    private static ReentrantLock task_lock = new ReentrantLock();
    private static Condition waiting = task_lock.newCondition();
    private static AtomicInteger waiting_count = new AtomicInteger(0);
    private static Condition stopping = task_lock.newCondition();
    private static PriorityQueue<ConditionedTask> condition_tasks = new PriorityQueue<>();
    private static Deque<Task> high_tasks = Queues.newArrayDeque();
    private static Deque<Task> med_tasks = Queues.newArrayDeque();
    private static Deque<Task> low_tasks = Queues.newArrayDeque();

    public static void init(int worker_count, Task initial_task) {
        if (workers != null) {
            throw new IllegalStateException("Core scheduler already initialized");
        }
        running = true;
        workers = new Worker[worker_count];
        addHighTask(initial_task);
        for (int i = 0; i < worker_count; i++) {
            workers[i] = new Worker(i);
            workers[i].start();
        }
    }

    public static void shutdown() {
        try {
            task_lock.lock();
            running = false;
        } finally {
            task_lock.unlock();
        }
    }

    public static void emergencyShutdown(Throwable e) {
        System.err.println("Sponge has crashed!");
        e.printStackTrace();
        stopWorkers();
        System.exit(0);
    }

    public static void waitForShutdown() throws InterruptedException {
        try {
            task_lock.lock();
            if (!running && waiting_count.get() == workers.length) {
                return;
            }
            stopping.await();
        } finally {
            task_lock.unlock();
        }
    }

    public static void stopWorkers() {
        for (int i = 0; i < workers.length; i++) {
            workers[i].stopWorker();
        }
    }

    static Task fetchTask(int worker_id) throws InterruptedException {
        try {
            task_lock.lock();
            while (true) {
                // Check for previously attempted condition tasks whose
                // conditions may have been fulfilled now
                for (Iterator<ConditionedTask> it = condition_tasks.iterator(); it.hasNext();) {
                    ConditionedTask t = it.next();
                    if (t.getCondition().check()) {
                        it.remove();
                        return t.getTask();
                    }
                }
                // Check for high tasks
                Task task = high_tasks.pollFirst();
                if (task != null) {
                    if (task instanceof ConditionedTask) {
                        // If it is a conditioned task then we need to check its
                        // condition
                        ConditionedTask ctask = (ConditionedTask) task;
                        if (!ctask.getCondition().check()) {
                            condition_tasks.add(ctask);
                            continue;
                        }
                        return ctask.getTask();
                    }
                    return task;
                }
                // Check for normal tasks
                task = med_tasks.pollFirst();
                if (task != null) {
                    if (task instanceof ConditionedTask) {
                        ConditionedTask ctask = (ConditionedTask) task;
                        if (!ctask.getCondition().check()) {
                            condition_tasks.add(ctask);
                            continue;
                        }
                        return ctask.getTask();
                    }
                    return task;
                }
                // Check for low tasks
                task = low_tasks.pollFirst();
                if (task != null) {
                    if (task instanceof ConditionedTask) {
                        ConditionedTask ctask = (ConditionedTask) task;
                        if (!ctask.getCondition().check()) {
                            condition_tasks.add(ctask);
                            continue;
                        }
                        return ctask.getTask();
                    }
                    return task;
                }
                // check how many workers are waiting
                int wait = waiting_count.incrementAndGet();
                if (wait == workers.length && condition_tasks.isEmpty()) {
                    // all workers are waiting and there are no tasks in the
                    // system
                    // but we haven't recieved a shutdown yet, indicates an
                    // error preventing the
                    // next task from being added to the scheduler
                    if (running) {
                        System.err.println("Unexpected end of tasks");
                        waiting_count.getAndDecrement();
                        // TODO: should change to some 'emergency' shutdown task
                        // and drop a crash report
                        return new ShutdownTask();
                    }
                    stopping.signalAll();
                    stopWorkers();
                }
                if (!condition_tasks.isEmpty()) {
                    // await a new task arriving
                    // we wake up periodically to check if any of the pending
                    // conditioned tasks are now satisfied
                    while (true) {
                        if (waiting.await(500, TimeUnit.MICROSECONDS)) {
                            break;
                        }
                        for (Iterator<ConditionedTask> it = condition_tasks.iterator(); it.hasNext();) {
                            ConditionedTask t = it.next();
                            if (t.getCondition().check()) {
                                it.remove();
                                waiting_count.getAndDecrement();
                                return t.getTask();
                            }
                        }
                    }
                } else {
                    // there are no conditioned tasks so we simply await the
                    // next task
                    waiting.await();
                }
                waiting_count.getAndDecrement();
            }
        } finally {
            task_lock.unlock();
        }
    }

    public static void addHighTask(Task task) {
        try {
            task_lock.lock();
            high_tasks.addLast(task);
            waiting.signal();
        } finally {
            task_lock.unlock();
        }
    }

    public static void addHighTask(Task task, TaskCondition condition) {
        addHighTask(new ConditionedTask(task, condition, System.currentTimeMillis() - 0xFFFFFF));
    }

    public static void addNormalTask(Task task) {
        try {
            task_lock.lock();
            med_tasks.addLast(task);
            waiting.signal();
        } finally {
            task_lock.unlock();
        }
    }

    public static void addNormalTask(Task task, TaskCondition condition) {
        addNormalTask(new ConditionedTask(task, condition, System.currentTimeMillis() - 0xFFFF));
    }

    public static void addLowTask(Task task) {
        try {
            task_lock.lock();
            low_tasks.addLast(task);
            waiting.signal();
        } finally {
            task_lock.unlock();
        }
    }

    public static void addLowTask(Task task, TaskCondition condition) {
        addLowTask(new ConditionedTask(task, condition, System.currentTimeMillis()));
    }

}
