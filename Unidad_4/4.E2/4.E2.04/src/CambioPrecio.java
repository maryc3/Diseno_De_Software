package unidad4.E2;

public record CambioPrecio(String producto, double precioAnterior, double precioNuevo) {

    /** Porcentaje de rebaja aplicado respecto del precio anterior. */
    public double porcentajeRebaja() {
        return (precioAnterior - precioNuevo) / precioAnterior * 100.0;
    }
}
