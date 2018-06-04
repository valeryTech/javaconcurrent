package tech.valery.dining.tables;

import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.philosophers.Philosopher;

import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Table {

    private final int participantsNumber;

    private final List<Chopstick> sticks;
    protected final List<Philosopher> philosophers;

    public Table(int participantsNumber, Supplier<Chopstick> stickSupplier,
                 Supplier<Philosopher> philosopherSupplier) {

        this.participantsNumber = participantsNumber;

        this.sticks = Stream.generate(stickSupplier)
                .limit(participantsNumber)
                .collect(Collectors.toList());

        this.philosophers = Stream.generate(philosopherSupplier)
                .limit(participantsNumber)
                .collect(Collectors.toList());

        //set sticks to philosophers
        for (int seat = 0; seat < participantsNumber - 1; seat++) {
            philosophers.get(seat).addStick(sticks.get(seat));
            philosophers.get(seat).addStick(sticks.get((seat + 1) % participantsNumber));
        }

        philosophers.get(participantsNumber - 1).addStick(sticks.get(0));
        philosophers.get(participantsNumber - 1).addStick(sticks.get(participantsNumber - 1));
    }

    public void startSimulation() {
        ExecutorService service = Executors.newFixedThreadPool(participantsNumber);
        philosophers.forEach((philosopher) -> service.submit(philosopher::run));
    }

    public void setLogFile(PrintWriter printWriter) {
        philosophers.forEach(philosopher -> philosopher.setWriter(printWriter));
    }
}