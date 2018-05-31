package tech.valery;

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
        synchronized (this) { // we need to get both sticks simultaneously
            //signal we can take both
            table.waitSticks(this);

            leftChopstick.take();
            sleep(50);
            rightChopstick.take();
        }
    }

    @Override
    public void prepareToThink() throws InterruptedException {
        super.prepareToThink();
        table.stickHasPuttedDown(this);
    }
}
