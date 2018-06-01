package tech.valery.dining.chopsticks;

import net.jcip.annotations.GuardedBy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Chopstick uses the ReentrantLock to provide a concurrency safety to the client classes.
 * Condition queue is explicit and presented as {@code ReentrantLock}.
 *
 * Important issue in the protocol: stick can not be given up by an another thread
 * when is still being holden by the current. So there is requirement to consumer classes:
 *
 * One should uses {@code put()} only after corresponding {@code take()} method.
 *
 */
public class LockChopstick implements Chopstick {

    @GuardedBy("lock")
    private boolean free = true;

    public final Lock lock = new ReentrantLock();

    public final Condition hasFreed = lock.newCondition();

    @Override
    public void take() throws InterruptedException {
        lock.lock();
        try {
            while (!free) {
                hasFreed.await();
            }
            free = false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void put() {
        lock.lock();
        try {
            free = true;
            hasFreed.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
