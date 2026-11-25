package unidad4.E1.Ejercicio8;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * VERSIÓN "DESPUÉS": el producto solo gestiona su stock y notifica a una lista
 * abstracta de oyentes. No conoce facturación, estadísticas ni ningún módulo concreto.
 */
public class Producto {

    private final String nombre;
    private int stock;
    private final CopyOnWriteArrayList<ObservadorInventario> oyentes = new CopyOnWriteArrayList<>();

    public Producto(String nombre, int stockInicial) {
        this.nombre = nombre;
        this.stock = stockInicial;
    }

    public void suscribir(ObservadorInventario oyente) {
        oyentes.addIfAbsent(oyente);
    }

    public void desuscribir(ObservadorInventario oyente) {
        oyentes.remove(oyente);
    }

    public void vender(int cantidad) {
        if (cantidad <= 0 || cantidad > stock) {
            throw new IllegalArgumentException("Cantidad invalida: " + cantidad);
        }
        cambiarStock(stock - cantidad);
    }

    public void reponer(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad invalida: " + cantidad);
        }
        cambiarStock(stock + cantidad);
    }

    private void cambiarStock(int nuevoStock) {
        CambioStock cambio = new CambioStock(nombre, stock, nuevoStock);
        this.stock = nuevoStock;
        System.out.println("(DESPUES) " + nombre + ": stock " + cambio.stockAnterior() + " -> " + nuevoStock);
        for (ObservadorInventario oyente : oyentes) {
            oyente.alCambiarStock(cambio);
        }
    }
}
