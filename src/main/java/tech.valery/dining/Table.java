package tech.valery.dining;

import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.philosophers.ChandyPhilosopher;
import tech.valery.dining.philosophers.Philosopher;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Table {

    private final Lock lock = new ReentrantLock();
    private final Condition stateChanged = lock.newCondition();

    private final int participantsNumber;

    private final boolean[] chopstickIsInFreeState;

    private final Chopstick[] sticks;

    public Table(int participantsNumber, Supplier<Chopstick> stickSupplier,
                 BiFunction<Integer, Table, Philosopher> philosopherFactory) {
        this.participantsNumber = participantsNumber;

        chopstickIsInFreeState = new boolean[participantsNumber];
        Arrays.fill(chopstickIsInFreeState, Boolean.TRUE);

        sticks = new Chopstick[participantsNumber];
        Arrays.setAll(sticks, i -> stickSupplier.get());

        Philosopher[] philosophers = new ChandyPhilosopher[participantsNumber];
        Arrays.setAll(philosophers, i -> philosopherFactory.apply(i, this));
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
                stateChanged.await();
            }
            chopstickIsInFreeState[philosopher.getSeat()] = false;
            chopstickIsInFreeState[philosopher.getSeat() + 1] = false;
        } finally {
            lock.unlock();
        }
    }

    public void stickHasPuttedDown(Philosopher philosopher) {
        lock.lock();
        try {
            chopstickIsInFreeState[philosopher.getSeat()] = true;
            chopstickIsInFreeState[philosopher.getSeat() + 1] = true;
            stateChanged.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private boolean bothSticksIsFree(Philosopher philosopher) {
        return chopstickIsInFreeState[philosopher.getSeat()] && chopstickIsInFreeState[philosopher.getSeat() + 1];
    }

    public void startSimulation() {

    }
}