class Buffer {
    // actua como una cola FIFO concurrente de capacidad acotada.
    // bloquea a un lector intentando sacar un elemento cuando est ́a vac ́ıa y bloquea a
    //un productor intentando agregar un elemento cuando est ́a llena.


    //    bloquea a un lector intentando sacar un elemento cuando est ́a vac ́ıa y bloquea a
    //    un productor intentando agregar un elemento cuando est ́a llena.

    private int quantity = 0;
    private Object[] data = new Object[quantity + 1];
    private int begin = 0, end = 0;

    Buffer(int quantity) {
        this.quantity = quantity;
    }

    synchronized void write(Object o) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        data[begin] = o;
        begin = next(begin);
        notifyAll();
    }

    synchronized Object read() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        Object result = data[end];
        end = next(end);
        notifyAll();
        return result;
    }

    private boolean isEmpty() {
        return begin == end;
    }

    private boolean isFull() {
        return next(begin) == end;
    }

    private int next(int i) {
        return (i + 1) % (quantity + 1);
    }


}
