package unidad4.E2;

public class ModuloEstadisticas implements ObservadorEvento<StockActualizado> {

    private int totalVendido = 0;

    @Override
    public void alOcurrir(StockActualizado evento) {
        totalVendido += evento.stockAnterior() - evento.stockNuevo();
        System.out.println("   [Estadisticas] Unidades vendidas en total: " + totalVendido);
    }
}
