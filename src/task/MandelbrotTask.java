package task;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class MandelbrotTask extends Task {


    private WritableRaster raster;
    private int height;
    private int width;
    private double xStart;
    private double yStart;
    private double xRange;
    private double yRange;
    private int numIterations;
    private int column;


    public MandelbrotTask(int height, int width, double xStart, double yStart, double xRange, double yRange, int numIterations, WritableRaster raster, int column) {
        this.height = height;
        this.width = width;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xRange = xRange;
        this.yRange = yRange;
        this.numIterations = numIterations;
        this.raster = raster;
        this.column = column;
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

        double toStartX = xStart;
        double toEndX = toStartX + Math.abs(xRange);

        double toStartY = yStart;
        double toEndY = toStartY + yRange;

        BufferedImage threadPng = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        WritableRaster rasterThread = threadPng.getRaster();
            for (int j = 0; j < width; j++) {
                double x0 = map(column, 0, width, toStartX, toEndX);  // Mapea la posición en el eje x
                double y0 = map(j, 0, height, toStartY, toEndY);  // Mapea la posición en el eje y
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
                raster.setPixel(column, j, color);
                rasterThread.setPixel(column, j, color);
            }


        File outputfile = new File("./output/thread-" + column + ".png");
        try {
            ImageIO.write(threadPng, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static double map(double value, double fromStart, double fromEnd, double toStart, double toEnd) {
        return toStart + (value - fromStart) * (toEnd - toStart) / (fromEnd - fromStart);
    }
}

