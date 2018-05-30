package tech.valery;

public interface Chopstick {
    void take() throws InterruptedException;

    void put() throws InterruptedException;

    void setHolder(Philosopher philosopher);

    Philosopher getHolder();

    Boolean isGotten();
}
