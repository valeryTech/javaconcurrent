package tech.valery.dining.philosophers;

import tech.valery.dining.Table;
import tech.valery.dining.chopsticks.Chopstick;

public class OrderedPhilosopher extends Philosopher {

    public OrderedPhilosopher(int number, Chopstick leftChopstick, Chopstick rightChopstick, Table table) {
        super(number, table);
    }

    public void stopSignal() {
        shouldStop = true;
    }

    /**
     * The partial order of resources is used in resource hierarchy solution to dining problems.
     * @throws InterruptedException
     */
    public void prepareToEat() throws InterruptedException {
        Chopstick firstChopstcik = leftChopstick.getId() < rightChopstick.getId() ? leftChopstick : rightChopstick;
        Chopstick secondChopstick = leftChopstick.getId() < rightChopstick.getId() ? rightChopstick : leftChopstick;

        firstChopstcik.take();
        sleep(40);
        secondChopstick.take();
    }

}
