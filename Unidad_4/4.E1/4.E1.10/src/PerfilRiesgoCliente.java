package unidad4.E1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Sujeto observable de grado empresarial.
 *  - Ejecución secuencial y en orden de registro de todos los observadores.
 *  - try-catch POR observador: la falla de uno no bloquea a los demás.
 *  - Lista CopyOnWriteArrayList: segura ante registros concurrentes desde varios hilos.
 *  - cambiarNivel es synchronized: dos cambios simultáneos no se mezclan (se auditan uno por vez).
 *  - Historial inmutable hacia afuera.
 */
public class PerfilRiesgoCliente {

    private final String idCliente;
    private NivelRiesgo nivel;
    private final CopyOnWriteArrayList<ObservadorRegulatorio> observadores = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<InformeNotificacion> historial = new CopyOnWriteArrayList<>();

    public PerfilRiesgoCliente(String idCliente, NivelRiesgo nivelInicial) {
        this.idCliente = idCliente;
        this.nivel = nivelInicial;
    }

    public void registrar(ObservadorRegulatorio observador) {
        observadores.addIfAbsent(observador);
    }

    public void quitar(ObservadorRegulatorio observador) {
        observadores.remove(observador);
    }

    public int cantidadObservadores() {
        return observadores.size();
    }

    public synchronized InformeNotificacion cambiarNivel(NivelRiesgo nuevoNivel) {
        CambioRiesgo cambio = new CambioRiesgo(idCliente, nivel, nuevoNivel);
        this.nivel = nuevoNivel;

        List<RegistroNotificacion> registros = new ArrayList<>();
        for (ObservadorRegulatorio observador : observadores) {
            try {
                observador.procesar(cambio);
                registros.add(new RegistroNotificacion(observador.nombre(), true, "Procesado correctamente"));
            } catch (Exception e) {
                registros.add(new RegistroNotificacion(observador.nombre(), false, e.getMessage()));
            }
        }

        InformeNotificacion informe = new InformeNotificacion(cambio, registros);
        historial.add(informe);
        return informe;
    }

    /** Copia inmutable: quien la recibe no puede alterar la traza de auditoría. */
    public List<InformeNotificacion> getHistorial() {
        return List.copyOf(historial);
    }
}
