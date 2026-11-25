package unidad4.E1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Mensaje inmutable que viaja del sujeto a los auditores. */
public record EventoAuditoria(OperacionEmpleado operacion, int idEmpleado, String detalle, LocalDateTime momento) {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String comoTexto() {
        return "[" + FORMATO.format(momento) + "] " + operacion + " empleado #" + idEmpleado + ": " + detalle;
    }
}
