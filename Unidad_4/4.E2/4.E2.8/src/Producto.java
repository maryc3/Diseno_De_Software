package unidad4.E2;

/**
 * VERSIÓN "DESPUÉS": el producto solo gestiona su stock y publica un evento en el bus
 * que recibe por constructor. No conoce facturación, estadísticas ni alertas.
 */
public class Producto {

    private final String nombre;
    private int stock;
    private final BusEventos<StockActualizado> bus;

    public Producto(String nombre, int stockInicial, BusEventos<StockActualizado> bus) {
        this.nombre = nombre;
        this.stock = stockInicial;
        this.bus = bus;
    }

    public void vender(int cantidad) {
        if (cantidad <= 0 || cantidad > stock) {
            throw new IllegalArgumentException("Cantidad invalida: " + cantidad);
        }
        int anterior = stock;
        stock -= cantidad;
        System.out.println("(DESPUES) " + nombre + ": stock " + anterior + " -> " + stock);
        bus.publicar(new StockActualizado(nombre, anterior, stock));
    }
}
