package unidad4.E1;

/** Interfaz común de los receptores de auditoría (consola, archivo, etc.). */
public interface OyenteAuditoria {
    void alAuditar(EventoAuditoria evento);
}
