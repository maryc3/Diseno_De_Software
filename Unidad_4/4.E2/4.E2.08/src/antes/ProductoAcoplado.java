package unidad4.E2.antes;

/**
 * VERSIÓN "ANTES" (heredada): el producto instancia y llama directamente a los módulos.
 * Cambiar un receptor, o sumar uno nuevo, obliga a editar esta clase.
 */
public class ProductoAcoplado {

    private final String nombre;
    private int stock;

    private final FacturacionAcoplada facturacion = new FacturacionAcoplada();
    private final EstadisticasAcopladas estadisticas = new EstadisticasAcopladas();

    public ProductoAcoplado(String nombre, int stock) {
        this.nombre = nombre;
        this.stock = stock;
    }

    public void vender(int cantidad) {
        stock -= cantidad;
        System.out.println("(ANTES) " + nombre + ": vendidas " + cantidad + ", stock " + stock);
        facturacion.facturar(nombre, cantidad);
        estadisticas.contabilizar(nombre, cantidad);
    }
}
