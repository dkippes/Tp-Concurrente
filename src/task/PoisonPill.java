package task;

public class PoisonPill extends Task {
    //    que hace que el Worker que la tome
//    termine su ejecuci ́on (puede ser por medio de una excepci ́on, como en la pr ́actica).    @Override
    @Override
    public void run() {
        // Puedes lanzar una excepción específica, como InterruptedException,
        // para señalar que el Worker debe terminar su ejecución.
        try {
            throw new InterruptedException("PoisonPill received");
        } catch (InterruptedException e) {
            // Maneja la excepción como sea necesario
            // Por ejemplo, puedes imprimir un mensaje o realizar alguna otra acción
            System.out.println("Worker terminado debido a PoisonPill");
        }
    }
}