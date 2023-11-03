import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    private ExecutorService executorService;

    public ThreadPool(int numWorkers) {
        this.executorService = Executors.newFixedThreadPool(numWorkers);
    }

    public void execute(Runnable task) {
        this.executorService.execute(task);
    }

    public void shutdown() {
        this.executorService.shutdown();
    }
}