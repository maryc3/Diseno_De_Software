package unidad4.E2;

/** Estructura de datos que reciben los paneles: valor de la divisa y su variación respecto del ciclo anterior. */
public record CotizacionDivisa(String divisa, double valor, double variacionPorcentual) {
}
