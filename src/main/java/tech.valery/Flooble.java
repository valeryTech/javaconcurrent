package tech.valery;

import java.util.concurrent.TimeUnit;

public class Flooble {
    public final int lower;
    public final int higher;

    public Flooble(int lower, int higher) {
        this.lower = lower;

        //add time delay to possible reveal of partially constructed object
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.higher = higher;
    }

    @Override
    public String toString() {
        return "Flooble{" +
                "lower=" + lower +
                ", higher=" + higher +
                '}';
    }
}
