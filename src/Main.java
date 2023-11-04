import task.MandelbrotTask;
import task.PoisonPill;

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


        long initialTime = System.currentTimeMillis();

        ScannerInput scannerInput = new ScannerInput();

        WorkerCounter workerCounter = new WorkerCounter();
        Buffer buffer = new Buffer(scannerInput.getBufferSize());


        BufferedImage bi = new BufferedImage(scannerInput.getWidth(), scannerInput.getHeight(), BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = bi.getRaster();
        System.out.println("se creo el raster");

        ThreadPool threadPool = new ThreadPool(scannerInput.getNumThreads(), buffer, workerCounter);
        threadPool.start();

        System.out.println("se termino de agregar los threads");

        for (int i = 0; i < scannerInput.getHeight(); i++) {
            buffer.write(new MandelbrotTask(scannerInput.getHeight(), scannerInput.getWidth(), scannerInput.getXStart(), scannerInput.getYStart() + i, scannerInput.getXRange(), scannerInput.getYRange(), scannerInput.getNumIterations(), raster));
        }

        for (int i = 0; i < scannerInput.getNumThreads(); i++) {
            buffer.write(new PoisonPill());
        }

        System.out.println("se termino de agregar las tareas");

        workerCounter.waitUntilFinished();

        File outputfile = new File("./output/test.png");
        try {
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            // TODO Auto - generated catch block
            e.printStackTrace();
        }

        System.out.println("---- TIME ----");
        System.out.println((System.currentTimeMillis() - initialTime) / 1000.0);
    }

}


