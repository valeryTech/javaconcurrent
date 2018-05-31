package tech.valery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {

    private final int participantsNumber;

    private final boolean[] isFree;

    private final Map<Chopstick,List<Philosopher>> stickPhilRelation;
    private final Chopstick[] sticks;

    public Table(int participantsNumber) {
        this.participantsNumber = participantsNumber;

        isFree = new boolean[participantsNumber];
        Arrays.fill(isFree, Boolean.TRUE);

        sticks = new LockChopstick[participantsNumber];
        Arrays.setAll(sticks, i -> new LockChopstick(i));



        stickPhilRelation = new HashMap<>();
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

    public void registerPhilosopher(Philosopher philosopher) {

    }

    public Chopstick getRightChopstick(int seat) {
        return sticks[(seat + 1) % participantsNumber];
    }

    public Chopstick getLeftChopstick(int seat) {
        return sticks[seat];
    }
}
