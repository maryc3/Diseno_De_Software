package unidad4.E1.Ejercicio8;

public record CambioStock(String producto, int stockAnterior, int stockNuevo) {

    /** Positivo si se vendió, negativo si se repuso. */
    public int unidadesVendidas() {
        return stockAnterior - stockNuevo;
    }
}
