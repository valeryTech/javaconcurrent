package tech.valery;

public class BoundedBuffer<V> {

    private int count;
    private V[] data;
    private int tail;
    private int head;

    public BoundedBuffer(int capacity) {
        data = (V[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public synchronized void doPut(V v) {
        data[tail] = v;
        if(++tail == data.length)
            tail = 0;
        ++count;
    }

    public synchronized V doTake(){
        V v = data[head];

        if(++head == data.length)
            head = 0;
        --count;

        return v;
    }

    public boolean isFull() {
        return count == data.length;
    }
}
