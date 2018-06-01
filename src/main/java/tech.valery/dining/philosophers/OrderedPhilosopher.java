package tech.valery.dining.philosophers;

import tech.valery.dining.Table;
import tech.valery.dining.chopsticks.Chopstick;

public class OrderedPhilosopher extends Philosopher {

    public OrderedPhilosopher(int number, Table table) {
        super(number, table);
    }

    /**
     * The partial order of resources (sticks) is used in resource hierarchy solution to dining problems.
     * @throws InterruptedException
     */
    public void prepareToEat() throws InterruptedException {
        sticks.forEach(this::acquireChopstick);
    }

    private void acquireChopstick(Chopstick chopstick) {
        try {
            chopstick.take();
        } catch (InterruptedException e) {
            // Unlock all previous locked sticks in the case of failure
            e.printStackTrace();
        }
    }

}
