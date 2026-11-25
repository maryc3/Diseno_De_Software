package unidad4.E2;

/** Evento que viaja por el bus cada vez que cambia el stock de un producto. */
public record StockActualizado(String producto, int stockAnterior, int stockNuevo) {
}
