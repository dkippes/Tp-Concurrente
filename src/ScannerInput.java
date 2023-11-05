import java.util.Scanner;

public class ScannerInput {
    private int height;
    private int width;
    private double x_start;
    private double y_start;
    private double x_range;
    private double y_range;
    private int num_iterations;
    private int num_threads;
    private int buffer_size;

    public ScannerInput() {
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Enter the value of num_threads: ");
//        num_threads = scanner.nextInt();
        num_threads = 1;

//        System.out.print("Enter the value of buffer_size: ");
//        buffer_size = scanner.nextInt();
        buffer_size = 100;

//        System.out.print("Enter the value of height: ");
//        height = scanner.nextInt();
        height = 64;

//        System.out.print("Enter the value of width: ");
//        width = scanner.nextInt();
        width = 64;

//        System.out.print("Enter the value of x_start: ");
//        x_start = scanner.nextDouble();
        x_start = -2.5;

//        System.out.print("Enter the value of x_range: ");
//        x_range = scanner.nextDouble();
        x_range = 3;

//        System.out.print("Enter the value of y_start: ");
//        y_start = scanner.nextDouble();
        y_start = 1.5;

//        System.out.print("Enter the value of y_range: ");
//        y_range = scanner.nextDouble();
        y_range = -3;

//        System.out.print("Enter the value of num_iterations: ");
//        num_iterations = scanner.nextInt();
        num_iterations = 50;

        scanner.close();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getXStart() {
        return x_start;
    }

    public double getYStart() {
        return y_start;
    }

    public double getXRange() {
        return x_range;
    }

    public double getYRange() {
        return y_range;
    }

    public int getNumIterations() {
        return num_iterations;
    }

    public int getNumThreads() {
        return num_threads;
    }

    public int getBufferSize() {
        return buffer_size;
    }
}
