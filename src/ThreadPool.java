import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    private Buffer buffer;
    private WorkerCounter workerCounter;

    private int cantThreads;

    public ThreadPool(int cantThreads, Buffer buffer, WorkerCounter workerCounter) {
        this.buffer = buffer;
        this.workerCounter = workerCounter;
        this.cantThreads = cantThreads;
    }

    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(cantThreads);
        for (int i = 0; i < cantThreads; i++) {
            executorService.execute(new Worker(buffer, workerCounter));
            workerCounter.increment();
        }
    }

}