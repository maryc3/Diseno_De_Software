# 4.E.1 – Ejercicio 10: Sistema Empresarial de Notificación de Riesgos y Cumplimiento Legal

**Objetivo:** un Observer robusto de grado empresarial: seguridad estricta, trazabilidad de eventos críticos y protección contra fallos en cascada.

## Archivos

| Archivo | Rol |
|---|---|
| `NivelRiesgo` | Enum: `BAJO`, `MEDIO`, `ALTO`, `CRITICO`. |
| `CambioRiesgo` | *Record*: cliente, nivel anterior y nuevo. |
| `ObservadorRegulatorio` | **Interfaz Observer.** Su `procesar` puede lanzar `Exception`. |
| `RegistroNotificacion` | *Record*: resultado de notificar a **un** observador (éxito/fallo + detalle). |
| `InformeNotificacion` | *Record* **inmutable** con todos los registros de una ronda. |
| `PerfilRiesgoCliente` | **Sujeto.** |
| `CumplimientoLegal`, `PrevencionLavadoDinero`, `GeneradorReportesRegulatorios` | Los tres subsistemas obligatorios. AML falla a propósito con `CRITICO`. |
| `Main` | Demuestra éxito, fallo aislado, inmutabilidad y seguridad multihilo. |

## Por qué está hecho así
- **Ejecución secuencial y controlada:** `cambiarNivel` es `synchronized` y recorre los observadores **en orden de registro**, uno por vez.
- **Aislamiento de errores:** `try/catch` **por observador** (restricción técnica 1). Si AML falla, Legal y Reportes igual corren, y el fallo queda registrado.
- **Registro inmutable:** `InformeNotificacion` es un `record` y su lista se copia con `List.copyOf` (imposible de modificar). `getHistorial()` devuelve otra copia inmutable. En `Main` se intenta `historial.clear()` y salta `UnsupportedOperationException`: la traza de auditoría no se puede manipular.
- **Seguridad multihilo** (restricción técnica 2): `CopyOnWriteArrayList` para los observadores. `Main` lanza 8 hilos que registran observadores a la vez y al final hay **exactamente 11** (3 + 8).

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E1/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
=== Cambio 1: BAJO -> ALTO (todos los sistemas funcionan) ===
   [Legal] Revisando legajo de CLI-1001 (BAJO -> ALTO)
   [AML] Analisis de transacciones de CLI-1001 completado
   [Reportes] Reporte obligatorio generado para CLI-1001
Informe del cambio BAJO -> ALTO
   - Cumplimiento Legal: OK (Procesado correctamente)
   - Prevencion de Lavado de Dinero: OK (Procesado correctamente)
   - Reportes Regulatorios: OK (Procesado correctamente)
=== Cambio 2: ALTO -> CRITICO (AML falla, los demas siguen) ===
   [Legal] Revisando legajo de CLI-1001 (ALTO -> CRITICO)
   [Reportes] Reporte obligatorio generado para CLI-1001
Informe del cambio ALTO -> CRITICO
   - Cumplimiento Legal: OK (Procesado correctamente)
   - Prevencion de Lavado de Dinero: FALLO (motor de reglas AML no disponible)
   - Reportes Regulatorios: OK (Procesado correctamente)
=== Historial inmutable ===
Cantidad de informes: 2
Intento de modificar el historial -> UnsupportedOperationException (correcto)
=== Seguridad multihilo: 8 hilos registran observadores a la vez ===
Observadores registrados: 11 (esperado: 11)
```
En el cambio 2 **no aparece** la línea `[AML]` porque falló, pero Legal y Reportes sí se ejecutaron.

## Requisitos cubiertos
- [x] Disparar cambios de perfil con ejecución secuencial y controlada de todos los observadores
- [x] Registro inmutable de quiénes fueron notificados y quiénes tuvieron incidencias
- [x] Aislamiento de errores `try/catch` por observador
- [x] Lista de observadores segura en entornos multihilo
