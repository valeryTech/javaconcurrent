package tech.valery.dining.philosophers;

import tech.valery.dining.Table;
import tech.valery.dining.chopsticks.Chopstick;

public class SimplePhilosopher extends Philosopher{

    private final long timeBetweenAcquisitions;

    public SimplePhilosopher(Chopstick rightChopstick, int number, Chopstick leftChopstick, Table table, long time) {
        super(number, table);
        this.timeBetweenAcquisitions = time;
    }

    /**
     *  Sleep between acquisition the first and second resources is to regulate contention level.
     * @throws InterruptedException
     */
    @Override
    public void prepareToEat() throws InterruptedException {

        leftChopstick.take();

        sleep(timeBetweenAcquisitions);

        rightChopstick.take();
    }
}
