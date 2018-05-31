package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TableTest {

    @Test
    void ShouldGetTrue_WhenChopstickIsInFreeState(){

        Table table = new Table(1);
        Assertions.assertTrue(table.isChopstickFree(0));
    }

    @Test
    void ShouldChangeChopstickState(){
        Table table = new Table(1);
        table.getChopstick(0);

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
    void ShouldWaitUntilAllChopsticksIsAvailable_WhenTableIsArbitrator(){
        int problemSize = 5;

        Table table = new Table(problemSize);

        Philosopher[] philosophers = new DependentPhilosopher[problemSize];
        Arrays.setAll(philosophers, i -> new DependentPhilosopher(i, table));


    }
}