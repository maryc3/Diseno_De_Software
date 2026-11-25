package unidad4.E2;

public class Main {

    public static void main(String[] args) {
        SensorLuzSolar fachadaNorte = new SensorLuzSolar("fachada norte");
        SensorLuzSolar fachadaSur = new SensorLuzSolar("fachada sur");

        PersianasMotorizadas persianas = new PersianasMotorizadas();
        LucesInteriores luces = new LucesInteriores();

        // Varios actuadores escuchando varias fuentes a la vez
        for (SensorLuzSolar sensor : new SensorLuzSolar[]{fachadaNorte, fachadaSur}) {
            sensor.suscribir(persianas);
            sensor.suscribir(luces);
        }

        System.out.println("=== Luz moderada en la fachada norte ===");
        fachadaNorte.medir(35000);

        System.out.println("=== Sol fuerte en la fachada sur ===");
        fachadaSur.medir(80000);

        // Se agrega un dispositivo nuevo: los sensores NO se modifican
        fachadaSur.suscribir(new CortinasTermicas());

        System.out.println("=== Sol fuerte otra vez en la sur (ya con cortinas termicas) ===");
        fachadaSur.medir(85000);

        System.out.println("=== Atardecer en la sur ===");
        fachadaSur.medir(5000);
    }
}
