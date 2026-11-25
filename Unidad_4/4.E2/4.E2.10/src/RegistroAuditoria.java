package unidad4.E2;

import java.util.List;

/**
 * Registro INMUTABLE de una auditoría: es un record (campos finales, sin setters) y su lista
 * se copia con List.copyOf, que devuelve una lista imposible de modificar.
 */
public record RegistroAuditoria(CambioNivelRiesgo cambio, List<ResultadoNotificacion> resultados) {

    public RegistroAuditoria {
        resultados = List.copyOf(resultados);
    }

    public List<String> entidadesExitosas() {
        return resultados.stream().filter(ResultadoNotificacion::exitosa).map(ResultadoNotificacion::entidad).toList();
    }

    public List<String> entidadesConIncidencias() {
        return resultados.stream().filter(r -> !r.exitosa()).map(ResultadoNotificacion::entidad).toList();
    }
}
