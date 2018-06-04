package tech.valery.dining.tables;

import tech.valery.dining.chopsticks.Chopstick;
import tech.valery.dining.philosophers.Philosopher;

import java.util.function.Supplier;

public class OrderedTable extends Table{
    public OrderedTable(int participantsNumber, Supplier<Chopstick> stickSupplier,
                        Supplier<Philosopher> philosopherSupplier) {
        super(participantsNumber, stickSupplier, philosopherSupplier);
    }


}
