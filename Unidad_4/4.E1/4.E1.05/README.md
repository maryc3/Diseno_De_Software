# 4.E.1 – Ejercicio 5: Monitor de Sensores de Humo en Edificio Inteligente

**Objetivo:** controlar la interacción en escenarios con **varios sujetos** y varios observadores mediante una arquitectura desacoplada.

## Archivos

| Archivo | Rol |
|---|---|
| `AlertaHumo` | *Record* con id del sensor, piso y zona (la "ubicación exacta"). |
| `ObservadorAlarma` | **Interfaz Observer** que implementa todo sistema de respuesta. |
| `SensorHumo` | **Sujeto.** Hay un objeto por sensor (3 en el ejemplo). `detectarHumo()` simula el incendio. |
| `CentralAlarmas`, `SistemaRociadores`, `ServicioConserjeria` | Observadores iniciales. |
| `ControlAscensores` | Observador **nuevo**, agregado después para demostrar extensibilidad. |
| `Main` | Crea 3 sensores y suscribe los sistemas. |

## Por qué está hecho así
- **Muchos a muchos:** la central y los rociadores escuchan los **tres** sensores; conserjería solo los pisos 1 y 2. Un mismo observador puede escuchar varias fuentes (restricción técnica 1).
- **Principio abierto/cerrado:** `ControlAscensores` se agrega en `Main` con una línea (`sensor3.suscribir(...)`) y **no se modifica nada** en `SensorHumo` (restricción técnica 2).
- Los rociadores actúan **solo en la zona del evento** porque la alerta lleva la ubicación.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E1/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
=== Deteccion en piso 2 ===
!!! Sensor S-02 detecto humo en piso 2, Oficinas
   [Central de alarmas] Sirena activada. Ubicacion: piso 2, Oficinas
   [Rociadores] Activando rociadores solo en piso 2, Oficinas
   [Conserjeria] Aviso al personal: revisar piso 2, Oficinas
=== Deteccion en piso 3 (ya con el sistema de ascensores) ===
!!! Sensor S-03 detecto humo en piso 3, Archivo
   [Central de alarmas] Sirena activada. Ubicacion: piso 3, Archivo
   [Rociadores] Activando rociadores solo en piso 3, Archivo
   [Ascensores] Bloqueados y enviados a planta baja (alerta en piso 3)
```
En el piso 2 responden tres sistemas; en el piso 3 conserjería **no** está suscrita pero sí los ascensores nuevos.

## Requisitos cubiertos
- [x] Simular detección con ubicación exacta
- [x] Activar respuestas coordinadas de los sistemas vinculados a la zona
- [x] Varios sistemas escuchan una o varias fuentes
- [x] Nuevo tipo de dispositivo sin modificar los sensores
