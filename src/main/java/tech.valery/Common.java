package tech.valery;

import java.util.concurrent.TimeUnit;

public class Common {
    public static void sleep(int timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
