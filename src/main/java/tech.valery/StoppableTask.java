package tech.valery;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class StoppableTask implements Runnable{

    private long runningTime;

    private volatile boolean shouldStop;

    private static final long MAX_TIME_THRESHOLD = 2000000;
    private static final long TIME_STEP = 50;

    public void run() {

        long startTime = System.nanoTime();

        while (!shouldStop && !isTimeIntervalExceeded(startTime)){
            System.out.println("running");

            try {
                TimeUnit.MICROSECONDS.sleep(TIME_STEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        runningTime = System.nanoTime() - startTime;
    }

    private boolean isTimeIntervalExceeded(long startTime) {
        return System.nanoTime() - startTime > MAX_TIME_THRESHOLD;
    }

    public void setStopSignal() {
        shouldStop = true;
    }

    public long getRunningTime() {

        return TimeUnit.MICROSECONDS.convert(runningTime, TimeUnit.NANOSECONDS);
    }
}
