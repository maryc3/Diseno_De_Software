package unidad4.E2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Sujeto observable de grado empresarial.
 *  - Lista de entidades protegida con ReentrantReadWriteLock: muchos lectores a la vez,
 *    escritura exclusiva al registrar/quitar (alternativa explícita a CopyOnWriteArrayList).
 *  - cambiarNivel toma una "foto" de las entidades y las notifica EN ORDEN, una por vez.
 *  - Cada entidad se ejecuta en su propio try-catch (aislamiento de fallos).
 *  - Registro de auditoría inmutable.
 */
public class CuentaCorporativa {

    private final String id;
    private NivelRiesgoCuenta nivel;

    private final List<EntidadReceptora> entidades = new ArrayList<>();
    private final ReentrantReadWriteLock cerrojo = new ReentrantReadWriteLock();
    private final CopyOnWriteArrayList<RegistroAuditoria> auditoria = new CopyOnWriteArrayList<>();

    public CuentaCorporativa(String id, NivelRiesgoCuenta nivelInicial) {
        this.id = id;
        this.nivel = nivelInicial;
    }

    public void registrar(EntidadReceptora entidad) {
        cerrojo.writeLock().lock();
        try {
            if (!entidades.contains(entidad)) {
                entidades.add(entidad);
            }
        } finally {
            cerrojo.writeLock().unlock();
        }
    }

    public void quitar(EntidadReceptora entidad) {
        cerrojo.writeLock().lock();
        try {
            entidades.remove(entidad);
        } finally {
            cerrojo.writeLock().unlock();
        }
    }

    public int cantidadEntidades() {
        cerrojo.readLock().lock();
        try {
            return entidades.size();
        } finally {
            cerrojo.readLock().unlock();
        }
    }

    public synchronized RegistroAuditoria cambiarNivel(NivelRiesgoCuenta nuevoNivel) {
        CambioNivelRiesgo cambio = new CambioNivelRiesgo(id, nivel, nuevoNivel);
        this.nivel = nuevoNivel;

        List<EntidadReceptora> foto;
        cerrojo.readLock().lock();
        try {
            foto = new ArrayList<>(entidades);
        } finally {
            cerrojo.readLock().unlock();
        }

        List<ResultadoNotificacion> resultados = new ArrayList<>();
        for (EntidadReceptora entidad : foto) {
            try {
                entidad.notificar(cambio);
                resultados.add(new ResultadoNotificacion(entidad.nombre(), true, "Notificada"));
            } catch (Exception e) {
                resultados.add(new ResultadoNotificacion(entidad.nombre(), false, e.getMessage()));
            }
        }

        RegistroAuditoria registro = new RegistroAuditoria(cambio, resultados);
        auditoria.add(registro);
        return registro;
    }

    public List<RegistroAuditoria> getAuditoria() {
        return List.copyOf(auditoria);
    }
}
