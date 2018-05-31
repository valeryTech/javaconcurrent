package tech.valery;

import java.util.concurrent.TimeUnit;

public abstract class Philosopher implements Runnable {

    protected int number;

    protected final Chopstick leftChopstick;
    protected final Chopstick rightChopstick;

    protected final Table table;

    public volatile boolean shouldStop;

    public Philosopher(Chopstick rightChopstick, int number, Chopstick leftChopstick, Table table) {
        this.rightChopstick = rightChopstick;
        this.number = number;
        this.leftChopstick = leftChopstick;
        this.table = table;
    }

    @Override
    public String toString() {
        return "P" + number;
    }

    @Override
    public void run() {
        while (true) {
            long timeout = 200;
            try {
                prepareToEat();
                eat(timeout);

                prepareToThink();
                think(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void prepareToEat() throws InterruptedException;

    private void prepareToThink() throws InterruptedException {
        leftChopstick.put();
        rightChopstick.put();
    }

    private void eat(long eatDuration) {
        System.out.println(this + " eating.");
        sleep(eatDuration);
    }

    private void think(long thinkDuration) {
        sleep(thinkDuration);
    }

    protected void sleep(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
