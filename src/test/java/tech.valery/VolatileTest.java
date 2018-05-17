package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

    @Test
    void FlagChangesShouldBeVisible_WhenStateIsChangedFromAnotherThread(){

        StoppableTask stoppableTask = new StoppableTask();

        long timeToWait = 1200; //milliseconds
        long epsilon = 10; //milliseconds

        Thread runner = new Thread(stoppableTask);
        runner.start();

        System.out.println("main thread: start sleep");
        sleep(timeToWait);
        System.out.println("main thread: end sleep");

        stoppableTask.sendStopSignal();
        long runningTime = TimeUnit.MILLISECONDS.convert(stoppableTask.getRunningTime(), TimeUnit.NANOSECONDS);

        sleep(timeToWait);

        Assertions.assertTrue(runningTime < timeToWait + epsilon);
    }

    private void sleep(long timeToWait) {
        try {

            TimeUnit.MILLISECONDS.sleep(timeToWait);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
