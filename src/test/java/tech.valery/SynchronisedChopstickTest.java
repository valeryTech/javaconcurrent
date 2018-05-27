package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class SynchronisedChopstickTest {

    private volatile int anotherThreadGetsNumber;

    @Test
    void ShouldGetBusyState_WhenChopsickIsAlreadyTaken() {
        Chopstick chopstick = new SynchronisedChopstick(0);
        chopstick.get();

        Assertions.assertTrue(!chopstick.canGet());
    }

    @Test
    void ShouldBlockThreadsAttemptingToGetChopstick_WhenChopsickIsAlreadyGotten()
            throws ExecutionException, InterruptedException {

        Chopstick chopstick = new SynchronisedChopstick(1);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Callable<Long> r = ()->{
            long startTime = System.nanoTime();

            chopstick.get();

            long blockingTime = TimeUnit.MILLISECONDS
                    .convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);

            return blockingTime;
        };

        Future<Long> blockedTimeFuture = scheduler.schedule(r, 100, TimeUnit.MILLISECONDS);

        chopstick.get();

        long blockedTime = blockedTimeFuture.get();

        Assertions.assertTrue(300 - 10 < blockedTime && blockedTime < 300 + 1);
    }
}
