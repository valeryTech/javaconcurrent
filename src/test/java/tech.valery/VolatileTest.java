package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
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

    /**
     * One-time safe publication.
     * An object to publish must be properly constructed, thread-safe, immutable or effectively immutable.
     */
    @Test
    void ShouldDoneSafePublication_When_GivenReferenceFinalType(){

        // we need to check proper publication of fully constructed object,
        // so the parameters of the flooble to construct must be passed to the loader

        int espectedLower = 1;
        int espectedHigher = 1;

        FloobleLoader floobleLoader = new FloobleLoader(espectedLower, espectedHigher);

        CompletableFuture.runAsync(floobleLoader::initInBackGround);

        boolean isPartiallyConstructed = false;

        while (true){
            sleep(500);
            Flooble flooble = floobleLoader.theFlooble;

            if(flooble != null){
                //Check the first attempt, so successive would be proper
                if(espectedLower != flooble.lower || espectedHigher != flooble.higher)
                    isPartiallyConstructed = true;
                break;
            }
        }

        Assertions.assertTrue(!isPartiallyConstructed);
    }
}
