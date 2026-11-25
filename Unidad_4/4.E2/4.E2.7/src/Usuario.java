package unidad4.E2;

public class Usuario implements ObservadorSeguidor {

    private final String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void alSubirVideo(Video video) {
        System.out.println("   [Alerta emergente para " + nombre + "] Nuevo video de " + video.idCanal()
                + ": " + video.titulo());
    }
}
