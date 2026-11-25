package unidad4.E1;

/** Evento estructurado: quién lo emitió, de qué tipo es, su severidad y un cuerpo descriptivo. */
public record EventoSistema(String nodoOrigen, TipoAlerta tipo, Severidad severidad, String cuerpo) {
}
