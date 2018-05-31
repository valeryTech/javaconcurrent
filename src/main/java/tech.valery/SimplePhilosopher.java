package tech.valery;

public class SimplePhilosopher extends Philosopher{

    public SimplePhilosopher(Chopstick rightChopstick, int number, Chopstick leftChopstick, Table table) {
        super(number, table);
    }

    @Override
    public void prepareToEat() throws InterruptedException {
        leftChopstick.take();
        sleep(40);
        rightChopstick.take();
    }
}
