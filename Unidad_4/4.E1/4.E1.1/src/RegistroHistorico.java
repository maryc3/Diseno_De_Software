package unidad4.E1.Ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Otro tipo de observador: no muestra nada, guarda las lecturas recibidas. */
public class RegistroHistorico implements ObservadorTemperatura {

    private final List<Double> lecturas = new ArrayList<>();

    @Override
    public void actualizar(double temperaturaCelsius) {
        lecturas.add(temperaturaCelsius);
    }

    public void imprimirResumen() {
        StringBuilder sb = new StringBuilder("[Registro historico] Lecturas guardadas: ");
        for (int i = 0; i < lecturas.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(String.format(Locale.US, "%.1f", lecturas.get(i)));
        }
        System.out.println(sb);
    }
}
