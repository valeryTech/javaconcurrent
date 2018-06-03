package tech.valery;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Common {
    public static void sleep(int timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T> String asString(List<T> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\t"));
    }
}
