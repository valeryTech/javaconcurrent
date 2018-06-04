package tech.valery.dining.philosophers;

import net.jcip.annotations.GuardedBy;
import tech.valery.dining.chopsticks.Chopstick;

import java.util.ArrayList;
import java.util.List;

public class OrderedPhilosopher extends Philosopher {

    @GuardedBy("this")
    private final List<Chopstick> holdedChopsticks;

    public OrderedPhilosopher(long eatTime, long thinkTime) {
        super(eatTime, thinkTime);
        holdedChopsticks = new ArrayList<>();
    }

    /**
     * The partial order of resources (sticks) is used in resource hierarchy solution to dining problems.
     *
     * @throws InterruptedException
     */
    @Override
    public void prepareToEat() {
        try {
            // ensure atomicity
            synchronized (this) {
                for (Chopstick chopstick : sticks) {
                    chopstick.take();
                    holdedChopsticks.add(chopstick);
                }
            }
        } catch (InterruptedException e) {
            // Unlock all previous locked sticks in the case of failure
            giveUpAllSticks();
            e.printStackTrace();
        }
    }

    @Override
    public void prepareToThink() throws InterruptedException {
        giveUpAllSticks();
    }

    private synchronized void giveUpAllSticks() {
        holdedChopsticks.forEach(Chopstick::put);
        holdedChopsticks.clear();
    }

    @Override
    public void addStick(Chopstick stick) {
        super.addStick(stick);
    }

    @Override
    public String toString() {
        return "P";
    }
}
