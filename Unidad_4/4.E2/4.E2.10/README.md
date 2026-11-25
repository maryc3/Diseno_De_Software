# 4.E.2 – Ejercicio 10: Sistema Empresarial de Cumplimiento Normativo y Auditoría de Riesgos

**Objetivo:** Observer robusto de grado empresarial: seguridad estricta, trazabilidad y protección contra fallos en cascada.

> Enunciado muy parecido al 10 de 4.E.1. Esta versión trabaja con **cuentas corporativas** y **entidades receptoras**, y usa **`ReentrantReadWriteLock`** (en lugar de `CopyOnWriteArrayList`) para proteger la lista, para mostrar otra técnica de seguridad multihilo.

## Archivos

| Archivo | Rol |
|---|---|
| `NivelRiesgoCuenta` | Enum: `BAJO`, `MEDIO`, `ALTO`, `CRITICO`. |
| `CambioNivelRiesgo` | *Record*: cuenta, nivel anterior y nuevo. |
| `EntidadReceptora` | **Interfaz Observer.** `notificar` puede lanzar excepción. |
| `ResultadoNotificacion` | *Record*: entidad, éxito/fallo y detalle. |
| `RegistroAuditoria` | *Record* **inmutable** con los resultados + utilidades `entidadesExitosas()` / `entidadesConIncidencias()`. |
| `CuentaCorporativa` | **Sujeto.** |
| `PrevencionLavadoDinero`, `AnalisisLegal`, `OrganismoRegulatorioInternacional` | Las tres entidades. El organismo **falla** (timeout) cuando el nuevo nivel es `ALTO`. |
| `Main` | Éxito, fallo aislado, auditoría inmutable y prueba multihilo. |

## Por qué está hecho así
- **Ejecución secuencial y controlada:** `cambiarNivel` es `synchronized`, toma una **foto** de la lista bajo *read lock* y notifica **en orden**, una entidad por vez.
- **`ReentrantReadWriteLock`:** muchos hilos pueden leer a la vez; registrar/quitar toma el *write lock* (exclusivo). Es la alternativa "manual" a `CopyOnWriteArrayList`.
- **Aislamiento de errores:** `try/catch` por entidad; el fallo se registra y no frena a las demás.
- **Registro inmutable:** `record` + `List.copyOf`. En `Main` se intenta agregar un resultado falso a un registro y salta `UnsupportedOperationException`.
- **Multihilo:** 8 tareas en un `ExecutorService` registran entidades a la vez: resultado exacto de 11 (3 + 8).

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E2/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
=== Cambio 1: BAJO -> ALTO (el organismo internacional falla por timeout) ===
   [AML] Monitoreo reforzado de la cuenta CTA-CORP-77 (BAJO -> ALTO)
   [Legal] Analisis contractual abierto para CTA-CORP-77
Notificadas con exito: [Prevencion de Lavado de Dinero, Analisis Legal]
Con incidencias:       [Organismo Regulatorio Internacional]
=== Cambio 2: ALTO -> MEDIO (todo funciona) ===
   [AML] Monitoreo reforzado de la cuenta CTA-CORP-77 (ALTO -> MEDIO)
   [Legal] Analisis contractual abierto para CTA-CORP-77
   [Organismo] Reporte enviado sobre la cuenta CTA-CORP-77
Notificadas con exito: [Prevencion de Lavado de Dinero, Analisis Legal, Organismo Regulatorio Internacional]
Con incidencias:       []
=== Auditoria inmutable ===
Registros de auditoria: 2
Intento de alterar un registro -> UnsupportedOperationException (correcto)
=== Seguridad multihilo: 8 hilos registran entidades a la vez ===
Entidades registradas: 11 (esperado: 11)
```

## Requisitos cubiertos
- [x] Registrar y disparar modificaciones de nivel con ejecución secuencial y controlada
- [x] Registro inmutable de entidades notificadas con éxito y las que tuvieron incidencias
- [x] Aislamiento de errores por observador
- [x] Gestión interna de la lista segura en entornos multihilo
