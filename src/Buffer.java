import java.util.LinkedList;

public class Buffer<T> {
    private LinkedList<T> data;
    private int size;

    public Buffer(int size) {
        this.data = new LinkedList<>();
        this.size = size;
    }

    public synchronized void write(T o) throws InterruptedException {
        while (isFull()) {
            wait();
        }

        data.add(o);
        notifyAll();
    }

    public synchronized T read() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }

        T o = data.removeFirst();
        notifyAll();
        return o;
    }

    private boolean isEmpty() {
        return data.isEmpty();
    }

    private boolean isFull() {
        return data.size() == size;
    }
}
