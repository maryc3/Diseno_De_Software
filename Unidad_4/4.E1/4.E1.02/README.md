# 4.E.1 – Ejercicio 2: Sincronizador de Interfaz para Reproductor Multimedia

**Objetivo:** desacoplar el estado central de un reproductor de sus varias representaciones visuales.

## Archivos

| Archivo | Rol |
|---|---|
| `EstadoReproduccion` | Enum: `REPRODUCIENDO`, `PAUSADO`, `DETENIDO`. |
| `ObservadorReproductor` | **Interfaz Observer.** `actualizar(estado, titulo)`. |
| `MotorReproduccion` | **Sujeto.** Guarda estado y título; encapsula la lista de observadores. |
| `BarraInferiorControl`, `VentanaMiniatura`, `WidgetPantallaPrincipal` | **Tres observadores** (las tres vistas), cada una con su formato. |
| `Main` | Simula usar el reproductor. |

## Por qué está hecho así
- **Modelo push:** el motor *empuja* estado y título a las vistas. Ninguna vista hace *polling*: solo reaccionan cuando les llega la notificación (restricción técnica 1).
- La lista es `CopyOnWriteArrayList` y es **privada**: la gestión "segura" de la lista queda encapsulada en el motor (restricción técnica 2). Se puede suscribir/desuscribir aun durante una notificación.
- `reproducir(titulo)` cambia canción **y** estado con **una sola** notificación (si se hicieran dos llamadas, las vistas verían un estado intermedio inconsistente).

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E1/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
--- Se elige una cancion ---
[Barra inferior]      >  Bohemian Rhapsody
[Miniatura flotante]  Cancion: Bohemian Rhapsody (REPRODUCIENDO)
[Widget principal]    Ahora suena: Bohemian Rhapsody
--- Pausa ---
[Barra inferior]      ||  Bohemian Rhapsody
[Miniatura flotante]  Cancion: Bohemian Rhapsody (PAUSADO)
[Widget principal]    Reproduccion pausado
--- Cambio de cancion ---
[Barra inferior]      >  Hotel California
[Miniatura flotante]  Cancion: Hotel California (REPRODUCIENDO)
[Widget principal]    Ahora suena: Hotel California
--- Detener ---
[Barra inferior]      []  Hotel California
[Miniatura flotante]  Cancion: Hotel California (DETENIDO)
[Widget principal]    Reproduccion detenido
```
Cada cambio aparece **en las tres vistas a la vez**.

## Requisitos cubiertos
- [x] Cambiar estado y título de la canción
- [x] Actualización automática y simultánea de todas las vistas
- [x] Sin polling
- [x] Gestión de la lista de observadores encapsulada y segura
