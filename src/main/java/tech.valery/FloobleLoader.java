package tech.valery;

import java.util.concurrent.TimeUnit;

public class FloobleLoader {

    public volatile Flooble theFlooble;

    private int lower;
    private int higher;

    public FloobleLoader(int lower, int higher) {
        this.lower = lower;
        this.higher = higher;
    }

    public void initInBackGround() {

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        theFlooble = new Flooble(lower, higher);
    }
}
