package tech.valery;

import net.jcip.annotations.GuardedBy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockChopstick implements Chopstick {

    @GuardedBy("this") private boolean free = true;

    private final Lock lock = new ReentrantLock();

    private Condition hasFreed = lock.newCondition();
    private Condition hasTaken = lock.newCondition();


    @Override
    public void take() throws InterruptedException {

        lock.lock();
        try {
            while (!free){
                hasFreed.await();
            }

            free = false;
            hasTaken.signal();

        }finally {
            lock.unlock();
        }
    }

    @Override
    public void put() throws InterruptedException {
        lock.lock();
        try {
            free = true;
            hasFreed.signal();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void setHolder(Philosopher philosopher) {

    }

    @Override
    public Philosopher getHolder() {
        return null;
    }

    @Override
    public Boolean isGotten() {
        return true;
    }
}
