package unidad4.E2;

/** Metadatos del video publicado. */
public record Video(String idCanal, String titulo, int duracionSegundos) {

    public String duracionFormateada() {
        return String.format("%d:%02d", duracionSegundos / 60, duracionSegundos % 60);
    }
}
