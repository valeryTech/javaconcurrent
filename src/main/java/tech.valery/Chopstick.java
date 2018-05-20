package tech.valery;

public class Chopstick {
    private int forkNumber;

    public Chopstick(int forkNumber) {
        this.forkNumber = forkNumber;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "forkNumber=" + forkNumber +
                '}';
    }
}
