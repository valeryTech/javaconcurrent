package tech.valery;

public interface Chopstick {
    boolean get();

    void put();

    boolean canGet();

    void setHolder(Philosopher philosopher);

    Philosopher getHolder();
}
