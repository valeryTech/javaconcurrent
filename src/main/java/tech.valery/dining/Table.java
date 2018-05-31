package tech.valery.dining;

import net.jcip.annotations.GuardedBy;
import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.chopsticks.LockChopstick;
import tech.valery.dining.philosophers.Philosopher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {

    private final Lock lock = new ReentrantLock();
    private final Condition stateChanged = lock.newCondition();

    private final int participantsNumber;

    private final boolean[] isFree;

    private final Chopstick[] sticks;

    @GuardedBy("this")
    private final Map<Chopstick, List<Philosopher>> stickPhilRelation;

    public Table(int participantsNumber) {
        this.participantsNumber = participantsNumber;

        isFree = new boolean[participantsNumber];
        Arrays.fill(isFree, Boolean.TRUE);

        sticks = new LockChopstick[participantsNumber];
        Arrays.setAll(sticks, i -> new LockChopstick(i));

        stickPhilRelation = new HashMap<>();
    }

    public boolean isChopstickFree(int chopstickId) {
        return isFree[chopstickId];
    }

    public void giveChopstickToPhilosopher(int chopstickId) {
        isFree[chopstickId] = false;
    }

    public synchronized void registerPhilosopher(Philosopher philosopher) {
    }

    public Chopstick getRightChopstick(int seat) {
        return sticks[(seat + 1) % participantsNumber];
    }

    public Chopstick getLeftChopstick(int seat) {
        return sticks[seat];
    }

    public void waitSticks(Philosopher philosopher) throws InterruptedException {

        lock.lock();
        try {
            while (!bothSticksIsFree(philosopher)) {
                System.out.println(Thread.currentThread().getId() + " is waiting for conditions");
                stateChanged.await();
                System.out.println(Thread.currentThread().getId() + " waited for true conditions");
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
