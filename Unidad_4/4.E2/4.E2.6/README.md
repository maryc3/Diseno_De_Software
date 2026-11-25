# 4.E.2 – Ejercicio 6: Panel Financiero de Variación de Divisas

**Objetivo:** propagar datos numéricos frecuentes mediante un punto centralizado de notificación.

## Archivos

| Archivo | Rol |
|---|---|
| `CotizacionDivisa` | *Record*: divisa, valor y variación porcentual. |
| `ObservadorDivisas` | **Interfaz Observer.** |
| `MercadoDivisas` | **Sujeto central.** Maneja varias divisas (USD, EUR…) y recuerda el último valor de cada una. |
| `PanelTendencia` | Observador: indica si sube, baja o se mantiene. |
| `TablaConversion` | Observador: tabla con el último valor de cada divisa en pesos. |
| `AlarmaLimite` | Observador de un solo disparo: avisa al superar un límite y se desvincula sola. |
| `Main` | Cotizaciones de USD y EUR. |

## Por qué está hecho así
- **Un solo punto de notificación** para todas las divisas: el mercado guarda un `Map<divisa, último valor>` y calcula la variación contra el ciclo anterior.
- Se envía **un objeto `CotizacionDivisa`** a todos los paneles (restricción técnica 1): no se copian datos por panel.
- **Modificaciones simultáneas durante el envío** (restricción técnica 2): `AlarmaLimite` se desvincula **dentro** de la notificación. Con `CopyOnWriteArrayList` eso es seguro (con `ArrayList` fallaría).
- Los valores son **inventados** para la demostración.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E2/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
--- Primera cotizacion de cada divisa (variacion 0) ---
   [Tendencia] USD IGUAL +0.00%
   [Tabla] 1 USD = 1200.00 ARS
   [Tendencia] EUR IGUAL +0.00%
   [Tabla] 1 EUR = 1300.00 ARS | 1 USD = 1200.00 ARS
--- USD sube ---
   [Tendencia] USD SUBE +2.50%
   [Tabla] 1 EUR = 1300.00 ARS | 1 USD = 1230.00 ARS
--- EUR baja ---
   [Tendencia] EUR BAJA -1.54%
   [Tabla] 1 EUR = 1280.00 ARS | 1 USD = 1230.00 ARS
--- USD supera el limite: la alarma se dispara y se desvincula sola ---
   [Tendencia] USD SUBE +2.44%
   [Tabla] 1 EUR = 1280.00 ARS | 1 USD = 1260.00 ARS
   [ALARMA] USD supero el limite de 1250.00 (valor 1260.00). Alarma desactivada.
--- USD vuelve a subir: la alarma ya no existe ---
   [Tendencia] USD SUBE +2.38%
   [Tabla] 1 EUR = 1280.00 ARS | 1 USD = 1290.00 ARS
```
La primera cotización de cada divisa tiene variación 0 porque todavía no hay valor anterior.

## Requisitos cubiertos
- [x] Actualizar la cotización de una divisa y calcular su diferencia porcentual
- [x] Enviar la nueva estructura de datos a todos los paneles de inmediato
- [x] Paneles reciben el objeto de cotización
- [x] Lista de suscriptores a prueba de modificaciones durante la notificación
