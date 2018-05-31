package tech.valery;

public class DependentPhilosopher extends Philosopher {

    public DependentPhilosopher(Chopstick rightChopstick,
                                int number, Chopstick leftChopstick, Table table) {
        super(rightChopstick, number, leftChopstick, table);
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
