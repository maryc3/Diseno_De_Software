package unidad4.E2;

import java.util.Locale;

/** Gráfico de tendencia: indica si la divisa sube, baja o se mantiene. */
public class PanelTendencia implements ObservadorDivisas {

    @Override
    public void alActualizar(CotizacionDivisa c) {
        String flecha = c.variacionPorcentual() > 0 ? "SUBE" : (c.variacionPorcentual() < 0 ? "BAJA" : "IGUAL");
        System.out.println(String.format(Locale.US, "   [Tendencia] %s %s %+.2f%%", c.divisa(), flecha, c.variacionPorcentual()));
    }
}
