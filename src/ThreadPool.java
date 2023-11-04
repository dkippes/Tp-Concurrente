import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    private Worker[] workers;

    public ThreadPool(int numThreads, Buffer buffer, WorkerCounter workerCounter) {
        workers = new Worker[numThreads];
        for (int i = 0; i < numThreads; i++) {
            workers[i] = new Worker(buffer, workerCounter);
        }
    }

    public void start() {
        for (Worker worker : workers) {
            worker.start();
        }
    }

}