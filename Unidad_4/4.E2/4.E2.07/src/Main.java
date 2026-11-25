package unidad4.E2;

public class Main {

    public static void main(String[] args) {
        PlataformaVideos plataforma = new PlataformaVideos();

        Usuario lucia = new Usuario("Lucia");
        Usuario tomas = new Usuario("Tomas");
        Usuario valen = new Usuario("Valen");

        plataforma.suscribir("canal-cocina", lucia);
        plataforma.suscribir("canal-cocina", tomas);
        plataforma.suscribir("canal-gaming", tomas);
        plataforma.suscribir("canal-gaming", valen);

        plataforma.publicar(new Video("canal-cocina", "Empanadas criollas", 754));
        plataforma.publicar(new Video("canal-gaming", "Speedrun en 40 minutos", 2400));
        plataforma.publicar(new Video("canal-musica", "Concierto en vivo", 3600));

        System.out.println("--- Tomas cancela canal-cocina ---");
        plataforma.cancelar("canal-cocina", tomas);
        plataforma.publicar(new Video("canal-cocina", "Pizza casera", 610));
    }
}
