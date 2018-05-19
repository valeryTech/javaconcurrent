package tech.valery;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class PhilosopherTest {
    @Test
    void DiningPhilosophersTest_GivenExplicitLocks() throws InterruptedException {

        List<Fork> forks = new ArrayList<Fork>();
        int forksNumber = 5;
        for(int i = 0; i < forksNumber; i++){
            forks.add(new Fork(i));
        }

        List<Philosopher> philosophers = new ArrayList<Philosopher>();

        CountDownLatch start = new CountDownLatch(1);

        int philosophersNumber = 5;
        for (int i = 0; i < philosophersNumber - 1; i++) {
            philosophers.add(new Philosopher(i, forks.get(i), forks.get(i + 1), start));
        }

        philosophers.add(new Philosopher(4, forks.get(forksNumber - 1), forks.get(0), start));

        philosophers.forEach(philosopher -> new Thread(philosopher).start());

        start.countDown();

        TimeUnit.SECONDS.sleep(3);

    }

}
