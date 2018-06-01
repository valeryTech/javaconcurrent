package tech.valery.dining.philosophers;

import tech.valery.dining.chopsticks.Chopstick;

import java.util.ArrayList;
import java.util.List;

public class OrderedPhilosopher extends Philosopher {

    private List<Chopstick> holdedChopsticks = new ArrayList<>();

    /**
     * The partial order of resources (sticks) is used in resource hierarchy solution to dining problems.
     *
     * @throws InterruptedException
     */
    @Override
    public void prepareToEat() throws InterruptedException {
        try {
            for (Chopstick chopstick : sticks) {
                chopstick.take();
                holdedChopsticks.add(chopstick);
            }
        } catch (InterruptedException e) {
            // Unlock all previous locked sticks in the case of failure
            giveUpAllSticks();
            throw new InterruptedException("There is an exception during trying to lock");
        }
    }

    @Override
    public void prepareToThink() throws InterruptedException {
        giveUpAllSticks();
    }

    private void giveUpAllSticks() {
        holdedChopsticks.forEach(Chopstick::put);
        holdedChopsticks.clear();
    }
}
