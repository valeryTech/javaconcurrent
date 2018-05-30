package tech.valery;

import net.jcip.annotations.GuardedBy;

public class BoundedBuffer<V> {

    @GuardedBy("this")
    private int count;

    @GuardedBy("this")
    private V[] data;

    @GuardedBy("this")
    private int tail;

    @GuardedBy("this")
    private int head;

    public BoundedBuffer(int capacity) {
        data = (V[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public synchronized void doPut(V v) {
        try {
            this.wait(10, 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data[tail] = v;
        if (++tail == data.length)
            tail = 0;
        ++count;
    }

    public synchronized V doTake() {
        V v = data[head];

        if (++head == data.length)
            head = 0;
        --count;

        return v;
    }

    public boolean isFull() {
        return count == data.length;
    }
}
