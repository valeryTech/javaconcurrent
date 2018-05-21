package tech.valery;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PhilosopherTest {

    @Test
    void DiningPhilosophersTest_GivenExplicitLocks() throws InterruptedException {

        List<Chopstick> chopsticks = new ArrayList<Chopstick>();
        int forksNumber = 5;
        for(int i = 0; i < forksNumber; i++){
            chopsticks.add(new SynchronisedChopstick(i));
        }

        List<Philosopher> philosophers = new ArrayList<Philosopher>();

        int philosophersNumber = 5;
        for (int i = 0; i < philosophersNumber - 1; i++) {
            philosophers.add(new Philosopher(i, chopsticks.get(i), chopsticks.get(i + 1)));
        }

        philosophers.add(new Philosopher(4, chopsticks.get(forksNumber - 1), chopsticks.get(0)));

        philosophers.forEach(philosopher -> new Thread(philosopher).start());

        TimeUnit.SECONDS.sleep(5);
    }

}
