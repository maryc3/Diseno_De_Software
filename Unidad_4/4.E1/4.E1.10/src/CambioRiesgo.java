package unidad4.E1;

/** Evento inmutable: qué cliente cambió y de qué nivel a qué nivel. */
public record CambioRiesgo(String idCliente, NivelRiesgo nivelAnterior, NivelRiesgo nivelNuevo) {
}
