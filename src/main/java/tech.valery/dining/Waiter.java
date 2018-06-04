package tech.valery.dining;

import tech.valery.dining.philosophers.Philosopher;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter{
    private final boolean[] isFree;

    private final Lock lock = new ReentrantLock();
    private final Condition stateChanged = lock.newCondition();

    public Waiter(int participantsNumber) {
        this.isFree = new boolean[participantsNumber];
        Arrays.fill(this.isFree, Boolean.TRUE);
    }

    public void waitSticks(Philosopher philosopher) throws InterruptedException {

        lock.lock();
        try {
            while (!bothSticksIsFree(philosopher)) {
                stateChanged.await();
            }
            isFree[philosopher.getSeat()] = false;
            isFree[philosopher.getSeat() + 1] = false;
        } finally {
            lock.unlock();
        }
    }

    public void stickHasPuttedDown(Philosopher philosopher) {
        lock.lock();
        try {
            isFree[philosopher.getSeat()] = true;
            isFree[philosopher.getSeat() + 1] = true;
            stateChanged.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private boolean bothSticksIsFree(Philosopher philosopher) {
        return isFree[philosopher.getSeat()] && isFree[philosopher.getSeat() + 1];
    }
}
