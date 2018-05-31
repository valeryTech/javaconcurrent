package tech.valery.dining.chopsticks;

import net.jcip.annotations.GuardedBy;
import tech.valery.dining.philosophers.OrderedPhilosopher;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Chopstick uses the ReentrantLock to provide concurrency safety to the client classes.
 * Condition queue is explicit.
 */
public class LockChopstick implements Chopstick {

    @GuardedBy("this")
    private final int id;

    @GuardedBy("this")
    private boolean free = true;

    public final Lock lock = new ReentrantLock();

    public final Condition hasFreed = lock.newCondition();

    public LockChopstick(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

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
    public void put() throws InterruptedException {
        lock.lock();
        try {
            free = true;
            hasFreed.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void setHolder(OrderedPhilosopher philosopher) {

    }

    @Override
    public OrderedPhilosopher getHolder() {
        return null;
    }

    @Override
    public Boolean isGotten() {
        return !free;
    }

    @Override
    public String toString() {
        return "stick{" +
                "id=" + id +
                ", free=" + free +
                '}';
    }
}
