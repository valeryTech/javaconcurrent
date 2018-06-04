package tech.valery.dining.chopsticks;

public class ScheduleSynchronisedChopstic implements Chopstick {

    @Override
    public synchronized void pickUp() {
        ;
    }

    @Override
    public synchronized void putDown() {
        ;
    }

}