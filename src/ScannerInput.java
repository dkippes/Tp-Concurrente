import java.util.Scanner;

public class ScannerInput {
    private final int height;
    private final int width;
    private final double xStart;
    private final double yStart;
    private final double xRange;
    private final double yRange;
    private final int numIterations;
    private final int numThreads;
    private final int bufferSize;

    public ScannerInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of num_threads: ");
        numThreads = scanner.nextInt();

        System.out.print("Enter the value of buffer_size: ");
        bufferSize = scanner.nextInt();

        System.out.print("Enter the value of height: ");
        height = scanner.nextInt();

        System.out.print("Enter the value of width: ");
        width = scanner.nextInt();

        System.out.print("Enter the value of x_start: ");
        xStart = scanner.nextDouble();

        System.out.print("Enter the value of x_range: ");
        xRange = scanner.nextDouble();

        System.out.print("Enter the value of y_start: ");
        yStart = scanner.nextDouble();

        System.out.print("Enter the value of y_range: ");
        yRange = scanner.nextDouble();

        System.out.print("Enter the value of num_iterations: ");
        numIterations = scanner.nextInt();

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
