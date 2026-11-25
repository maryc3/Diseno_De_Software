# 4.E.2 – Ejercicio 5: Automatización de Persianas según Sensor de Luz Solar

**Objetivo:** controlar la interacción en escenarios con **múltiples sujetos** y dispositivos actuadores, con arquitectura desacoplada.

## Archivos

| Archivo | Rol |
|---|---|
| `LecturaLuz` | *Record*: zona y lux medidos. |
| `ObservadorLuz` | **Interfaz Observer** de todo dispositivo actuador. |
| `SensorLuzSolar` | **Sujeto.** Un objeto por fachada (norte y sur). `medir(lux)`. |
| `PersianasMotorizadas` | Respuesta **mecánica**: cierra 0% / 50% / 100% según lux. |
| `LucesInteriores` | Respuesta **eléctrica**: atenúa las luces (cuanta más luz, menos intensidad; mínimo 10%). |
| `CortinasTermicas` | Dispositivo **nuevo**, agregado después. |
| `Main` | Dos sensores y los actuadores. |

## Por qué está hecho así
- **Múltiples sujetos:** las persianas y las luces escuchan **ambos** sensores; la lectura incluye la **zona**, así el actuador sabe de qué fachada viene.
- **Extensible sin tocar el sensor** (restricción técnica 2): `CortinasTermicas` se suscribe en `Main` a la fachada sur y el código del sensor no cambia.
- Umbrales de las persianas: ≥ 60000 lux cierra 100%, ≥ 30000 cierra 50%, menos abre.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E2/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
=== Luz moderada en la fachada norte ===
Sensor fachada norte mide 35000 lux
   [Persianas fachada norte] cierre al 50%
   [Luces fachada norte] intensidad al 65%
=== Sol fuerte en la fachada sur ===
Sensor fachada sur mide 80000 lux
   [Persianas fachada sur] cierre al 100%
   [Luces fachada sur] intensidad al 20%
=== Sol fuerte otra vez en la sur (ya con cortinas termicas) ===
Sensor fachada sur mide 85000 lux
   [Persianas fachada sur] cierre al 100%
   [Luces fachada sur] intensidad al 15%
   [Cortinas termicas fachada sur] desplegadas para aislar el calor
=== Atardecer en la sur ===
Sensor fachada sur mide 5000 lux
   [Persianas fachada sur] cierre al 0%
   [Luces fachada sur] intensidad al 95%
   [Cortinas termicas fachada sur] recogidas
```
Las cortinas térmicas aparecen **solo desde que se suscriben** y solo para la fachada sur.

## Requisitos cubiertos
- [x] Simular variación de luz exterior
- [x] Activar de forma simultánea respuestas mecánicas y eléctricas
- [x] Varios actuadores escuchan las variaciones sin acoplamiento rígido
- [x] Nuevo dispositivo sin modificar el código del sensor
