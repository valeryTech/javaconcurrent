package tech.valery.dining.philosophers;

import tech.valery.dining.Table;
import tech.valery.dining.chopsticks.Chopstick;

public abstract class Philosopher implements Runnable {

    protected final int seat;

    protected final Chopstick leftChopstick;
    protected final Chopstick rightChopstick;

    protected final Table table;

    public Philosopher(int seat, Table table) {
        this.rightChopstick = table.getRightChopstick(seat);
        this.leftChopstick = table.getLeftChopstick(seat);

        this.seat = seat;
        this.table = table;
    }

    @Override
    public String toString() {
        return "P" + seat;
    }

    public int getSeat() {
        return seat;
    }

    @Override
    public void run() {
        try {
            while (true) {
                long timeout = 200;

                prepareToEat();
                eat(timeout);

                prepareToThink();
                think(timeout);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void prepareToEat() throws InterruptedException;

    public void prepareToThink() throws InterruptedException {
        leftChopstick.put();
        rightChopstick.put();
    }

    private void eat(long eatDuration) {
        sleep(eatDuration);
    }

    private void think(long thinkDuration) {
        sleep(thinkDuration);
    }

    protected void sleep(long sleepDuration) {
        sleep(sleepDuration);
    }
}
