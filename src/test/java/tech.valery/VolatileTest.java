package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

    @Test
    void FlagChangesShouldBeVisible_WhenStateIsChanged(){

        StoppableTask stoppableTask = new StoppableTask();

        long timeToWait = 1200;
        long epsilon = 10;

        Thread runner = new Thread(stoppableTask);
        runner.start();

        try {
            System.out.println("main thread: start sleep");
            TimeUnit.MICROSECONDS.sleep(timeToWait);
            System.out.println("main thread: end sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stoppableTask.setStopSignal();

        long runningTime = TimeUnit.MICROSECONDS.convert(stoppableTask.getRunningTime(), TimeUnit.NANOSECONDS);

        Assertions.assertTrue(runningTime < timeToWait + epsilon);
    }


}
