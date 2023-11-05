import task.MandelbrotTask;
import task.PoisonPill;
import task.Task;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {

    // TODO: Inicia los threads necesarios usando ThreadPool.
    // TODO: Introduce las regiones a procesar en el Buffer.
    // TODO: Cada Worker toma las tareas de a una del Buffer y genera los pixeles.
    // TODO: cant_threads == la cantidad de threads :V
    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        ScannerInput scannerInput = new ScannerInput();
        int alto = scannerInput.getHeight();
        int ancho = scannerInput.getWidth();
        double x_inicial = scannerInput.getXStart();
        double y_inicial = scannerInput.getYStart();
        double x_rango = scannerInput.getXRange();
        double y_rango = scannerInput.getYRange();
        int cant_iteraciones = scannerInput.getNumIterations();
        int cant_threads = scannerInput.getNumThreads();
        int tamano_buffer = scannerInput.getBufferSize();

        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = imagen.getRaster();

        Buffer buffer = new Buffer(tamano_buffer);
        WorkerCounter workerCounter = new WorkerCounter(cant_threads);

        ThreadPool threadPool = new ThreadPool(cant_threads, buffer, workerCounter);
        threadPool.start();

        // Divide la región a graficar en tareas y ponlas en el buffer
        double x_step = x_rango / ancho;
        double y_step = y_rango / alto;

        for (int i = 0; i < scannerInput.getHeight(); i++) {
            buffer.put(new MandelbrotTask(alto, ancho, x_inicial, y_inicial, x_rango, y_rango, cant_iteraciones, raster, i));
        }

        // Agrega las Poison Pills al buffer
        for (int i = 0; i < cant_threads; i++) {
            buffer.put(new PoisonPill());
        }

        // Espera a que todos los threads terminen
        workerCounter.waitForCompletion();

        // Guarda la imagen generada en un archivo
        File outputfile = new File("./output/test.png");
        try {
            ImageIO.write(imagen, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calcula y muestra el tiempo transcurrido
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo transcurrido: " + (endTime - startTime) + " ms");
    }

}


