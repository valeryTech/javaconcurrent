package tech.valery;

public class SynchronisedChopstick implements Chopstick {
    private final int id;
    private boolean free;

    public SynchronisedChopstick(int id) {
        this.id = id;
    }

    private volatile Philosopher holder;

    @Override
    public String toString() {
        return "Chopstick{" +
                "id=" + id +
                '}';
    }

    @Override
    public synchronized void get() {
        Common.sleep(200);
        free = false;
    }

    @Override
    public synchronized void put() {
        free = true;
    }

    @Override
    public boolean canGet() {
        return free;
    }

    @Override
    public void setHolder(Philosopher holder) {
        this.holder = holder;
    }

    @Override
    public Philosopher getHolder() {
        return holder;
    }
}