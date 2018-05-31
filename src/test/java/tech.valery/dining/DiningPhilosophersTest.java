package tech.valery.dining;

import org.junit.jupiter.api.Test;
import tech.valery.Common;
import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.chopsticks.LockChopstick;
import tech.valery.dining.philosophers.OrderedPhilosopher;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class DiningPhilosophersTest {
    @Test
    void ShouldGetToDeadLock_WhenTryToTakeChopsticksSimultaneously() {
        int problemSize = 5;

        Table table = new Table(problemSize);

        Chopstick[] sticks = new LockChopstick[problemSize];
        Arrays.setAll(sticks, i -> new LockChopstick(i));

        OrderedPhilosopher[] philosophers = new OrderedPhilosopher[problemSize];
        Arrays.setAll(philosophers, i -> new OrderedPhilosopher(i, sticks[i], sticks[(i + 1) % problemSize], table));

        Arrays.stream(philosophers).forEach(System.out::println);

        Arrays.stream(philosophers).forEach(CompletableFuture::runAsync);

        Common.sleep(2000);
    }
}
