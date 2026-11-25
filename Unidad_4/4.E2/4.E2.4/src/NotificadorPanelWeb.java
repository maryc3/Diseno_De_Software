package unidad4.E2;

import java.util.Locale;

/** Aviso en el panel web. Procesa los datos según el porcentaje de rebaja: destaca las grandes ofertas. */
public class NotificadorPanelWeb implements ObservadorPrecio {

    private static final double UMBRAL_OFERTA_DESTACADA = 20.0;

    @Override
    public void alBajarPrecio(CambioPrecio cambio) {
        String etiqueta = cambio.porcentajeRebaja() >= UMBRAL_OFERTA_DESTACADA ? "OFERTA DESTACADA" : "Aviso de rebaja";
        System.out.println(String.format(Locale.US, "   [Panel web] %s: %s -%.1f%%",
                etiqueta, cambio.producto(), cambio.porcentajeRebaja()));
    }
}
