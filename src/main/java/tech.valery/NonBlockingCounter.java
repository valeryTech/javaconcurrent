package tech.valery;

import java.util.concurrent.atomic.AtomicInteger;

public class NonBlockingCounter {

    public NonBlockingCounter() {
        this.value = new AtomicInteger();
    }

    private AtomicInteger value;

    public int increment() {
        int v;
        do {
            v = value.get();
        }
        while (!value.compareAndSet(v, v + 1));

        return v + 1;
    }

    public int getValue() {
        return value.get();
    }
}
