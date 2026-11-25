package unidad4.E1;

public class DepartamentoCompras implements ObservadorStock {

    @Override
    public void alBajarStock(EventoStockBajo evento) {
        // Decide según los datos: repone hasta el doble del umbral.
        int aPedir = evento.umbralMinimo() * 2 - evento.stockActual();
        System.out.println("   [Compras] Orden de reabastecimiento: " + aPedir + " unidades de " + evento.producto());
    }
}
