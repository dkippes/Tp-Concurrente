package task;

import java.awt.*;
import java.awt.image.WritableRaster;

public class MandelbrotTask extends Task {


    private WritableRaster raster;
    private int height;
    private int width;
    private double xStart;
    private double yStart;
    private double xRange;
    private double yRange;
    private int numIterations;
    private int row;


    public MandelbrotTask(int height, int width, double xStart, double yStart, double xRange, double yRange, int numIterations, WritableRaster raster, int row) {
        this.height = height;
        this.width = width;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xRange = xRange;
        this.yRange = yRange;
        this.numIterations = numIterations;
        this.raster = raster;
        this.row = row;
    }

    @Override
    public void run() {

        int[] r = new int[numIterations + 1];
        int[] g = new int[numIterations + 1];
        int[] b = new int[numIterations + 1];
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
        for (int j = 0; j < width; j++) {
            double x0 = map(j, 0, width, toStartX, toEndX);
            double y0 = map(row, 0, height, toEndY, toStartY);
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

            int[] color = {r[colorIndex], g[colorIndex], b[colorIndex]};
            raster.setPixel(j, row, color);
        }
    }

    public static double map(double value, double fromStart, double fromEnd, double toStart, double toEnd) {
        return toStart + (value - fromStart) * (toEnd - toStart) / (fromEnd - fromStart);
    }
}

