import task.Task;

import java.util.LinkedList;

public class Buffer {
    private LinkedList<Task> tasks;
    private int capacity;

    public Buffer(int capacity) {
        this.tasks = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void put(Task task) {
        while (tasks.size() >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        tasks.add(task);
        notifyAll();
    }

    public synchronized Task get() {
        while (tasks.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Task task = tasks.removeFirst();
        notifyAll();
        return task;
    }
}
