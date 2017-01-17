package org.spongepowered.clean.scheduler;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CoreScheduler {

    private static boolean running;
    private static Worker[] workers = null;

    private static ReentrantLock task_lock = new ReentrantLock();
    private static Condition waiting = task_lock.newCondition();
    private static AtomicInteger waiting_count = new AtomicInteger(0);
    private static Condition stopping = task_lock.newCondition();
    private static ConcurrentLinkedDeque<Task> high_tasks = new ConcurrentLinkedDeque<>();
    private static ConcurrentLinkedDeque<Task> med_tasks = new ConcurrentLinkedDeque<>();
    private static ConcurrentLinkedDeque<Task> low_tasks = new ConcurrentLinkedDeque<>();

    public static void init(int worker_count) {
        if (workers != null) {
            throw new IllegalStateException("Core scheduler already initialized");
        }
        running = true;
        workers = new Worker[worker_count];

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
                Task task = high_tasks.pollFirst();
                if (task != null) {
                    return task;
                }
                task = med_tasks.pollFirst();
                if (task != null) {
                    return task;
                }
                task = low_tasks.pollFirst();
                if (task != null) {
                    return task;
                }
                int wait = waiting_count.incrementAndGet();
                if (!running && wait == workers.length) {
                    stopping.signalAll();
                }
                waiting.await();
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

    public static void addNormalTask(Task task) {
        try {
            task_lock.lock();
            med_tasks.addLast(task);
            waiting.signal();
        } finally {
            task_lock.unlock();
        }
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
}
