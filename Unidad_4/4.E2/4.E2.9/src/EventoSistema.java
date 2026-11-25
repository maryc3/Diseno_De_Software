package unidad4.E2;

/**
 * Evento estructurado. El código de severidad es numérico: 1 (informativo) a 5 (crítico).
 * El cuerpo es un texto descriptivo de lo ocurrido.
 */
public record EventoSistema(String nodoOrigen, TipoAlerta tipo, int codigoSeveridad, String cuerpo) {

    public EventoSistema {
        if (codigoSeveridad < 1 || codigoSeveridad > 5) {
            throw new IllegalArgumentException("La severidad debe estar entre 1 y 5");
        }
    }
}
