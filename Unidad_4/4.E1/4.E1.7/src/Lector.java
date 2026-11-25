package unidad4.E1;

import java.util.ArrayList;
import java.util.List;

/** Usuario del portal: recibe avisos en su bandeja. */
public class Lector implements ObservadorNoticias {

    private final String nombre;
    private final List<String> bandeja = new ArrayList<>();

    public Lector(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void recibirNoticia(Noticia noticia) {
        bandeja.add("[" + noticia.categoria() + "] " + noticia.titulo());
        System.out.println("   Aviso para " + nombre + ": " + noticia.titulo());
    }

    public void mostrarBandeja() {
        System.out.println("Bandeja de " + nombre + " (" + bandeja.size() + " avisos): " + bandeja);
    }
}
