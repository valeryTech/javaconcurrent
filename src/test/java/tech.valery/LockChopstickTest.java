package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LockChopstickTest {

    @Test
    void ShouldGetProperState_WhenIsTaken() throws InterruptedException {

        Chopstick chopstick = new LockChopstick(0);
        chopstick.take();

        Boolean gotten = chopstick.isGotten();

        chopstick.put();

        Assertions.assertTrue(gotten);
    }

    @Test
    void ShouldThreadsBlockOnGet_WhenChopstickIsAlreadyGotten() throws InterruptedException {
        Chopstick chopstick = new LockChopstick(0);
        chopstick.take();

        Executor executor = Executors.newFixedThreadPool(2);

        executor.execute(() -> {
            try {
                chopstick.take();
                Common.sleep(10);
                chopstick.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Common.sleep(10);
        chopstick.put();
    }
}
