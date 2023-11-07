import task.MandelbrotTask;
import task.PoisonPill;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        ScannerInput scannerInput = new ScannerInput();
        int cantThreads = scannerInput.getNumThreads();

        BufferedImage image = new BufferedImage(scannerInput.getWidth(), scannerInput.getHeight(), BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();

        Buffer buffer = new Buffer(scannerInput.getBufferSize());
        WorkerCounter workerCounter = new WorkerCounter(cantThreads);

        ThreadPool threadPool = new ThreadPool(cantThreads, buffer, workerCounter);
        long startTime = System.currentTimeMillis();
        threadPool.start();

        prepareBuffer(scannerInput, buffer, raster, cantThreads);

        workerCounter.waitForCompletion();

        generateImage(image);

        long endTime = System.currentTimeMillis();
        printTime(endTime, startTime);
    }

    private static void printTime(long endTime, long startTime) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_YELLOW = "\u001B[33m";

        System.out.println("\n-------------------------------------------");
        System.out.print(ANSI_YELLOW);
        System.out.println("        Tiempo transcurrido: " + (endTime - startTime) + " ms");
        System.out.print(ANSI_RESET);
        System.out.println("-------------------------------------------\n");
    }

    private static void prepareBuffer(ScannerInput scannerInput, Buffer buffer, WritableRaster raster, int cantThreads) {
        for (int i = 0; i < scannerInput.getHeight(); i++) {
            buffer.put(new MandelbrotTask(scannerInput.getHeight(), scannerInput.getWidth(), scannerInput.getXStart(), scannerInput.getYStart(), scannerInput.getXRange(), scannerInput.getYRange(), scannerInput.getNumIterations(), raster, i));
        }

        for (int i = 0; i < cantThreads; i++) {
            buffer.put(new PoisonPill());
        }
    }

    private static void generateImage(BufferedImage image) {
        File outputfile = new File("./output/mandelbrot.png");
        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


