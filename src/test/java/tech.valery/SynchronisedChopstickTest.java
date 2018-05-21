package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;


public class SynchronisedChopstickTest {

    private volatile int anotherThreadGetsNumber;

    @Test
    void ShouldGetBusyState_WhenChopsickIsAlreadyTaken() {
        Chopstick chopstick = new SynchronisedChopstick(1);
        chopstick.get();

        Assertions.assertTrue(!chopstick.canGet());
    }

    @Test
    void ShouldBlockThreadsAttemptingToGetChopstick_WhenChopsickIsAlreadyTaken() {
        Chopstick chopstick = new SynchronisedChopstick(1);
        chopstick.get();

        anotherThreadGetsNumber = 0;

        Runnable r = () -> {
            while (true) {

                boolean isGetted = chopstick.get();
                if(isGetted) anotherThreadGetsNumber++;

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(r).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(2, anotherThreadGetsNumber);
    }

}
