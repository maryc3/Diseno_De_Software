package unidad4.E1;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/** Bitácora en archivo: agrega una línea por cada evento recibido. */
public class AuditorArchivo implements OyenteAuditoria {

    private final Path ruta;

    public AuditorArchivo(Path ruta) {
        this.ruta = ruta;
    }

    @Override
    public void alAuditar(EventoAuditoria evento) {
        try {
            Files.writeString(ruta, evento.comoTexto() + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo escribir la bitacora", e);
        }
    }
}
