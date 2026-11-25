package unidad4.E1;

public class AuditorConsola implements OyenteAuditoria {

    @Override
    public void alAuditar(EventoAuditoria evento) {
        System.out.println("[AUDITORIA-CONSOLA] " + evento.comoTexto());
    }
}
