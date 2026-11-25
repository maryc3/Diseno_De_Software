# 4.E.2 – Ejercicio 7: Sistema de Suscripción a Canales de Video Streaming

**Objetivo:** un Observer con suscripciones filtradas y selectivas bajo múltiples canales independientes.

## Archivos

| Archivo | Rol |
|---|---|
| `Video` | *Record* con los **metadatos** (canal, título, duración). |
| `ObservadorSeguidor` | **Interfaz unificada** que recibe los metadatos del video. |
| `PlataformaVideos` | **Gestor de eventos indexados por identificador de canal (emisor).** `Map<String, lista de seguidores>`. |
| `Usuario` | Observador: muestra una alerta emergente. |
| `Main` | Tres usuarios, varios canales. |

## Por qué está hecho así
- **Indexado por id de emisor** (restricción técnica 1): cada canal tiene su propia lista de seguidores; al publicar se busca por `idCanal` y solo se notifica a esos usuarios.
- `computeIfAbsent` crea la lista de un canal la primera vez que alguien lo sigue; todo concurrente (`ConcurrentHashMap` + `CopyOnWriteArrayList`).
- Si un canal no tiene seguidores, se avisa en consola y no pasa nada más.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E2/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
>> canal-cocina sube: 'Empanadas criollas' (12:34)
   [Alerta emergente para Lucia] Nuevo video de canal-cocina: Empanadas criollas
   [Alerta emergente para Tomas] Nuevo video de canal-cocina: Empanadas criollas
>> canal-gaming sube: 'Speedrun en 40 minutos' (40:00)
   [Alerta emergente para Tomas] Nuevo video de canal-gaming: Speedrun en 40 minutos
   [Alerta emergente para Valen] Nuevo video de canal-gaming: Speedrun en 40 minutos
>> canal-musica sube: 'Concierto en vivo' (60:00)
   (el canal no tiene seguidores)
--- Tomas cancela canal-cocina ---
>> canal-cocina sube: 'Pizza casera' (10:10)
   [Alerta emergente para Lucia] Nuevo video de canal-cocina: Pizza casera
```
Valen no recibe nada de cocina, Lucia no recibe nada de gaming, y tras cancelar, Tomas ya no recibe cocina.

## Requisitos cubiertos
- [x] Suscribirse y cancelar canales
- [x] Publicar con id de canal; solo reciben los registrados en ese canal
- [x] Gestor de eventos indexado por identificador de emisor
- [x] Clientes con interfaz unificada que recibe los metadatos del video
