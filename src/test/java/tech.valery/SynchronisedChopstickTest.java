package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class SynchronisedChopstickTest {

    private volatile int anotherThreadGetsNumber;

    @Test
    void ShouldBlockThreadsAttemptingToGetChopstick_WhenChopsickIsAlreadyGotten()
            throws ExecutionException, InterruptedException {

        Chopstick chopstick = new ScheduleSynchronisedChopstic(1);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Callable<Long> getAndPut = ()->{
            long startTime = System.nanoTime();

            chopstick.take();

            long blockingTime = TimeUnit.MILLISECONDS
                    .convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);

            Common.sleep(100);

            chopstick.put();

            return blockingTime;
        };

        Future<Long> blockedTimeFuture = scheduler.schedule(getAndPut, 100, TimeUnit.MILLISECONDS);

        chopstick.take();
        Common.sleep(200);
        chopstick.put();

        long blockedTime = blockedTimeFuture.get();
        System.out.println(blockedTime);
        Assertions.assertTrue(100 - 5 < blockedTime && blockedTime < 100 + 5);
    }
}
