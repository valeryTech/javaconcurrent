package tech.valery.dining.philosophers;

import tech.valery.dining.Table;

public class ChandyPhilosopher extends Philosopher{

    public ChandyPhilosopher(int seat, Table table) {
        super(seat, table);
    }

    @Override
    public void prepareToEat() throws InterruptedException {

    }

    @Override
    public void prepareToThink(){

    }
}
