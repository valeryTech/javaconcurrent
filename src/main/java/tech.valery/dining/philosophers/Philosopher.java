package tech.valery.dining.philosophers;

import tech.valery.dining.chopsticks.Chopstick;

import java.util.List;

public abstract class Philosopher implements Runnable {

    protected List<Chopstick> sticks;

    /**
     * Adds stick to acquisition list of resources required to achieve
     * the eat for the philosopher
     * @param stick
     */
    public void addStick(Chopstick stick){
        sticks.add(stick);
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

    public abstract void prepareToThink() throws InterruptedException;

    private void eat(long eatDuration) {
        sleep(eatDuration);
    }

    private void think(long thinkDuration) {
        sleep(thinkDuration);
    }

    protected void sleep(long sleepDuration) {
        sleep(sleepDuration);
    }

    public int getSeat() {
        return 0;
    }
}
