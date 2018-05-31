package tech.valery.dining.chopsticks;

import tech.valery.dining.philosophers.OrderedPhilosopher;

public interface Chopstick {
    void take() throws InterruptedException;

    void put() throws InterruptedException;

    void setHolder(OrderedPhilosopher philosopher);

    OrderedPhilosopher getHolder();

    Boolean isGotten();

    int getId();
}
