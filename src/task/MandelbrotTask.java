package task;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Random;

public class MandelbrotTask extends Task {


    private WritableRaster raster;
    private int height;
    private int width;
    private double xStart;
    private double yStart;
    private double xRange;
    private double yRange;
    private int numIterations;


    public MandelbrotTask(int height, int width, double xStart, double yStart, double xRange, double yRange, int numIterations, WritableRaster raster) {
        this.height = height;
        this.width = width;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xRange = xRange;
        this.yRange = yRange;
        this.numIterations = numIterations;
        this.raster = raster;
    }

    @Override
    public void run() {

        int[] r = new int[numIterations+1];
        int[] g = new int[numIterations+1];
        int[] b = new int[numIterations+1];
        r[numIterations] = 0;
        g[numIterations] = 0;
        b[numIterations] = 0;
        for (int i = 0; i < numIterations; i++) {
            int argb = Color.HSBtoRGB((float) i / (float) numIterations, 1, 1);
            r[i] = (argb >> 16) & 255;
            g[i] = (argb >> 8) & 255;
            b[i] = argb & 255;
        }

        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                double x0 = map(i, 0, 256, -2.0, 2.0);  // Mapea la posición en el eje x
                double y0 = map(j, 0, 256, -2.0, 2.0);  // Mapea la posición en el eje y
                double x = 0.0;
                double y = 0.0;
                int iteration = 0;

                while (x * x + y * y < 4 && iteration < numIterations) {
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
    }
    public static double map(double value, double fromStart, double fromEnd, double toStart, double toEnd) {
        return toStart + (value - fromStart) * (toEnd - toStart) / (fromEnd - fromStart);
    }

}

