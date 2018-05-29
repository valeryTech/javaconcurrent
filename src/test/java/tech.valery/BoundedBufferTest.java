package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoundedBufferTest {
    @Test
    void ShouldCheckForEmptiness_WhenBufferIsJustCreated(){
        int capacity = 1;
        BoundedBuffer boundedBuffer = new BoundedBuffer(capacity);

        Assertions.assertTrue(boundedBuffer.isEmpty());
    }

    @Test
    void ShouldGetFullState_WhenBufferIsFull(){
        int capacity = 1;
        BoundedBuffer boundedBuffer = new BoundedBuffer(capacity);

        boundedBuffer.doPut(Integer.valueOf(0));

        Assertions.assertTrue(boundedBuffer.isFull());
        Assertions.assertFalse(boundedBuffer.isEmpty());
    }

    @Test
    void ShouldGetElement_WhenIsAdded(){
        int capacity = 10;

        BoundedBuffer boundedBuffer = new BoundedBuffer(capacity);

        int elementToCheck = 0;
        boundedBuffer.doPut(elementToCheck);

        Assertions.assertEquals(elementToCheck, boundedBuffer.doTake());
    }
}
