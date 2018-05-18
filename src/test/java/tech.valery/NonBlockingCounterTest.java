package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NonBlockingCounterTest {

    @Test
    void ShouldSetNonBlockingIncrement(){
        NonBlockingCounter counter = new NonBlockingCounter();
        counter.increment();

        Assertions.assertEquals(1, counter.getValue());
    }
}
