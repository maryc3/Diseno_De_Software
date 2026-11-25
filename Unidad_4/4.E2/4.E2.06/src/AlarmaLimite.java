package unidad4.E2;

import java.util.Locale;

/**
 * Alarma de un solo disparo: cuando una divisa supera el límite, avisa y se
 * desuscribe del mercado DURANTE la notificación (por eso la lista es CopyOnWriteArrayList).
 */
public class AlarmaLimite implements ObservadorDivisas {

    private final String divisa;
    private final double limite;
    private final MercadoDivisas mercado;

    public AlarmaLimite(String divisa, double limite, MercadoDivisas mercado) {
        this.divisa = divisa;
        this.limite = limite;
        this.mercado = mercado;
    }

    @Override
    public void alActualizar(CotizacionDivisa c) {
        if (c.divisa().equals(divisa) && c.valor() > limite) {
            System.out.println(String.format(Locale.US,
                    "   [ALARMA] %s supero el limite de %.2f (valor %.2f). Alarma desactivada.", divisa, limite, c.valor()));
            mercado.desvincular(this);
        }
    }
}
