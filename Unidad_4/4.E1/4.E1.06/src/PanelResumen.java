package unidad4.E1;

import java.util.Locale;

/** Tabla resumen: precio actual, mínimo y máximo vistos. Solo guarda números primitivos. */
public class PanelResumen implements ObservadorCotizacion {

    private double minimo = Double.MAX_VALUE;
    private double maximo = Double.MIN_VALUE;

    @Override
    public void actualizar(Cotizacion c) {
        minimo = Math.min(minimo, c.precio());
        maximo = Math.max(maximo, c.precio());
        System.out.println(String.format(Locale.US,
                "   [Resumen] %s | actual %.2f | var %+.2f%% | min %.2f | max %.2f",
                c.simbolo(), c.precio(), c.variacionPorcentual(), minimo, maximo));
    }
}
