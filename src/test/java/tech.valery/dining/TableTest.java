package tech.valery.dining;

import org.junit.jupiter.api.Test;
import tech.valery.Common;
import tech.valery.dining.chopsticks.LockChopstick;
import tech.valery.dining.philosophers.OrderedPhilosopher;
import tech.valery.dining.philosophers.Philosopher;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.Supplier;

public class TableTest {

    private Table table;

    @Test
    void ShouldRunWithoutDeadlocks_WhenOrderingResources() {

        long eatTime = 10;
        long thinkTime = 5;

        Supplier<Philosopher> philosopherSupplier = () -> new OrderedPhilosopher(eatTime, thinkTime);

        try (PrintWriter printWriter = new PrintWriter("log.txt")) {
            table = new Table(5, LockChopstick::new, philosopherSupplier);
            table.setLogFile(printWriter);

            table.startSimulation();
            Common.sleep(1000);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}