package tech.valery;

public class DependentPhilosopher extends Philosopher {

    public DependentPhilosopher(int seat, Table table) {
        super(seat, table);
    }

    @Override
    public void prepareToEat() throws InterruptedException {

        //need to be atomic
        synchronized (this){
            Chopstick firstChopstick = table.getChopstick(leftChopstick);
            Chopstick secondChopstick = table.getChopstick(rightChopstick);
        }
    }
}
