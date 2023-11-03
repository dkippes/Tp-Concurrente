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

        ScannerInput scannerInput = new ScannerInput();

        ThreadPool threadPool = new ThreadPool(scannerInput.getCant_threads());
        Buffer buffer = new Buffer(scannerInput.getTamano_buffer());
        Worker[] workers = new Worker[scannerInput.getCant_threads()];
        for (int i = 0; i < scannerInput.getCant_threads(); i++) {
            workers[i] = new Worker(buffer);
            workers[i].start();
        }






        long initialTime = System.currentTimeMillis();


        BufferedImage bi = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = bi.getRaster();

        // Definir la paleta de colores
        int cant_iteraciones = 20; // Puedes ajustar esto según tus necesidades
        int[] r = new int[cant_iteraciones+1];
        int[] g = new int[cant_iteraciones+1];
        int[] b = new int[cant_iteraciones+1];
        r[cant_iteraciones] = 0;
        g[cant_iteraciones] = 0;
        b[cant_iteraciones] = 0;
        for (int i = 0; i < cant_iteraciones; i++) {
            int argb = Color.HSBtoRGB((float) i / (float) cant_iteraciones, 1, 1);
            r[i] = (argb >> 16) & 255;
            g[i] = (argb >> 8) & 255;
            b[i] = argb & 255;
        }

        // Itera a través de tus píxeles y asigna colores basados en la paleta
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                double x0 = map(i, 0, 256, -2.0, 2.0);  // Mapea la posición en el eje x
                double y0 = map(j, 0, 256, -2.0, 2.0);  // Mapea la posición en el eje y
                double x = 0.0;
                double y = 0.0;
                int iteration = 0;

                while (x * x + y * y < 4 && iteration < cant_iteraciones) {
                    double xTemp = x * x - y * y + x0;
                    y = 2 * x * y + y0;
                    x = xTemp;
                    iteration++;
                }

                int colorIndex = iteration;

                // Asigna el color basado en el valor de colorIndex
                int[] color = {r[colorIndex], g[colorIndex], b[colorIndex]};
                raster.setPixel(i, j, color);
            }
        }

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

    public static double map(double value, double fromStart, double fromEnd, double toStart, double toEnd) {
        return toStart + (value - fromStart) * (toEnd - toStart) / (fromEnd - fromStart);
    }
}



//        int x = 30;
//        int y = 30;
//        int[] color = {255, 255, 255};  // Rojo en formato RGB
//
//        // Establece los valores de píxeles en las coordenadas deseadas
//        raster.setPixel(x, y, color);
