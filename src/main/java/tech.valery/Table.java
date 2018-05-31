package tech.valery;

import java.util.Arrays;

public class Table {

    private final int participantsNumber;

    private boolean[] isFree;

    public Table(int participantsNumber) {
        this.participantsNumber = participantsNumber;
        isFree = new boolean[participantsNumber];
        Arrays.fill(isFree, Boolean.TRUE);
    }

    public boolean isChopstickFree(int chopstickId) {
        return isFree[chopstickId];
    }

    public void getChopstick(int chopstickId) {
        isFree[chopstickId] = false;
    }

    public Chopstick getChopstick(Chopstick stickToReturn) {
        return null;
    }
}
