package unidad4.E1.Ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class EstacionMeteorologica {

    private final List<ObservadorTemperatura> observadores = new ArrayList<>();
    private double temperatura;

    public void registrar(ObservadorTemperatura observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void desvincular(ObservadorTemperatura observador) {
        observadores.remove(observador);
    }

    public void setTemperatura(double nuevaTemperatura) {
        this.temperatura = nuevaTemperatura;
        notificar();
    }

    public double getTemperatura() {
        return temperatura;
    }

    private void notificar() {
        // Se itera sobre una copia para que un observador pueda desvincularse durante la notificación.
        for (ObservadorTemperatura observador : new ArrayList<>(observadores)) {
            observador.actualizar(temperatura);
        }
    }
}
