package unidad4.E1;

import java.util.List;

/**
 * Registro INMUTABLE de una ronda de notificaciones: un record (sin setters) cuya lista
 * se copia con List.copyOf, que devuelve una lista que no se puede modificar.
 */
public record InformeNotificacion(CambioRiesgo cambio, List<RegistroNotificacion> registros) {

    public InformeNotificacion {
        registros = List.copyOf(registros);
    }

    public long fallidos() {
        return registros.stream().filter(r -> !r.exitoso()).count();
    }
}
