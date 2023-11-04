import exceptions.PoisonPillException;
import task.PoisonPill;
import task.Task;

public class Worker extends Thread {
    private final Buffer buffer;
    private final WorkerCounter workerCounter;

    public Worker(Buffer buffer, WorkerCounter workerCounter) {
        this.buffer = buffer;
        this.workerCounter = workerCounter;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = buffer.read();
                task.run();
            }  catch (PoisonPillException e) {
                workerCounter.decrement();
                break;
            }
        }
    }
}
