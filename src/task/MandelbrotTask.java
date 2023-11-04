package task;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class MandelbrotTask extends Task {

    private double width;
    private double height;
    private double startX;
    private double endX;
    private double startY;
    private double endY;
    private int cant_iteraciones;
    WritableRaster raster;


    public MandelbrotTask(double width, double height, double startX, double endX, double startY, double endY, int cant_iteraciones, WritableRaster raster) {
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;

        this.cant_iteraciones = cant_iteraciones;
        this.raster = raster;
    }

    @Override
    public void run() {

        int iteraciones = this.cant_iteraciones; // Puedes ajustar esto según tus necesidades
        int[] r = new int[iteraciones+1];
        int[] g = new int[iteraciones+1];
        int[] b = new int[iteraciones+1];
        r[iteraciones] = 0;
        g[iteraciones] = 0;
        b[iteraciones] = 0;
        for (int i = 0; i < iteraciones; i++) {
            int argb = Color.HSBtoRGB((float) i / (float) cant_iteraciones, 1, 1);
            r[i] = (argb >> 16) & 255;
            g[i] = (argb >> 8) & 255;
            b[i] = argb & 255;
        }

        for (double i = startY; i < endY; i++) {
            for (double j = startX; j < endX; j++) {
                // Evalúa el color del píxel
                int[] color = evaluateColor(i, j, r, g, b);

                // Asigna el color al píxel
                raster.setPixel((int) i, (int) j, color);
            }
        }

    }

    // Evalua el color del píxel
    private int[] evaluateColor(double i, double j, int[] r, int[] g, int[] b) {
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
    private double map(double x, double minX, double maxX, double minY, double maxY) {
        return (x - minX) / (maxX - minX) * (maxY - minY) + minY;
    }
}
