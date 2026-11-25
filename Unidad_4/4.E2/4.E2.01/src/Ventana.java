package unidad4.E2;

/**
 * Ventana principal. NO implementa OyenteClic: expone un método suyo (alClicGuardar)
 * que se registra en el botón como referencia a método (ventana::alClicGuardar).
 */
public class Ventana {

    private final String titulo;
    private int guardados = 0;

    public Ventana(String titulo) {
        this.titulo = titulo;
    }

    public void alClicGuardar(EventoClic evento) {
        guardados++;
        System.out.println("   [Ventana '" + titulo + "'] Boton '" + evento.idBoton()
                + "' pulsado: guardando documento (guardado #" + guardados + ")");
    }
}
