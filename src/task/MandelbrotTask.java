package task;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Task implements Runnable {

    private int width;
    private int height;
    private int startX;
    private int endX;
    private int startY;
    private int endY;
    private int cant_iteraciones;
    WritableRaster raster;


    public Task(int width, int height, int startX, int endX, int startY, int endY, int cant_iteraciones) {
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;

        this.cant_iteraciones = cant_iteraciones;
        raster = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB).getRaster();
    }

    @Override
    public void run() {

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

        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                // Evalua el color del píxel
                int[] color = evaluateColor(i, j);

                // Asigna el color al píxel
                raster.setPixel(i, j, color);
            }
        }
    }

    // Evalua el color del píxel
    private int[] evaluateColor(int i, int j) {
        // Mapea la posición del píxel en el eje x
        double x0 = map(j, 0, width, -2.0, 2.0);

        // Mapea la posición del píxel en el eje y
        double y0 = map(i, 0, height, -2.0, 2.0);

        // Calcula el color del píxel
        int colorIndex = evaluateIteration(x0, y0);
        int[] color = {r[colorIndex], g[colorIndex], b[colorIndex]};

        return color;
    }

    // Calcula el número de iteraciones del conjunto de Mandelbrot para el punto (x0, y0)
    private int evaluateIteration(double x0, double y0) {
        double x = 0.0;
        double y = 0.0;
        int iteration = 0;

        while (x * x + y * y < 4 && iteration < cant_iteraciones) {
            double xTemp = x * x - y * y + x0;
            y = 2 * x * y + y0;
            x = xTemp;
            iteration++;
        }

        return iteration;
    }

    // Mapea el valor de la coordenada x a un valor entre [minY, maxY]
    private double map(int x, int minX, int maxX, double minY, double maxY) {
        return (x - minX) / (maxX - minX) * (maxY - minY) + minY;
    }
}
