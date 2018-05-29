package tech.valery;

public interface Chopstick {
    void take();

    void put();

    void setHolder(Philosopher philosopher);

    Philosopher getHolder();
}
