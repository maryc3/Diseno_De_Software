# 4.E.2 – Ejercicio 2: Indicador de Estado de Conexión de Red

**Objetivo:** desacoplar la fuente del estado de red de sus representaciones visuales.

## Archivos

| Archivo | Rol |
|---|---|
| `EstadoRed` | Enum: `CONECTADO`, `DESCONECTADO`, `INTERMITENTE`. |
| `ObservadorRed` | **Interfaz Observer.** Recibe estado anterior y nuevo. |
| `MonitorRed` | **Sujeto.** `cambiarEstado(...)`. Lista privada y concurrente. |
| `IconoBarraHerramientas` | Observador: dibuja un icono según el estado. |
| `BarraEstado` | Observador: muestra el texto de estado. |
| `Main` | Recorre los tres estados. |

## Por qué está hecho así
- **Solo notifica si el estado realmente cambió:** el enunciado dice "cada vez que la conexión **pasa de** activa a inactiva". Si se pide el mismo estado dos veces, `cambiarEstado` sale sin notificar (evita parpadeos innecesarios).
- **Sin polling:** el icono y la barra nunca le "preguntan" nada al monitor; reaccionan a la notificación (restricción técnica 1).
- La lista de suscriptores es privada y es `CopyOnWriteArrayList`; `cambiarEstado` es `synchronized` (restricción técnica 2).
- Se envía también el estado **anterior** (útil para mensajes tipo "volvió la conexión").

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E2/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
--- Se establece la conexion ---
   [Icono barra de herramientas] [ (o) ]  senal completa
   [Barra de estado] Red: CONECTADO (antes: DESCONECTADO)
--- Se informa CONECTADO otra vez (no hay cambio: nadie es notificado) ---
--- La senal se vuelve inestable ---
   [Icono barra de herramientas] [ (~) ]  senal inestable
   [Barra de estado] Red: INTERMITENTE (antes: CONECTADO)
--- Se pierde la conexion ---
   [Icono barra de herramientas] [ (x) ]  sin senal
   [Barra de estado] Red: DESCONECTADO (antes: INTERMITENTE)
```
Bajo el segundo encabezado **no aparece nada**: es lo correcto.

## Requisitos cubiertos
- [x] Cambiar el estado (Conectado, Desconectado, Intermitente)
- [x] Actualizar icono y texto de forma automática y simultánea
- [x] Sin consultas periódicas (polling)
- [x] Lista de suscriptores encapsulada y segura
