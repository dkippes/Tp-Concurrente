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

        addToBuffer(scannerInput, buffer, raster, cantThreads);

        workerCounter.waitForCompletion();

        generateImage(image);

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo transcurrido: " + (endTime - startTime) + " ms");
    }

    private static void addToBuffer(ScannerInput scannerInput, Buffer buffer, WritableRaster raster, int cantThreads) {
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


