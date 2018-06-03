package tech.valery.dining;

import org.junit.jupiter.api.Test;
import tech.valery.dining.chopsticks.LockChopstick;
import tech.valery.dining.chopsticks.MisraStatefulStick;
import tech.valery.dining.philosophers.ChandyPhilosopher;
import tech.valery.dining.philosophers.DependentPhilosopher;
import tech.valery.dining.philosophers.OrderedPhilosopher;
import tech.valery.dining.philosophers.Philosopher;

public class TableTest {

    private Table table;

    @Test
    void PhilosopherShouldAddSticks() {
        Philosopher p = new OrderedPhilosopher();
        p.addStick(new LockChopstick());
    }

    @Test
    void ShouldRunWithoutDeadlocks_WhenOrderingResources() {
        table = new Table(5, LockChopstick::new, OrderedPhilosopher::new);
        table.startSimulation();
    }

    @Test
    void ShouldRunWithoutDeadlocks_WhenUsingArbitrator() {

        table = new Table(5, LockChopstick::new, DependentPhilosopher::new);
        table.startSimulation();
    }

    @Test
    void ShouldRundWithoutDeadlocks_WhenUsingChandyMisraSolution() {

        table = new Table(5, MisraStatefulStick::new, ChandyPhilosopher::new);
        table.startSimulation();
    }
}