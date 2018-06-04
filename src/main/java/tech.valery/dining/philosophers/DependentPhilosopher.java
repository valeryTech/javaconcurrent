package tech.valery.dining.philosophers;

import tech.valery.dining.tables.ManagerTable;

public class DependentPhilosopher extends Philosopher {

    private ManagerTable table;

    public DependentPhilosopher(long eatTime, long thinkTime) {
        super(eatTime, thinkTime);
    }

    /**
     * method guarantees that stick can be taken taken necessary both or none
     *
     * @throws InterruptedException
     */
    @Override
    public void prepareToEat() {
        try {
            table.waitSticks(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prepareToThink() throws InterruptedException {

        table.stickHasPuttedDown(this);
    }

    public void setTable(ManagerTable table) {
        this.table = table;
    }
}
