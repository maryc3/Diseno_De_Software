# 4.E.2 – Ejercicio 3: Marcador de Puntuación para Videojuego Sencillo

**Objetivo:** canalizar eventos de cambio de puntuación hacia varios receptores independientes.

## Archivos

| Archivo | Rol |
|---|---|
| `ObservadorPuntuacion` | **Interfaz Observer.** Recibe total, puntos ganados y motivo. |
| `MarcadorJuego` | **Sujeto** y lógica de puntuación. `sumarPuntos(puntos, motivo)`. |
| `PantallaJuego` | Observador visual: muestra los puntos en pantalla. |
| `RegistroRecords` | Observador de persistencia: guarda el mejor puntaje y avisa de nuevos récords. |
| `Main` | Juega una partida corta y oculta la pantalla a mitad. |

## Por qué está hecho así
- **La lógica del juego no contiene llamadas a pantalla ni a récords** (restricción técnica 1): `MarcadorJuego` solo suma y notifica. No sabe que existen `PantallaJuego` ni `RegistroRecords`.
- **Suscripción dinámica** (restricción técnica 2): en `Main` se desuscribe la pantalla en plena partida (como si se ocultara el HUD) y el registro de récords sigue funcionando.
- Se valida que los puntos sumados sean positivos.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E2/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
--- Nivel 1 (record a superar: 150) ---
   [Pantalla] +50 por destruir obstaculo  |  PUNTOS: 50
   [Pantalla] +100 por recolectar objeto  |  PUNTOS: 150
   [Pantalla] +75 por destruir obstaculo  |  PUNTOS: 225
   [Records] NUEVO RECORD: 225 puntos
--- Se oculta la pantalla (desuscripcion dinamica); sigue el registro ---
   [Records] NUEVO RECORD: 250 puntos
Record final guardado: 250
```
Con 150 puntos **no** hay récord (hay que superarlo, no igualarlo). Tras desuscribir la pantalla, solo responde el registro de récords.

## Requisitos cubiertos
- [x] Disparar actualización cada vez que el jugador suma puntos
- [x] Enviar el valor a la pantalla y al sistema de récords a la vez
- [x] Lógica de puntuación sin llamadas directas a render/persistencia
- [x] Suscripción y desuscripción dinámicas en ejecución
