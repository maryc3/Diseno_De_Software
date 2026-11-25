package unidad4.E2;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Gestor de eventos indexados por identificador de canal (emisor): cada canal tiene su propia
 * lista de seguidores y solo ellos reciben la alerta de un video nuevo.
 */
public class PlataformaVideos {

    private final Map<String, CopyOnWriteArrayList<ObservadorSeguidor>> seguidoresPorCanal = new ConcurrentHashMap<>();

    public void suscribir(String idCanal, ObservadorSeguidor seguidor) {
        seguidoresPorCanal.computeIfAbsent(idCanal, id -> new CopyOnWriteArrayList<>()).addIfAbsent(seguidor);
    }

    public void cancelar(String idCanal, ObservadorSeguidor seguidor) {
        List<ObservadorSeguidor> lista = seguidoresPorCanal.get(idCanal);
        if (lista != null) {
            lista.remove(seguidor);
        }
    }

    public void publicar(Video video) {
        System.out.println(">> " + video.idCanal() + " sube: '" + video.titulo() + "' (" + video.duracionFormateada() + ")");
        List<ObservadorSeguidor> seguidores = seguidoresPorCanal.get(video.idCanal());
        if (seguidores == null || seguidores.isEmpty()) {
            System.out.println("   (el canal no tiene seguidores)");
            return;
        }
        for (ObservadorSeguidor seguidor : seguidores) {
            seguidor.alSubirVideo(video);
        }
    }
}
