package tech.valery.dining;

import org.junit.jupiter.api.Test;
import tech.valery.Common;
import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.chopsticks.MisraStatefulStick;
import tech.valery.dining.philosophers.ChandyPhilosopher;
import tech.valery.dining.philosophers.DependentPhilosopher;
import tech.valery.dining.philosophers.Philosopher;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class TableTest {

    @Test
    void PhilosopherShouldWaitUntilAllChopsticksIsAvailable_WhenItWantsToEat() throws InterruptedException {
        int problemSize = 5;

        Table table = new Table(problemSize);

        Philosopher[] philosophers = new DependentPhilosopher[problemSize];
        Arrays.setAll(philosophers, i -> new DependentPhilosopher(i, table));

        Philosopher lockingPhilosopher = philosophers[0];
        Philosopher asquiringPhilosoper = philosophers[1];

        lockingPhilosopher.prepareToEat();

        CompletableFuture.runAsync(()->{
            try {
                asquiringPhilosoper.prepareToEat();
                Common.sleep(10);
                asquiringPhilosoper.prepareToThink();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Common.sleep(10);
        lockingPhilosopher.prepareToThink();
    }

    @Test
    void ShouldRunWithoutDeadlocks_WhenUsingArbitrator() {
        int problemSize = 5;

        Table table = new Table(problemSize);

        Philosopher[] philosophers = new DependentPhilosopher[problemSize];
        Arrays.setAll(philosophers, i -> new DependentPhilosopher(i, table));

        Arrays.stream(philosophers).forEach(CompletableFuture::runAsync);

        Common.sleep(2000);
    }

    @Test
    void ShouldRundWithoutDeadlocks_WhenUsingChandyMisraSolution() {
        int problemSize = 5;
        Supplier<Chopstick> stickSupplier = MisraStatefulStick::new;
        //todo add supplier
        Table table = new Table(problemSize);

        Philosopher[] philosophers = new ChandyPhilosopher[problemSize];
        Arrays.setAll(philosophers, i -> new ChandyPhilosopher(i, table));

        Arrays.stream(philosophers).forEach(CompletableFuture::runAsync);

        Common.sleep(2000);
    }
}