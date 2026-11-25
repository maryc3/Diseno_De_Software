package unidad4.E1;

import java.util.Locale;

/**
 * Alerta de un solo disparo: cuando la variación supera el umbral avisa y se
 * desuscribe a sí misma EN PLENA NOTIFICACIÓN. Funciona sin errores gracias a
 * que el sujeto usa una lista CopyOnWriteArrayList.
 */
public class AlertaVolatilidad implements ObservadorCotizacion {

    private final double umbralPorcentual;
    private final ActivoFinanciero activo;

    public AlertaVolatilidad(double umbralPorcentual, ActivoFinanciero activo) {
        this.umbralPorcentual = umbralPorcentual;
        this.activo = activo;
    }

    @Override
    public void actualizar(Cotizacion c) {
        if (Math.abs(c.variacionPorcentual()) >= umbralPorcentual) {
            System.out.println(String.format(Locale.US,
                    "   [ALERTA] Volatilidad alta en %s: %+.2f%% (umbral %.1f%%). Alerta desactivada.",
                    c.simbolo(), c.variacionPorcentual(), umbralPorcentual));
            activo.desvincular(this);
        }
    }
}
