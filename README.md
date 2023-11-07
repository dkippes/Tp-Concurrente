# Conjunto de Mandelbrot
## Descripción
### Indicaciones para ejecutar el programa
Para ejecutar el programa debe clonar el repositorio o ejecutar el siguiente comando en la terminal:
```bash
   git clone git@github.com:dkippes/Tp-Concurrente.git
```

Una vez clonado el repositorio, compile el programa dirigiendose a la carpeta
donde está posicionado el archivo Main y ejecute los comandos:
```bash
    javac Main.java
```

```bash
    java Main
```

Cuando ejecute el programa, ingrese los parámetros solicitados por consola.

### Parámetros
- ```num_threads```: Cantidad de hilos que se utilizarán para calcular el conjunto de Mandelbrot.
- ```buffer_size```: Tamaño del buffer que se utilizará para almacenar las tareas.
- ```height```: Alto de la imagen.
- ```width```: Ancho de la imagen.
- ```x_start```: Coordenada x del punto de inicio de la imagen.
- ```x_range```: Rango de la coordenada x.
- ```y_start:```: Coordenada y del punto de inicio de la imagen.
- ```y_range```: Rango de la coordenada y.
- ```num_iterations```: Cantidad de iteraciones que se realizarán para calcular el conjunto de Mandelbrot.

### Resultado
El resultado del programa será una imagen en formato .png que se guardará en la carpeta ```output```.