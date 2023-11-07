public class ThreadPool {

    private final Worker[] workers;

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