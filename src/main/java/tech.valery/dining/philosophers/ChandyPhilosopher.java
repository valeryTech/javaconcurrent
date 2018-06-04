package tech.valery.dining.philosophers;

public class ChandyPhilosopher extends Philosopher{
    public ChandyPhilosopher(long eatTime, long thinkTime) {
        super(eatTime, thinkTime);
    }

    @Override
    public void prepareToEat() {

    }

    @Override
    public void prepareToThink() throws InterruptedException {

    }
}
