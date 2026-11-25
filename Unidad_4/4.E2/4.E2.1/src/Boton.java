package unidad4.E2;

import java.util.ArrayList;
import java.util.List;

/** Sujeto observable. No conoce a la ventana ni a ninguna clase concreta: solo la interfaz OyenteClic. */
public class Boton {

    private final String id;
    private int clics = 0;
    private final List<OyenteClic> oyentes = new ArrayList<>();

    public Boton(String id) {
        this.id = id;
    }

    public void registrar(OyenteClic oyente) {
        if (oyente != null && !oyentes.contains(oyente)) {
            oyentes.add(oyente);
        }
    }

    public void quitar(OyenteClic oyente) {
        oyentes.remove(oyente);
    }

    /** Simula que el usuario hizo clic sobre el componente gráfico. */
    public void simularClic() {
        clics++;
        EventoClic evento = new EventoClic(id, clics);
        for (OyenteClic oyente : new ArrayList<>(oyentes)) {
            oyente.alHacerClic(evento);
        }
    }
}
