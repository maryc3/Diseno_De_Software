package unidad4.E1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Guarda la serie de precios para "dibujar" la línea. */
public class PanelGrafico implements ObservadorCotizacion {

    private final List<Double> serie = new ArrayList<>();

    @Override
    public void actualizar(Cotizacion c) {
        serie.add(c.precio());
        StringBuilder linea = new StringBuilder();
        for (double p : serie) {
            if (linea.length() > 0) linea.append(" -> ");
            linea.append(String.format(Locale.US, "%.2f", p));
        }
        System.out.println("   [Grafico] " + c.simbolo() + ": " + linea);
    }
}
