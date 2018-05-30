package tech.valery;

import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
    private int number;
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;
    private final Table table;

    public volatile boolean shouldStop;


    public Philosopher(int number, Chopstick leftChopstick, Chopstick rightChopstick, Table table) {
        this.number = number;

        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.table = table;
    }

    @Override
    public String toString() {
        return "P" + number;
    }

    public void stopSignal() {
        shouldStop = true;
    }

    @Override
    public void run() {
        while (true) {
            long timeout = 200;
            try {
                prepareForEat();
                eat(timeout);

                prepareToThink();
                think(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void prepareForEat() throws InterruptedException {
            leftChopstick.take();
            sleep(40);
            rightChopstick.take();
    }

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

    private void sleep(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
