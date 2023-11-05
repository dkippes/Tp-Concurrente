public class WorkerCounter {
    private int remainingWorkers;

    public WorkerCounter(int totalWorkers) {
        this.remainingWorkers = totalWorkers;
    }

    public synchronized void workerFinished() {
        remainingWorkers--;
        if (remainingWorkers == 0) {
            notify();
        }
    }

    public synchronized void waitForCompletion() {
        while (remainingWorkers > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
