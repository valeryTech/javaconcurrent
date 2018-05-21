package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TableTest {

    @Test
    void ShouldGetTrue_WhenChopstickIsInFreeState(){

        Table table = new Table(5);
        Assertions.assertTrue(table.isChopstickFree(0));
    }

    @Test
    void ShouldChangeChopstickState(){
        Table table = new Table(5);
        table.getChopstick(0);

        Assertions.assertFalse(table.isChopstickFree(0));
    }
}
