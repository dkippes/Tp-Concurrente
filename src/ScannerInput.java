import java.util.Scanner;

public class ScannerInput {
    private int alto;
    private int ancho;
    private double x_inicial;
    private double y_inicial;
    private double x_rango;
    private double y_rango;
    private int cant_iteraciones;
    private int cant_threads;
    private double y;
    private int tamano_buffer;

    public ScannerInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el valor de alto: ");
        alto = scanner.nextInt();

        System.out.print("Ingrese el valor de ancho: ");
        ancho = scanner.nextInt();

        System.out.print("Ingrese el valor de x_inicial: ");
        x_inicial = scanner.nextDouble();

        System.out.print("Ingrese el valor de y_inicial: ");
        y_inicial = scanner.nextDouble();

        System.out.print("Ingrese el valor de x_rango: ");
        x_rango = scanner.nextDouble();

        System.out.print("Ingrese el valor de y_rango: ");
        y_rango = scanner.nextDouble();

        System.out.print("Ingrese el valor de cant_iteraciones: ");
        cant_iteraciones = scanner.nextInt();

        System.out.print("Ingrese el valor de cant_threads: ");
        cant_threads = scanner.nextInt();

        System.out.print("Ingrese el valor de tamano_buffer: ");
        tamano_buffer = scanner.nextInt();

        scanner.close();
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    public double getX_inicial() {
        return x_inicial;
    }

    public double getY_inicial() {
        return y_inicial;
    }

    public double getX_rango() {
        return x_rango;
    }

    public double getY_rango() {
        return y_rango;
    }

    public int getCant_iteraciones() {
        return cant_iteraciones;
    }

    public int getCant_threads() {
        return cant_threads;
    }

    public double getY() {
        return y;
    }

    public int getTamano_buffer() {
        return tamano_buffer;
    }
}
