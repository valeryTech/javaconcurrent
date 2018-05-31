package tech.valery;

public interface Chopstick {
    void take() throws InterruptedException;

    void put() throws InterruptedException;

    void setHolder(OrderedPhilosopher philosopher);

    OrderedPhilosopher getHolder();

    Boolean isGotten();

    int getId();
}
