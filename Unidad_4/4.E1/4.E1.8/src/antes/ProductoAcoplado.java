package unidad4.E1.Ejercicio8antes;

/**
 * VERSIÓN "ANTES" (código heredado, MAL diseñado): el producto crea y llama
 * directamente a los módulos concretos. Agregar o cambiar un módulo obliga a
 * modificar esta clase.
 */
public class ProductoAcoplado {

    private final String nombre;
    private int stock;

    // Dependencias directas y rígidas
    private final ModuloFacturacionAcoplado facturacion = new ModuloFacturacionAcoplado();
    private final ModuloEstadisticasAcoplado estadisticas = new ModuloEstadisticasAcoplado();

    public ProductoAcoplado(String nombre, int stock) {
        this.nombre = nombre;
        this.stock = stock;
    }

    public void vender(int cantidad) {
        stock -= cantidad;
        System.out.println("(ANTES) Venta de " + cantidad + " x " + nombre + ", stock: " + stock);
        facturacion.facturarMovimiento(nombre, cantidad);   // llamada fija
        estadisticas.registrarMovimiento(nombre, cantidad); // llamada fija
    }
}
