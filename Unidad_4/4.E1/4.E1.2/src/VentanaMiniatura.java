package unidad4.E1.Ejercicio2;

public class VentanaMiniatura implements ObservadorReproductor {

    @Override
    public void actualizar(EstadoReproduccion estado, String tituloCancion) {
        System.out.println("[Miniatura flotante]  Cancion: " + tituloCancion + " (" + estado + ")");
    }
}
