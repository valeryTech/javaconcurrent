package tech.valery.dining;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.valery.Common;
import tech.valery.dining.chopsticks.LockChopstick;
import tech.valery.dining.chopsticks.SimpleLockChopstick;
import tech.valery.dining.philosophers.DependentPhilosopher;
import tech.valery.dining.philosophers.OrderedPhilosopher;
import tech.valery.dining.philosophers.Philosopher;
import tech.valery.dining.tables.Table;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.Supplier;

public class TableTest {

    private Table table;
    private PrintWriter printWriter;

    @BeforeEach
    void setUp() {
        try {
            printWriter = new PrintWriter("log.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void exitSetUp() {
        printWriter.close();
    }

    @Test
    void ShouldRunWithoutDeadlocks_WhenOrderingResources() {

        long eatTime = 3;
        long thinkTime = 1;

        Supplier<Philosopher> philosopherSupplier = () -> new OrderedPhilosopher(eatTime, thinkTime);

        table = new Table(5, SimpleLockChopstick::new, philosopherSupplier);
        table.setLogFile(printWriter);

        table.startSimulation();
        Common.sleep(1000);

    }

    @Test
    void ManagerTableTest() {
        long eatTime = 3;
        long thinkTime = 1;
        int participantsNumber = 5;

        Waiter waiter = new Waiter(participantsNumber);

        Supplier<Philosopher> philosopherSupplier = () -> new DependentPhilosopher(eatTime, thinkTime, waiter);

        table = new Table(5, LockChopstick::new, philosopherSupplier);
        table.setLogFile(printWriter);

        table.startSimulation();
        Common.sleep(1000);
    }
}