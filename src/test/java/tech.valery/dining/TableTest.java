package tech.valery.dining;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.valery.Common;
import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.chopsticks.ScheduleSynchronisedChopstic;
import tech.valery.dining.philosophers.DependentPhilosopher;
import tech.valery.dining.philosophers.OrderedPhilosopher;
import tech.valery.dining.philosophers.Philosopher;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class TableTest {

    @Test
    void ShouldGetTrue_WhenChopstickIsInFreeState(){

        Table table = new Table(1);
        Assertions.assertTrue(table.isChopstickFree(0));
    }

    @Test
    void ShouldChangeChopstickState(){
        Table table = new Table(1);
        table.giveChopstickToPhilosopher(0);

        Assertions.assertFalse(table.isChopstickFree(0));
    }

    @Test
    void ShouldSetHolder_WhenChopstickIsPickedUp() throws InterruptedException {

        Table table = new Table(1);

        Chopstick leftStick = new ScheduleSynchronisedChopstic(0);
        Chopstick rightStick = new ScheduleSynchronisedChopstic(1);

        OrderedPhilosopher ph = new OrderedPhilosopher(0, leftStick, rightStick, table);

        ph.prepareToEat();

        Assertions.assertEquals(ph, leftStick.getHolder());
        Assertions.assertEquals(ph, rightStick.getHolder());
    }

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
}