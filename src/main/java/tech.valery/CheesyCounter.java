package tech.valery;

import net.jcip.annotations.GuardedBy;

public class CheesyCounter {

    @GuardedBy("this") private volatile int value;

    public synchronized int increment() {
        return value++;
    }

    public int getValue() {
        return value;
    }
}
