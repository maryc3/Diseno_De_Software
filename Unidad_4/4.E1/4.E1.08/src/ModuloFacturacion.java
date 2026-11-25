package unidad4.E1.Ejercicio8;

public class ModuloFacturacion implements ObservadorInventario {

    @Override
    public void alCambiarStock(CambioStock cambio) {
        int unidades = cambio.unidadesVendidas();
        if (unidades > 0) {
            System.out.println("   [Facturacion] Se factura la venta de " + unidades + " x " + cambio.producto());
        } else {
            System.out.println("   [Facturacion] Ingreso de mercaderia: " + (-unidades) + " x " + cambio.producto());
        }
    }
}
