package tech.valery;

public interface Chopstick {
    void get();

    void put();

    boolean canGet();

    void setHolder(Philosopher philosopher);

    Philosopher getHolder();
}
