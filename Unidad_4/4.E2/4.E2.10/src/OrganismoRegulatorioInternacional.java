package unidad4.E2;

import java.util.concurrent.TimeoutException;

/** Falla a propósito cuando el nuevo nivel es ALTO, simulando un tiempo de espera agotado. */
public class OrganismoRegulatorioInternacional implements EntidadReceptora {

    @Override
    public String nombre() {
        return "Organismo Regulatorio Internacional";
    }

    @Override
    public void notificar(CambioNivelRiesgo cambio) throws Exception {
        if (cambio.nivelNuevo() == NivelRiesgoCuenta.ALTO) {
            throw new TimeoutException("tiempo de espera agotado al contactar al organismo");
        }
        System.out.println("   [Organismo] Reporte enviado sobre la cuenta " + cambio.idCuenta());
    }
}
