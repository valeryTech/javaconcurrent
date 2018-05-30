package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        Philosopher ph = new Philosopher(0, leftStick, rightStick, table);

        ph.prepareForEatOrdered();

        Assertions.assertEquals(ph, leftStick.getHolder());
        Assertions.assertEquals(ph, rightStick.getHolder());
    }
}