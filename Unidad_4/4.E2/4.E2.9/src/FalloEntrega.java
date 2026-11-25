package unidad4.E2;

/** Constancia de que un observador falló al recibir un evento (cola de "cartas muertas"). */
public record FalloEntrega(EventoSistema evento, String observador, String error) {
}
