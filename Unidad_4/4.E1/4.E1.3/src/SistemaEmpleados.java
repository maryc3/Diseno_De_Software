package unidad4.E1;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Sujeto observable y lógica de negocio. Cada operación crítica emite un
 * EventoAuditoria; este sistema NO escribe en consola ni en archivos.
 */
public class SistemaEmpleados {

    private final Map<Integer, Empleado> empleados = new LinkedHashMap<>();
    private final CopyOnWriteArrayList<OyenteAuditoria> oyentes = new CopyOnWriteArrayList<>();

    public void suscribir(OyenteAuditoria oyente) {
        oyentes.addIfAbsent(oyente);
    }

    public void desuscribir(OyenteAuditoria oyente) {
        oyentes.remove(oyente);
    }

    public void alta(Empleado empleado) {
        empleados.put(empleado.getId(), empleado);
        emitir(OperacionEmpleado.ALTA, empleado.getId(),
                "Alta de " + empleado.getNombre() + " (" + empleado.getCargo() + ")");
    }

    public void baja(int id) {
        Empleado empleado = obtener(id);
        empleados.remove(id);
        emitir(OperacionEmpleado.BAJA, id, "Baja de " + empleado.getNombre());
    }

    public void modificarSalario(int id, double nuevoSalario) {
        Empleado empleado = obtener(id);
        double anterior = empleado.getSalario();
        empleado.setSalario(nuevoSalario);
        emitir(OperacionEmpleado.MODIFICACION, id, String.format(Locale.US,
                "Salario de %s: %.2f -> %.2f", empleado.getNombre(), anterior, nuevoSalario));
    }

    public void modificarCargo(int id, String nuevoCargo) {
        Empleado empleado = obtener(id);
        String anterior = empleado.getCargo();
        empleado.setCargo(nuevoCargo);
        emitir(OperacionEmpleado.MODIFICACION, id,
                "Cargo de " + empleado.getNombre() + ": " + anterior + " -> " + nuevoCargo);
    }

    private Empleado obtener(int id) {
        Empleado empleado = empleados.get(id);
        if (empleado == null) {
            throw new IllegalArgumentException("No existe el empleado #" + id);
        }
        return empleado;
    }

    private void emitir(OperacionEmpleado operacion, int id, String detalle) {
        EventoAuditoria evento = new EventoAuditoria(operacion, id, detalle, LocalDateTime.now());
        for (OyenteAuditoria oyente : oyentes) {
            oyente.alAuditar(evento);
        }
    }
}
