package unidad4.E1;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Sujeto observable. El stock es privado y no tiene setter: la única forma de
 * cambiarlo es vender(), que además aplica la regla del umbral.
 */
public class Producto {

    private final String nombre;
    private final int umbralMinimo;
    private int stock;
    private final CopyOnWriteArrayList<ObservadorStock> observadores = new CopyOnWriteArrayList<>();

    public Producto(String nombre, int stockInicial, int umbralMinimo) {
        this.nombre = nombre;
        this.stock = stockInicial;
        this.umbralMinimo = umbralMinimo;
    }

    public void suscribir(ObservadorStock observador) {
        observadores.addIfAbsent(observador);
    }

    public void desuscribir(ObservadorStock observador) {
        observadores.remove(observador);
    }

    public void vender(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        if (cantidad > stock) {
            throw new IllegalStateException("Stock insuficiente de " + nombre
                    + " (hay " + stock + ", se pidieron " + cantidad + ")");
        }
        stock -= cantidad;
        System.out.println("Venta de " + cantidad + " x " + nombre + " -> stock restante: " + stock);
        evaluarUmbral();
    }

    public int getStock() {
        return stock; // solo lectura
    }

    public String getNombre() {
        return nombre;
    }

    private void evaluarUmbral() {
        // Regla de negocio condicional: solo se notifica si el stock cae por debajo del umbral.
        if (stock < umbralMinimo) {
            EventoStockBajo evento = new EventoStockBajo(nombre, stock, umbralMinimo);
            for (ObservadorStock observador : observadores) {
                observador.alBajarStock(evento);
            }
        }
    }
}
