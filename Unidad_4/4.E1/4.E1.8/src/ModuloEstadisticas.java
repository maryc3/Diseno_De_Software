package unidad4.E1.Ejercicio8;

public class ModuloEstadisticas implements ObservadorInventario {

    private int totalVendido = 0;

    @Override
    public void alCambiarStock(CambioStock cambio) {
        if (cambio.unidadesVendidas() > 0) {
            totalVendido += cambio.unidadesVendidas();
        }
        System.out.println("   [Estadisticas] Total vendido acumulado: " + totalVendido);
    }
}
