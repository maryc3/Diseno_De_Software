package unidad4.E2;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Producto de la lista de deseos. El precio es privado y solo cambia por
 * actualizarPrecio(), que valida el valor y decide si hay que notificar.
 * Cada producto tiene SUS propios canales: solo se avisa a los vinculados a ese artículo.
 */
public class Producto {

    private final String nombre;
    private double precio;
    private final CopyOnWriteArrayList<ObservadorPrecio> canales = new CopyOnWriteArrayList<>();

    public Producto(String nombre, double precioInicial) {
        this.nombre = nombre;
        this.precio = precioInicial;
    }

    public void vincular(ObservadorPrecio canal) {
        canales.addIfAbsent(canal);
    }

    public void desvincular(ObservadorPrecio canal) {
        canales.remove(canal);
    }

    public void actualizarPrecio(double nuevoPrecio) {
        if (nuevoPrecio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        double anterior = this.precio;
        this.precio = nuevoPrecio;
        System.out.println(String.format(java.util.Locale.US, "Catalogo: %s pasa de %.2f a %.2f", nombre, anterior, nuevoPrecio));

        // Regla condicional: solo si BAJA el precio
        if (nuevoPrecio < anterior) {
            CambioPrecio cambio = new CambioPrecio(nombre, anterior, nuevoPrecio);
            for (ObservadorPrecio canal : canales) {
                canal.alBajarPrecio(cambio);
            }
        }
    }

    public double getPrecio() {
        return precio;
    }
}
