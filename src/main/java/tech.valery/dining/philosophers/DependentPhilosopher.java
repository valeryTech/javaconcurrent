package tech.valery.dining.philosophers;

import tech.valery.dining.Table;

public class DependentPhilosopher extends Philosopher {

    public DependentPhilosopher(int seat, Table table) {
        super(seat, table);
    }

    /**
     * method guarantees that stick can be taken taken necessary both or none
     * @throws InterruptedException
     */
    @Override
    public void prepareToEat() throws InterruptedException {
        table.waitSticks(this);

        leftChopstick.take();
        sleep(50);
        rightChopstick.take();
    }

    @Override
    public void prepareToThink() throws InterruptedException {

        table.stickHasPuttedDown(this);
    }
}
