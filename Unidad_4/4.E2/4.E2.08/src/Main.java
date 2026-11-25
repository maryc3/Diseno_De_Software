package unidad4.E2;

import unidad4.E2.antes.ProductoAcoplado;

public class Main {

    public static void main(String[] args) {
        System.out.println("=========== ANTES (acoplado) ===========");
        new ProductoAcoplado("Monitor", 10).vender(2);

        System.out.println();
        System.out.println("=========== DESPUES (bus de eventos) ===========");
        // Este main es la "raiz de composicion": el unico lugar que arma las piezas concretas.
        BusEventos<StockActualizado> bus = new BusEventos<>();
        bus.suscribir(new ModuloFacturacion());
        bus.suscribir(new ModuloEstadisticas());

        Producto monitor = new Producto("Monitor", 10, bus);
        monitor.vender(2);
        monitor.vender(3);

        System.out.println("--- Se suma un modulo nuevo SIN modificar Producto ---");
        bus.suscribir(new ModuloAlertas(3));
        monitor.vender(3);
    }
}
