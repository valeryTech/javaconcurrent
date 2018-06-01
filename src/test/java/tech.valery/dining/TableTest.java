package tech.valery.dining;

import org.junit.jupiter.api.Test;
import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.chopsticks.LockChopstick;
import tech.valery.dining.chopsticks.MisraStatefulStick;
import tech.valery.dining.philosophers.ChandyPhilosopher;
import tech.valery.dining.philosophers.DependentPhilosopher;
import tech.valery.dining.philosophers.OrderedPhilosopher;
import tech.valery.dining.philosophers.Philosopher;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class TableTest {

    private Supplier<Chopstick> stickSupplier;
    private BiFunction<Integer, Table, Philosopher> philosopherFactory;
    private Table table;
    private int problemSize;

    @Test
    void ShouldRunWithoutDeadlocks_WhenOrderingResources() {
        problemSize = 5;

        stickSupplier = LockChopstick::new;
        philosopherFactory =
                (Integer seat, Table table) -> new OrderedPhilosopher(seat, table);

        table = new Table(problemSize, stickSupplier, philosopherFactory);

        table.startSimulation();
    }

    @Test
    void ShouldRunWithoutDeadlocks_WhenUsingArbitrator() {
        problemSize = 5;

        stickSupplier = LockChopstick::new;
        philosopherFactory =
                (Integer seat, Table table) -> new DependentPhilosopher(seat, table);

        table = new Table(problemSize, stickSupplier, philosopherFactory);

        table.startSimulation();
    }

    @Test
    void ShouldRundWithoutDeadlocks_WhenUsingChandyMisraSolution() {
        problemSize = 5;

        stickSupplier = MisraStatefulStick::new;
        philosopherFactory = (Integer seat, Table table) -> new ChandyPhilosopher(seat, table);

        table = new Table(problemSize, stickSupplier, philosopherFactory);

        table.startSimulation();
    }
}