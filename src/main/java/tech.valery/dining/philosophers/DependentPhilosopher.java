package tech.valery.dining.philosophers;

public class DependentPhilosopher extends Philosopher {

    /**
     * method guarantees that stick can be taken taken necessary both or none
     * @throws InterruptedException
     */
    @Override
    public void prepareToEat() throws InterruptedException {
        //table.waitSticks(this);


    }

    @Override
    public void prepareToThink() throws InterruptedException {

        //table.stickHasPuttedDown(this);
    }
}
