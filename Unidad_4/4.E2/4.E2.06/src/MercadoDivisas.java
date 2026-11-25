package unidad4.E2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Punto centralizado de notificación para varias divisas (dólar, euro...).
 * Recuerda el último valor de cada una para calcular la variación porcentual.
 * La lista de suscriptores admite altas/bajas mientras se está notificando.
 */
public class MercadoDivisas {

    private final Map<String, Double> ultimosValores = new HashMap<>();
    private final CopyOnWriteArrayList<ObservadorDivisas> suscriptores = new CopyOnWriteArrayList<>();

    public void suscribir(ObservadorDivisas observador) {
        suscriptores.addIfAbsent(observador);
    }

    public void desvincular(ObservadorDivisas observador) {
        suscriptores.remove(observador);
    }

    public synchronized void actualizarCotizacion(String divisa, double nuevoValor) {
        Double anterior = ultimosValores.put(divisa, nuevoValor);
        double variacion = (anterior == null || anterior == 0) ? 0.0 : (nuevoValor - anterior) / anterior * 100.0;

        CotizacionDivisa cotizacion = new CotizacionDivisa(divisa, nuevoValor, variacion);
        for (ObservadorDivisas suscriptor : suscriptores) {
            suscriptor.alActualizar(cotizacion);
        }
    }
}
