# 4.E.2 – Ejercicio 9: Motor Asíncrono de Eventos para Monitoreo de Servidores

**Objetivo:** Observer + bus de eventos centralizado + gestión de tipos de mensaje + control de suscripciones con alta concurrencia.

> El enunciado es el mismo que el 9 de 4.E.1. Esta versión varía el diseño: severidad como **código numérico (1–5)**, **registro de fallos** del bus (cola de "cartas muertas") y publicación asíncrona con **`CompletableFuture`**.

## Archivos

| Archivo | Rol |
|---|---|
| `TipoAlerta` | Enum: `CPU_AL_90`, `MEMORIA_INSUFICIENTE`, `FALLO_DE_DISCO`. |
| `EventoSistema` | *Record*: nodo, tipo, **código de severidad (1 a 5, validado)** y cuerpo descriptivo. |
| `ObservadorEvento` | **Interfaz Observer.** |
| `FalloEntrega` | *Record*: constancia de que un observador falló con un evento concreto. |
| `BusEventos` | **El bus.** Mapa por tipo, `publicar`, `publicarAsincrono` (devuelve `CompletableFuture<Void>`), `getFallos`, `cerrar`. |
| `ServicioAutoescalado`, `ServicioLimpiezaMemoria`, `ServicioRespaldoDisco` | Servicios por tipo. |
| `ServicioGuardia` | Escucha CPU y MEMORIA; **falla** cuando la severidad es 5. |
| `ContadorEventos` | Observador silencioso con `AtomicInteger`. |

## Por qué está hecho así
- **Indexado por tipo:** `ConcurrentHashMap<TipoAlerta, CopyOnWriteArrayList<...>>`; el despacho es una búsqueda directa.
- **Fallo aislado y registrado:** `try/catch` por observador; el error no se pierde, se guarda en `fallos` y se puede consultar con `getFallos()` (copia inmutable).
- **`CompletableFuture`:** `publicarAsincrono` devuelve el future, así el que publica puede esperar (`join`) o encadenar acciones. En la demo se publican **300** eventos y se espera a todos.
- La prueba de concurrencia usa un bus separado con solo el contador para mantener la consola limpia.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E2/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
=== 1) Despacho selectivo por tipo ===
   [Guardia] Aviso al ingeniero de turno (sev 3): CPU_AL_90 en nodo-A
   [Autoescalado] Se agregan 2 nodos al cluster (causa: CPU sostenida al 92%)
   [RespaldoDisco] Copia de seguridad urgente de nodo-B
=== 2) Un observador falla (severidad 5) y los demas igual reciben el evento ===
   [BUS] ServicioGuardia fallo: el buscapersonas de guardia no tiene cobertura -> registrado, se sigue con los demas
   [LimpiezaMemoria] Reinicio de procesos pesados en nodo-C
=== 3) Fallos registrados por el bus ===
   - ServicioGuardia no pudo procesar MEMORIA_INSUFICIENTE de nodo-C: el buscapersonas de guardia no tiene cobertura
=== 4) Concurrencia: 300 eventos asincronicos con CompletableFuture ===
Eventos recibidos por el contador: 300 (esperado: 300)
```

## Requisitos cubiertos
- [x] Nodos publican eventos estructurados con código de severidad y cuerpo descriptivo
- [x] Despacho exclusivo a los observadores registrados para ese tipo
- [x] Estructuras indexadas por tipo de alerta
- [x] Robusto ante excepciones de observadores individuales
