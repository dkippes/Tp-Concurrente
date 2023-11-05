import java.util.Scanner;

public class ScannerInput {
    private int height;
    private int width;
    private double xStart;
    private double yStart;
    private double xRange;
    private double yRange;
    private int numIterations;
    private int numThreads;
    private int bufferSize;

    public ScannerInput() {
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Enter the value of num_threads: ");
//        num_threads = scanner.nextInt();
        numThreads = 20;

//        System.out.print("Enter the value of buffer_size: ");
//        buffer_size = scanner.nextInt();
        bufferSize = 1000;

//        System.out.print("Enter the value of height: ");
//        height = scanner.nextInt();
        height = 1024;

//        System.out.print("Enter the value of width: ");
//        width = scanner.nextInt();
        width = 1024;

//        System.out.print("Enter the value of x_start: ");
//        x_start = scanner.nextDouble();
        xStart = -0.55;

//        System.out.print("Enter the value of x_range: ");
//        x_range = scanner.nextDouble();
        xRange = 0.0002;

//        System.out.print("Enter the value of y_start: ");
//        y_start = scanner.nextDouble();
        yStart = -0.55;

//        System.out.print("Enter the value of y_range: ");
//        y_range = scanner.nextDouble();
        yRange = 0.0002;

//        System.out.print("Enter the value of num_iterations: ");
//        num_iterations = scanner.nextInt();
        numIterations = 100;

        scanner.close();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getXStart() {
        return xStart;
    }

    public double getYStart() {
        return yStart;
    }

    public double getXRange() {
        return xRange;
    }

    public double getYRange() {
        return yRange;
    }

    public int getNumIterations() {
        return numIterations;
    }

    public int getNumThreads() {
        return numThreads;
    }

    public int getBufferSize() {
        return bufferSize;
    }
}
