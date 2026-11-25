package unidad4.E1;

/** Datos que reciben los observadores para decidir qué hacer. */
public record EventoStockBajo(String producto, int stockActual, int umbralMinimo) {
}
