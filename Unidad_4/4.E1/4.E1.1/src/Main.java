package unidad4.E1.Ejercicio1;;

public class Main {

    public static void main(String[] args) {
        EstacionMeteorologica estacion = new EstacionMeteorologica();

        PantallaDigital hall = new PantallaDigital("Hall principal");
        PantallaDigital cafeteria = new PantallaDigital("Cafeteria");
        RegistroHistorico historico = new RegistroHistorico();

        estacion.registrar(hall);
        estacion.registrar(cafeteria);
        estacion.registrar(historico);

        System.out.println("--- Cambio 1: 3 observadores suscritos ---");
        estacion.setTemperatura(21.5);

        System.out.println("--- Cambio 2 ---");
        estacion.setTemperatura(24.0);

        System.out.println("--- Se desvincula la pantalla de la cafeteria ---");
        estacion.desvincular(cafeteria);

        System.out.println("--- Cambio 3: solo 2 observadores ---");
        estacion.setTemperatura(31.2);

        historico.imprimirResumen();
    }
}
