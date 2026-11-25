package unidad4.E2;

/** Escucha todos los tipos. Falla cuando la severidad es 5 para probar el aislamiento de errores. */
public class ServicioGuardia implements ObservadorEvento {

    @Override
    public void alRecibir(EventoSistema evento) {
        if (evento.codigoSeveridad() == 5) {
            throw new IllegalStateException("el buscapersonas de guardia no tiene cobertura");
        }
        System.out.println("   [Guardia] Aviso al ingeniero de turno (sev " + evento.codigoSeveridad()
                + "): " + evento.tipo() + " en " + evento.nodoOrigen());
    }
}
