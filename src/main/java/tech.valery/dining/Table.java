package tech.valery.dining;

import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.philosophers.Philosopher;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Table {

    private final Lock lock = new ReentrantLock();
    private final Condition stateChanged = lock.newCondition();

    private final int participantsNumber;

    private final boolean[] chopstickIsInFreeState;

    private final Chopstick[] sticks;
    private final Philosopher[] philosophers;

    public Table(int participantsNumber, Supplier<Chopstick> stickSupplier,
                 Supplier<Philosopher> philosopherSupplier) {
        this.participantsNumber = participantsNumber;

        chopstickIsInFreeState = new boolean[participantsNumber];
        Arrays.fill(chopstickIsInFreeState, Boolean.TRUE);

        List<Chopstick> csticks = Stream.generate(stickSupplier)
                .limit(participantsNumber)
                .collect(Collectors.toList());

        sticks = new Chopstick[participantsNumber];
        Arrays.setAll(sticks, i -> stickSupplier.get());



        philosophers = new Philosopher[participantsNumber];
        Arrays.setAll(philosophers, i -> philosopherSupplier.get());
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