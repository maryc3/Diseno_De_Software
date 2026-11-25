package unidad4.E2;

/** Módulo NUEVO, agregado después: no hizo falta tocar Producto ni el bus. */
public class ModuloAlertas implements ObservadorEvento<StockActualizado> {

    private final int minimo;

    public ModuloAlertas(int minimo) {
        this.minimo = minimo;
    }

    @Override
    public void alOcurrir(StockActualizado evento) {
        if (evento.stockNuevo() <= minimo) {
            System.out.println("   [Alertas] Stock critico de " + evento.producto() + ": quedan " + evento.stockNuevo());
        }
    }
}
