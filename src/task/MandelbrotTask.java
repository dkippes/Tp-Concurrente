package task;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Random;

public class MandelbrotTask extends Task {

    private BufferedImage imagen;
    private int x;
    private int y;
    private double cx;
    private double cy;
    private int maxIter;

    public MandelbrotTask(BufferedImage imagen, int x, int y, double cx, double cy, int maxIter) {
        this.imagen = imagen;
        this.x = x;
        this.y = y;
        this.cx = cx;
        this.cy = cy;
        this.maxIter = maxIter;
    }

    @Override
    public void run() {
        int iter = 0;
        double zx = 0;
        double zy = 0;

        while (zx * zx + zy * zy < 4 && iter < maxIter) {
            double temp = zx * zx - zy * zy + cx;
            zy = 2 * zx * zy + cy;
            zx = temp;
            iter++;
        }

        int color = getColor(iter, maxIter);
        imagen.setRGB(x, y, color);
    }

    private int getColor(int iter, int maxIter) {
        if (iter >= maxIter) {
            return 0;  // Color negro para puntos que pertenecen al conjunto
        } else {
            // Calcular un color basado en el número de iteraciones
            int r = (iter * 255) / maxIter;  // Componente rojo
            int g = 255 - (iter * 255) / maxIter;  // Componente verde (inverso de r)
            int b = 0;  // Sin componente azul
            return (r << 16) | (g << 8) | b;
        }
    }
}
