package tech.valery.dining.philosophers;

import tech.valery.dining.Waiter;

public class DependentPhilosopher extends Philosopher {

    private Waiter waiter;

    public DependentPhilosopher(long eatTime, long thinkTime, Waiter waiter) {
        super(eatTime, thinkTime);
        this.waiter = waiter;
    }

    /**
     * method guarantees that stick can be taken taken necessary both or none
     *
     * @throws InterruptedException
     */
    @Override
    public void prepareToEat() {
        try {
            waiter.waitSticks(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prepareToThink() throws InterruptedException {
        waiter.stickHasPuttedDown(this);
    }
}
