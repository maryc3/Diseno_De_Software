package unidad4.E1;

public class SistemaMarketing implements ObservadorStock {

    @Override
    public void alBajarStock(EventoStockBajo evento) {
        if (evento.stockActual() == 0) {
            System.out.println("   [Marketing] " + evento.producto() + " agotado: se pausan las campanas");
        } else {
            System.out.println("   [Marketing] Campana de liquidacion 'ultimas " + evento.stockActual()
                    + " unidades' de " + evento.producto());
        }
    }
}
