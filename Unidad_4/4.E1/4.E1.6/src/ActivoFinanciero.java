package unidad4.E1;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Sujeto observable. Calcula la variación porcentual y envía UN solo objeto
 * Cotizacion a todos los paneles (no se copian datos por cada panel).
 */
public class ActivoFinanciero {

    private final String simbolo;
    private double precio;
    // Lista segura ante altas/bajas mientras se está notificando.
    private final CopyOnWriteArrayList<ObservadorCotizacion> suscriptores = new CopyOnWriteArrayList<>();

    public ActivoFinanciero(String simbolo, double precioInicial) {
        this.simbolo = simbolo;
        this.precio = precioInicial;
    }

    public void suscribir(ObservadorCotizacion observador) {
        suscriptores.addIfAbsent(observador);
    }

    public void desvincular(ObservadorCotizacion observador) {
        suscriptores.remove(observador);
    }

    public synchronized void actualizarPrecio(double nuevoPrecio) {
        double variacion = precio == 0 ? 0 : (nuevoPrecio - precio) / precio * 100.0;
        this.precio = nuevoPrecio;
        Cotizacion cotizacion = new Cotizacion(simbolo, nuevoPrecio, variacion);
        for (ObservadorCotizacion suscriptor : suscriptores) {
            suscriptor.actualizar(cotizacion);
        }
    }

    public String getSimbolo() {
        return simbolo;
    }
}
