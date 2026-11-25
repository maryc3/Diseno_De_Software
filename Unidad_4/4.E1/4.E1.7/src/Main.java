package unidad4.E1;

public class Main {

    public static void main(String[] args) {
        PortalNoticias portal = new PortalNoticias();

        Lector ana = new Lector("Ana");
        Lector beto = new Lector("Beto");
        Lector carla = new Lector("Carla");

        portal.suscribir(Categoria.TECNOLOGIA, ana);
        portal.suscribir(Categoria.DEPORTES, ana);
        portal.suscribir(Categoria.ECONOMIA, beto);
        portal.suscribir(Categoria.TECNOLOGIA, beto);
        portal.suscribir(Categoria.DEPORTES, carla);

        portal.publicar(new Noticia(Categoria.TECNOLOGIA, "Nuevo procesador de 2 nm"));
        portal.publicar(new Noticia(Categoria.DEPORTES, "Final de la copa este domingo"));
        portal.publicar(new Noticia(Categoria.ECONOMIA, "El dolar cierra estable"));

        System.out.println("--- Ana cancela su suscripcion a TECNOLOGIA ---");
        portal.cancelar(Categoria.TECNOLOGIA, ana);
        portal.publicar(new Noticia(Categoria.TECNOLOGIA, "Lanzan un nuevo lenguaje de programacion"));

        System.out.println("--- Bandejas finales ---");
        ana.mostrarBandeja();
        beto.mostrarBandeja();
        carla.mostrarBandeja();
    }
}
