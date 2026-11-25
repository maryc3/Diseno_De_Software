package unidad4.E2;

public class ModuloFacturacion implements ObservadorEvento<StockActualizado> {

    @Override
    public void alOcurrir(StockActualizado evento) {
        int unidades = evento.stockAnterior() - evento.stockNuevo();
        System.out.println("   [Facturacion] Se emite factura por " + unidades + " x " + evento.producto());
    }
}
