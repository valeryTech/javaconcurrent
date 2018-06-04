package tech.valery.dining.philosophers;

public class ChandyPhilosopher extends Philosopher{
    public ChandyPhilosopher(long eatTime, long thinkTime) {
        super(eatTime, thinkTime);
    }

    @Override
    public void prepareToEat() {

        //neighbours

        // obtain the absent forks from contending neighbours
    }

    @Override
    public void prepareToThink() throws InterruptedException {
        // check all the requests give up dirty,
        // if dirty -> clean & send
        // if clean -> keep
    }

    // override eat -> forks shoud be dirty after 1 use

}
