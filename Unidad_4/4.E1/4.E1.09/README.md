# 4.E.1 – Ejercicio 9: Motor Asíncrono de Eventos para Monitoreo de Servidores

**Objetivo:** combinar Observer con un **bus de eventos centralizado**, tipos de mensajes y control de suscripciones bajo concurrencia.

## Archivos

| Archivo | Rol |
|---|---|
| `TipoAlerta` | Enum: `CPU_AL_90`, `MEMORIA_INSUFICIENTE`, `FALLO_DE_DISCO`. |
| `Severidad` | Enum: `INFO`, `ADVERTENCIA`, `CRITICA`. |
| `EventoSistema` | *Record* estructurado: nodo de origen, tipo, severidad y cuerpo descriptivo. |
| `ObservadorEvento` | **Interfaz Observer** (con un método `nombre()` por defecto para los mensajes de error). |
| `BusEventos` | **El bus.** Mapa `TipoAlerta → lista de observadores`; `publicar` (síncrono), `publicarAsincrono` (pool de hilos) y `cerrar`. |
| `MicroservicioEscalado`, `MicroservicioCache`, `MicroservicioRespaldo` | Microservicios que escuchan **un** tipo. |
| `MicroservicioNotificaciones` | Escucha **los tres** tipos y está diseñado para **fallar** con memoria crítica. |
| `ContadorEventos` | Observador silencioso con `AtomicInteger`, para la prueba de concurrencia. |

## Por qué está hecho así
- **Estructura indexada por tipo** (restricción técnica 1): un `ConcurrentHashMap<TipoAlerta, CopyOnWriteArrayList<...>>`. Para despachar solo se busca la lista del tipo, sin recorrer observadores que no interesan (despacho selectivo).
- **Robusto ante excepciones** (restricción técnica 2): cada llamada a un observador va en su **propio `try/catch`**. Si falla, se informa y se continúa con los demás. En la demo, `MicroservicioNotificaciones` está registrado **antes** que `MicroservicioCache` en MEMORIA para demostrar que el fallo del primero no impide al segundo.
- **Concurrencia:** el mapa es concurrente, las listas son `CopyOnWriteArrayList` y el contador usa `AtomicInteger`. La prueba envía 200 eventos desde un pool de 4 hilos y **no se pierde ninguno**.
- La prueba de concurrencia usa un **bus aparte** con solo el contador, para no inundar la consola de mensajes.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E1/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
=== 1) Despacho selectivo por tipo ===
   [Escalado] Levantando una instancia adicional por: CPU sostenida al 92%
   [Notificaciones] Mail enviado: CPU_AL_90 en nodo-A
   [Respaldo] Backup de emergencia iniciado en nodo-B
   [Notificaciones] Mail enviado: FALLO_DE_DISCO en nodo-B
=== 2) Un observador falla y los demas igual reciben el evento ===
   [BUS] Fallo en MicroservicioNotificaciones: el servidor SMTP no responde (se continua con los demas)
   [Cache] Liberando cache en nodo-C (Memoria libre < 2%)
=== 3) Concurrencia: 200 eventos publicados desde hilos del bus ===
Eventos recibidos por el contador: 200 (esperado: 200)
```
Puntos clave: (1) el evento de CPU **no** llega a Respaldo ni a Cache; (2) aunque Notificaciones falló, **Cache sí recibió** el evento; (3) el contador llega **exactamente a 200**.

## Requisitos cubiertos
- [x] Publicar eventos con código de severidad y cuerpo descriptivo
- [x] Despachar solo a los registrados para ese tipo
- [x] Estructuras indexadas por tipo de alerta
- [x] Robusto ante excepciones de observadores individuales
