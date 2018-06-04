package tech.valery.dining;

import org.junit.jupiter.api.Test;
import tech.valery.Common;
import tech.valery.dining.chopsticks.LockChopstick;
import tech.valery.dining.chopsticks.MisraStatefulStick;
import tech.valery.dining.philosophers.ChandyPhilosopher;
import tech.valery.dining.philosophers.DependentPhilosopher;
import tech.valery.dining.philosophers.OrderedPhilosopher;
import tech.valery.dining.philosophers.Philosopher;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TableTest {

    private Table table;

    @Test
    void PhilosopherShouldAddSticks() {
        Philosopher p = new OrderedPhilosopher();
        p.addStick(new LockChopstick());
    }

    @Test
    void ShouldRunWithoutDeadlocks_WhenOrderingResources() {

        try (PrintWriter printWriter = new PrintWriter("log.txt")) {
            table = new Table(5, LockChopstick::new, OrderedPhilosopher::new);
            table.setLogFile(printWriter);
            table.startSimulation();
            Common.sleep(1000);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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