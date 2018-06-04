package tech.valery.dining;

import tech.valery.Common;
import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.philosophers.Philosopher;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    private final boolean[] isFree;

    private final List<Chopstick> sticks;
    private final List<Philosopher> philosophers;

    public Table(int participantsNumber, Supplier<Chopstick> stickSupplier,
                 Supplier<Philosopher> philosopherSupplier) {

        this.participantsNumber = participantsNumber;

        this.isFree = new boolean[participantsNumber];
        Arrays.fill(this.isFree, Boolean.TRUE);

        this.sticks = Stream.generate(stickSupplier)
                .limit(participantsNumber)
                .collect(Collectors.toList());

        this.philosophers = Stream.generate(philosopherSupplier)
                .limit(participantsNumber)
                .collect(Collectors.toList());

        //set sticks to philosophers
        for (int seat = 0; seat < participantsNumber; seat++) {
            philosophers.get(seat).addStick(sticks.get(seat));
            philosophers.get(seat).addStick(sticks.get((seat + 1) % participantsNumber));
        }
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

    public void startSimulation() {
        ExecutorService service = Executors.newFixedThreadPool(participantsNumber);
        philosophers.forEach((philosopher) -> service.submit(philosopher::run));
    }

    public void show() {
        System.out.println(Common.asString(philosophers));
    }
}