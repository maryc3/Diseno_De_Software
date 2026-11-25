package unidad4.E1.Ejercicio2;

public class BarraInferiorControl implements ObservadorReproductor {

    @Override
    public void actualizar(EstadoReproduccion estado, String tituloCancion) {
        String simbolo = switch (estado) {
            case REPRODUCIENDO -> ">";
            case PAUSADO -> "||";
            case DETENIDO -> "[]";
        };
        System.out.println("[Barra inferior]      " + simbolo + "  " + tituloCancion);
    }
}
