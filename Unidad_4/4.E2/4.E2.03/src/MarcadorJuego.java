package unidad4.E2;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Lógica de puntuación del juego (sujeto observable). Solo calcula y notifica:
 * no contiene ninguna llamada a clases de pantalla ni de persistencia de récords.
 */
public class MarcadorJuego {

    private int puntuacion = 0;
    private final CopyOnWriteArrayList<ObservadorPuntuacion> oyentes = new CopyOnWriteArrayList<>();

    public void suscribir(ObservadorPuntuacion oyente) {
        oyentes.addIfAbsent(oyente);
    }

    public void desuscribir(ObservadorPuntuacion oyente) {
        oyentes.remove(oyente);
    }

    public void sumarPuntos(int puntos, String motivo) {
        if (puntos <= 0) {
            throw new IllegalArgumentException("Los puntos deben ser positivos");
        }
        puntuacion += puntos;
        for (ObservadorPuntuacion oyente : oyentes) {
            oyente.alCambiarPuntuacion(puntuacion, puntos, motivo);
        }
    }
}
