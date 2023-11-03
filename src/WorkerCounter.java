class WorkerCounter {
    private int workers;

    public WorkerCounter() {
        this.workers = 0;
    }

    public synchronized void increment() {
        workers++;
    }

    public synchronized void decrement() {
        workers--;
        if (workers == 0) {
            notifyAll();
        }
    }

    public synchronized void waitUntilFinished() throws InterruptedException {
        while (workers > 0) {
            wait();
        }
    }
}
