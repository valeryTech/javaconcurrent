package tech.valery.dining.philosophers;

import tech.valery.Common;
import tech.valery.dining.chopsticks.Chopstick;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class Philosopher implements Runnable {

    private final long eatTime;
    private final long thinkTime;
    protected List<Chopstick> sticks = new ArrayList<>();
    private PrintWriter printWriter;

    public Philosopher(long eatTime, long thinkTime) {

        this.eatTime = eatTime;
        this.thinkTime = thinkTime;
    }

    /**
     * Adds stick to acquisition list of resources required to achieve
     * the eat for the philosopher
     *
     * @param stick
     */
    public void addStick(Chopstick stick) {
        sticks.add(stick);
    }

    @Override
    public void run() {

        Long startPreparingTime;
        Long endPreparingTime;

        printWriter.print(Thread.currentThread().getId() + "\t");

        try {
            while (true) {
                long timeout = 2;

                startPreparingTime = System.nanoTime();
                prepareToEat();
                endPreparingTime = System.nanoTime();

                eat(timeout);

                prepareToThink();
                think(timeout);

                printWriter.println(Thread.currentThread().getId() + "\t" +
                        TimeUnit.MICROSECONDS.convert(endPreparingTime - startPreparingTime, TimeUnit.NANOSECONDS));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void prepareToEat();

    public abstract void prepareToThink() throws InterruptedException;

    private void eat(long eatDuration) {
        Common.sleep(eatDuration);
    }

    private void think(long thinkDuration) {
        Common.sleep(thinkDuration);
    }

    public int getSeat() {
        return 0;
    }

    public void setWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }
}
