import task.PoisonPill;
import task.Task;

public class Worker extends Thread {
    private final Buffer<Task> buffer;

    public Worker(Buffer<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = buffer.read();
                if (task instanceof PoisonPill) {
                    break;
                } else {
                    task.run();
                }
            } catch (InterruptedException e) {
                // Maneja la excepción como sea necesario
                // Por ejemplo, puedes imprimir un mensaje o realizar alguna otra acción
                System.out.println("Worker terminado debido a interrupción");
            }
        }
    }
}
