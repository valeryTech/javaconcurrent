package tech.valery.dining.philosophers;

import tech.valery.dining.Table;
import tech.valery.dining.chopsticks.Chopstick;

public class SimplePhilosopher extends Philosopher{

    public SimplePhilosopher(Chopstick rightChopstick, int number, Chopstick leftChopstick, Table table) {
        super(number, table);
    }

    @Override
    public void prepareToEat() throws InterruptedException {
        leftChopstick.take();
        sleep(40);
        rightChopstick.take();
    }
}
