package unidad4.E1.Ejercicio2;

/** Contrato que cumplen todas las vistas del reproductor. */
public interface ObservadorReproductor {
    void actualizar(EstadoReproduccion estado, String tituloCancion);
}
