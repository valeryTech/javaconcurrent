package tech.valery.dining.chopsticks;

import net.jcip.annotations.GuardedBy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MisraStatefulStick implements Chopstick{

    @GuardedBy("this")
    private boolean isClean;

    @GuardedBy("lock")
    private boolean free = true;

    public final Lock lock = new ReentrantLock();

    public final Condition hasFreed = lock.newCondition();

    public MisraStatefulStick() {
        setClean(true);
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    @Override
    public void pickUp() throws InterruptedException {
        lock.lock();
    }

    @Override
    public void putDown() {
        lock.unlock();
        isClean = false;
    }
}
