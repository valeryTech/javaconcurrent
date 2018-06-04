package tech.valery.dining.philosophers;

public class DependentPhilosopher extends Philosopher {


    public DependentPhilosopher(long eatTime, long thinkTime) {
        super(eatTime, thinkTime);
    }

    /**
     * method guarantees that stick can be taken taken necessary both or none
     * @throws InterruptedException
     */
    @Override
    public void prepareToEat() {
        //table.waitSticks(this);


    }

    @Override
    public void prepareToThink() throws InterruptedException {

        //table.stickHasPuttedDown(this);
    }
}
