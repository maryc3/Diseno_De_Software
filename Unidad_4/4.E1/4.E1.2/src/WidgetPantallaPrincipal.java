package unidad4.E1.Ejercicio2;

public class WidgetPantallaPrincipal implements ObservadorReproductor {

    @Override
    public void actualizar(EstadoReproduccion estado, String tituloCancion) {
        if (estado == EstadoReproduccion.REPRODUCIENDO) {
            System.out.println("[Widget principal]    Ahora suena: " + tituloCancion);
        } else {
            System.out.println("[Widget principal]    Reproduccion " + estado.name().toLowerCase());
        }
    }
}
