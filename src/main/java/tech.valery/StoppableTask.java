package tech.valery;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class StoppableTask implements Runnable{

    private long runningTime;

    private volatile boolean shouldStop;

    private static final long MAX_TIME_THRESHOLD = TimeUnit.NANOSECONDS.convert(4, TimeUnit.SECONDS);
    private static final long TIME_STEP = 300;

    public void run() {

        long startTime = System.nanoTime();

        while (!shouldStop && !isTimeIntervalExceeded(startTime)){
            System.out.println("running");

            try {
                TimeUnit.MILLISECONDS.sleep(TIME_STEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        runningTime = System.nanoTime() - startTime;
    }

    private boolean isTimeIntervalExceeded(long startTime) {
        return System.nanoTime() - startTime > MAX_TIME_THRESHOLD;
    }

    public void sendStopSignal() {
        shouldStop = true;
    }

    public long getRunningTime() {
        return TimeUnit.MILLISECONDS.convert(runningTime, TimeUnit.NANOSECONDS);
    }
}
