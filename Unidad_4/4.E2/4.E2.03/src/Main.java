package unidad4.E2;

public class Main {

    public static void main(String[] args) {
        MarcadorJuego marcador = new MarcadorJuego();
        PantallaJuego pantalla = new PantallaJuego();
        RegistroRecords records = new RegistroRecords(150);

        marcador.suscribir(pantalla);
        marcador.suscribir(records);

        System.out.println("--- Nivel 1 (record a superar: 150) ---");
        marcador.sumarPuntos(50, "destruir obstaculo");
        marcador.sumarPuntos(100, "recolectar objeto");
        marcador.sumarPuntos(75, "destruir obstaculo");

        System.out.println("--- Se oculta la pantalla (desuscripcion dinamica); sigue el registro ---");
        marcador.desuscribir(pantalla);
        marcador.sumarPuntos(25, "recolectar objeto");

        System.out.println("Record final guardado: " + records.getRecord());
    }
}
