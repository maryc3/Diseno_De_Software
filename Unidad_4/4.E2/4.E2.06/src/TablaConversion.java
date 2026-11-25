package unidad4.E2;

import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

/** Tabla de conversión: conserva el último valor de cada divisa (en pesos) y lo muestra completo. */
public class TablaConversion implements ObservadorDivisas {

    private final Map<String, Double> valores = new TreeMap<>();

    @Override
    public void alActualizar(CotizacionDivisa c) {
        valores.put(c.divisa(), c.valor());
        StringJoiner fila = new StringJoiner(" | ", "   [Tabla] ", "");
        valores.forEach((divisa, valor) ->
                fila.add(String.format(Locale.US, "1 %s = %.2f ARS", divisa, valor)));
        System.out.println(fila);
    }
}
