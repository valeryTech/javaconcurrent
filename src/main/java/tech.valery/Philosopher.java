package tech.valery;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable{
    private int number;
    private final Fork leftFork;
    private final Fork rightFork;
    private CountDownLatch start;

    public volatile boolean shouldStop;



    public Philosopher(int number, Fork leftFork, Fork rightFork, CountDownLatch start) {
        this.number = number;

        this.leftFork = leftFork;
        this.rightFork = rightFork;


        this.start = start;
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "number=" + number +
                ", leftFork=" + leftFork +
                ", rightFork=" + rightFork +
                '}';
    }

    public void stopSignal(){
        shouldStop = true;
    }

    @Override
    public void run() {

        try {
            start.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!shouldStop){
            synchronized (leftFork){
                System.out.println("Philosopher #" + number + ": try to get right lock.");
                synchronized (rightFork){
                    try {
                        System.out.println("Philosopher #" + number + " eating.");
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        try {
            System.out.println("Philosopher #" + number + " thinking.");
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
