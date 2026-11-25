# 4.E.1 – Ejercicio 3: Registro Automático de Auditoría por Consola

**Objetivo:** canalizar eventos de negocio hacia varios receptores independientes.

## Archivos

| Archivo | Rol |
|---|---|
| `OperacionEmpleado` | Enum: `ALTA`, `BAJA`, `MODIFICACION`. |
| `EventoAuditoria` | *Record* inmutable: qué pasó, a quién, detalle y momento. Es el mensaje que viaja. |
| `OyenteAuditoria` | **Interfaz Observer.** |
| `Empleado` | Dato de negocio. Sus setters son de **visibilidad de paquete**: solo el sistema central lo modifica. |
| `SistemaEmpleados` | **Sujeto + lógica de negocio.** `alta`, `baja`, `modificarSalario`, `modificarCargo`; cada una emite un evento. |
| `AuditorConsola` | Observador: imprime el evento. |
| `AuditorArchivo` | Observador: lo agrega a un archivo de bitácora. |
| `Main` | Demo, incluye una desuscripción en plena ejecución. |

## Por qué está hecho así
- **La lógica de negocio no escribe en consola ni archivos** (restricción técnica 1): `SistemaEmpleados` solo construye un `EventoAuditoria` y lo entrega. Si mañana hay que auditar a una base de datos, se agrega un nuevo `OyenteAuditoria` y no se toca el sistema.
- `suscribir/desuscribir` pueden llamarse **en cualquier momento** de la ejecución (restricción técnica 2).
- El mensaje es un `record` para que ningún auditor pueda alterarlo.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E1/Main.java` → **Run 'Main.main()'**. Se crea el archivo `bitacora-auditoria-ej03.log` en la carpeta del proyecto (se borra y recrea cada vez que corre).

## Qué tiene que verse
(La fecha y hora serán las de tu ejecución.)
```
--- Con consola y archivo suscritos ---
[AUDITORIA-CONSOLA] [2026-11-23 01:09:51] ALTA empleado #1: Alta de Laura Gomez (Analista)
[AUDITORIA-CONSOLA] [2026-11-23 01:09:51] ALTA empleado #2: Alta de Martin Perez (Programador)
[AUDITORIA-CONSOLA] [2026-11-23 01:09:51] MODIFICACION empleado #2: Salario de Martin Perez: 1800.00 -> 2100.00
--- Se desuscribe la consola en plena ejecucion (solo queda el archivo) ---
--- Contenido de la bitacora (bitacora-auditoria-ej03.log) ---
  [2026-11-23 01:09:51] ALTA empleado #1: Alta de Laura Gomez (Analista)
  [2026-11-23 01:09:51] ALTA empleado #2: Alta de Martin Perez (Programador)
  [2026-11-23 01:09:51] MODIFICACION empleado #2: Salario de Martin Perez: 1800.00 -> 2100.00
  [2026-11-23 01:09:51] MODIFICACION empleado #1: Cargo de Laura Gomez: Analista -> Lider de equipo
  [2026-11-23 01:09:51] BAJA empleado #2: Baja de Martin Perez
```
Fijate que tras desuscribir la consola, las últimas dos operaciones (cambio de cargo y baja) **ya no salen por consola**, pero **sí quedan en el archivo**.

## Requisitos cubiertos
- [x] Evento de auditoría en cada modificación
- [x] Mensaje enviado a consola y archivo a la vez
- [x] Lógica de negocio sin llamadas directas a consola/archivo
- [x] Suscripción y desuscripción dinámicas
