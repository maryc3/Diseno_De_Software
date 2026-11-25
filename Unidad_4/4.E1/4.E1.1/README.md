# 4.E.1.1 – Ejercicio 1: Notificador de Cambio de Temperatura

**Objetivo:** entender la estructura básica del patrón Observer (relación uno-a-muchos entre un sujeto y sus observadores).

## Archivos

| Archivo | Rol en el patrón |
|---|---|
| `ObservadorTemperatura` | **Interfaz Observer.** Declara `actualizar(double)`. Es lo único que el sujeto conoce de las pantallas. |
| `EstacionMeteorologica` | **Sujeto (Subject).** Guarda la temperatura y una lista genérica de observadores. Tiene `registrar`, `desvincular` y notifica en `setTemperatura`. |
| `PantallaDigital` | **Observador concreto.** Imprime la temperatura con su ubicación. |
| `RegistroHistorico` | **Observador concreto distinto.** No muestra nada: guarda las lecturas. Demuestra que los observadores pueden hacer cosas diferentes. |
| `Main` | Arma el escenario y prueba registrar/desvincular. |

## Por qué está hecho así
- La estación **solo conoce la interfaz** `ObservadorTemperatura` (restricción técnica 1): agregar un tipo de pantalla nuevo no obliga a tocar la estación.
- La notificación itera sobre una **copia** de la lista, así un observador puede desvincularse mientras se notifica sin romper el bucle.
- `registrar` evita duplicados con `contains`.

## Cómo ejecutarlo
En IntelliJ: clic derecho sobre `Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse


(El `RegistroHistorico` no imprime en cada cambio; solo al final.) Después de desvincular, la cafetería **deja de recibir** actualizaciones.

## Requisitos cubiertos
- [x] Registrar y desvincular dispositivos
- [x] Notificación automática a todos los suscritos
- [x] Sujeto con colección genérica de observadores
- [x] Observadores con interfaz común

