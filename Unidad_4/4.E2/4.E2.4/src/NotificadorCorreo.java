package unidad4.E2;

import java.util.Locale;

/** Correo electrónico simulado. */
public class NotificadorCorreo implements ObservadorPrecio {

    private final String direccion;

    public NotificadorCorreo(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public void alBajarPrecio(CambioPrecio cambio) {
        System.out.println(String.format(Locale.US,
                "   [Correo a %s] '%s' bajo de %.2f a %.2f (%.1f%% de rebaja)",
                direccion, cambio.producto(), cambio.precioAnterior(), cambio.precioNuevo(), cambio.porcentajeRebaja()));
    }
}
