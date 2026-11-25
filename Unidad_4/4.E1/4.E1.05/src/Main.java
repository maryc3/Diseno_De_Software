package unidad4.E1;

public class Main {

    public static void main(String[] args) {
        SensorHumo sensor1 = new SensorHumo("S-01", 1, "Hall");
        SensorHumo sensor2 = new SensorHumo("S-02", 2, "Oficinas");
        SensorHumo sensor3 = new SensorHumo("S-03", 3, "Archivo");

        CentralAlarmas central = new CentralAlarmas();
        SistemaRociadores rociadores = new SistemaRociadores();
        ServicioConserjeria conserjeria = new ServicioConserjeria();

        // Un mismo observador escucha varias fuentes de peligro
        for (SensorHumo sensor : new SensorHumo[]{sensor1, sensor2, sensor3}) {
            sensor.suscribir(central);
            sensor.suscribir(rociadores);
        }
        sensor1.suscribir(conserjeria);
        sensor2.suscribir(conserjeria);

        System.out.println("=== Deteccion en piso 2 ===");
        sensor2.detectarHumo();

        // Se agrega un nuevo tipo de dispositivo: el sensor NO se modifica
        ControlAscensores ascensores = new ControlAscensores();
        sensor3.suscribir(ascensores);

        System.out.println("=== Deteccion en piso 3 (ya con el sistema de ascensores) ===");
        sensor3.detectarHumo();
    }
}
