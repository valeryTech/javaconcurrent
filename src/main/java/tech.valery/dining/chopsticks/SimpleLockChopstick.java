package tech.valery.dining.chopsticks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleLockChopstick implements Chopstick{
    private final Lock lock = new ReentrantLock();

    public void pickUp(){
        lock.lock();
    }

    public void putDown(){
        lock.unlock();
    }
}
