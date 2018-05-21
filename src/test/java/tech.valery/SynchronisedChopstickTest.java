package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static tech.valery.Common.sleep;


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

                boolean isGotten = chopstick.get();
                if(isGotten) anotherThreadGetsNumber++;

                sleep(1000);
            }
        };

        new Thread(r).start();

        sleep(2000);

        Assertions.assertEquals(2, anotherThreadGetsNumber);
    }



}
