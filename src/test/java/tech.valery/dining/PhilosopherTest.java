package tech.valery.dining;

import org.junit.jupiter.api.Test;
import tech.valery.Common;
import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.chopsticks.LockChopstick;
import tech.valery.dining.philosophers.OrderedPhilosopher;
import tech.valery.dining.philosophers.Philosopher;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class PhilosopherTest {
    @Test
    void ThreadShouldBlock() throws InterruptedException {
        Chopstick stick = new LockChopstick();

        long eatTime = 10;
        long thinkTime = 1;

        Philosopher firstPhilosopher = new OrderedPhilosopher(eatTime, thinkTime);
        firstPhilosopher.addStick(stick);

        Philosopher secondPhilosopher = new OrderedPhilosopher(eatTime, thinkTime);
        secondPhilosopher.addStick(stick);

        firstPhilosopher.prepareToEat();

        CompletableFuture.delayedExecutor(100, TimeUnit.MILLISECONDS).execute(secondPhilosopher::prepareToEat);

        Common.sleep(1500);
        firstPhilosopher.prepareToThink();

        secondPhilosopher.prepareToThink();
    }
}
