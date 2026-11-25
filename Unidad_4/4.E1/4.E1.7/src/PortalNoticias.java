package unidad4.E1;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Gestor de eventos indexados por categoría: un observador solo recibe las
 * noticias de las categorías a las que se suscribió.
 */
public class PortalNoticias {

    private final Map<Categoria, CopyOnWriteArrayList<ObservadorNoticias>> suscriptores =
            new EnumMap<>(Categoria.class);

    public PortalNoticias() {
        for (Categoria categoria : Categoria.values()) {
            suscriptores.put(categoria, new CopyOnWriteArrayList<>());
        }
    }

    public void suscribir(Categoria categoria, ObservadorNoticias observador) {
        suscriptores.get(categoria).addIfAbsent(observador);
    }

    public void cancelar(Categoria categoria, ObservadorNoticias observador) {
        suscriptores.get(categoria).remove(observador);
    }

    public void publicar(Noticia noticia) {
        System.out.println(">> Redactor publica en " + noticia.categoria() + ": " + noticia.titulo());
        for (ObservadorNoticias observador : suscriptores.get(noticia.categoria())) {
            observador.recibirNoticia(noticia);
        }
    }
}
