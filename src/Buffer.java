import task.Task;

import java.util.LinkedList;

public class Buffer {
    private LinkedList<Task> tasks;
    private int size;

    public Buffer(int size) {
        this.tasks = new LinkedList<>();
        this.size = size;
    }

    public synchronized void write(Task task) throws InterruptedException {
        while (isFull()) {
            wait();
        }

        tasks.add(task);
        notifyAll();
    }

    public synchronized Task read() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Task task = tasks.removeFirst();
        notifyAll();
        return task;
    }

    private boolean isEmpty() {
        return tasks.isEmpty();
    }

    private boolean isFull() {
        return tasks.size() == size;
    }
}
