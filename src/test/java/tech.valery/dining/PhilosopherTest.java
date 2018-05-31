package tech.valery.dining;

import org.junit.jupiter.api.Test;
import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.chopsticks.ScheduleSynchronisedChopstic;
import tech.valery.dining.philosophers.OrderedPhilosopher;

import java.util.ArrayList;
import java.util.List;

import static tech.valery.Common.sleep;

public class PhilosopherTest {

    @Test
    void DiningPhilosophersTest_GivenExplicitLocks() throws InterruptedException {

        Table table = new Table(5);

        List<Chopstick> chopsticks = new ArrayList<Chopstick>();
        int forksNumber = 5;
        for(int i = 0; i < forksNumber; i++){
            chopsticks.add(new ScheduleSynchronisedChopstic(i));
        }

        List<OrderedPhilosopher> philosophers = new ArrayList<OrderedPhilosopher>();

        int philosophersNumber = 5;
        for (int i = 0; i < philosophersNumber - 1; i++) {
            philosophers.add(new OrderedPhilosopher(i, chopsticks.get(i), chopsticks.get(i + 1), table));
        }

        philosophers.add(new OrderedPhilosopher(4, chopsticks.get(forksNumber - 1), chopsticks.get(0), table));

        philosophers.forEach(philosopher -> new Thread(philosopher).start());

        sleep(3000);
    }

}
