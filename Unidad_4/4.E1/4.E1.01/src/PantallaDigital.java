package unidad4.E1.Ejercicio1;

import java.util.Locale;


public class PantallaDigital implements ObservadorTemperatura {

    private final String ubicacion;

    public PantallaDigital(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public void actualizar(double temperaturaCelsius) {
        System.out.println("[Pantalla " + ubicacion + "] Temperatura actual: "
                + String.format(Locale.US, "%.1f", temperaturaCelsius) + " C");
    }
}

