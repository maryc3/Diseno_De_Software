package unidad4.E1.Ejercicio8;

/** Abstracción de la que dependen tanto el producto como los módulos (inversión de control). */
@FunctionalInterface
public interface ObservadorInventario {
    void alCambiarStock(CambioStock cambio);
}
