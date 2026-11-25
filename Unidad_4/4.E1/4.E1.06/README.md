# 4.E.1 – Ejercicio 6: Panel de Cotizaciones Bursátiles en Tiempo Real

**Objetivo:** propagar datos numéricos frecuentes desde un punto centralizado de notificación.

## Archivos

| Archivo | Rol |
|---|---|
| `Cotizacion` | *Record* con símbolo, precio y variación porcentual. Se crea **una vez** y se entrega a todos. |
| `ObservadorCotizacion` | **Interfaz Observer.** |
| `ActivoFinanciero` | **Sujeto.** `actualizarPrecio` calcula la variación vs. el valor anterior y notifica. |
| `PanelGrafico` | Observador: arma la serie de precios (línea). |
| `PanelResumen` | Observador: actual, variación, mínimo y máximo. |
| `AlertaVolatilidad` | Observador de **un solo disparo**: se desuscribe a sí mismo al superar el umbral. |
| `Main` | Cuatro cambios de precio. |

## Por qué está hecho así
- **Sin sobrecargar memoria** (restricción técnica 1): se crea un único `Cotizacion` inmutable por actualización y se pasa la misma referencia a todos los paneles; los paneles solo guardan números primitivos.
- **Seguridad ante modificaciones concurrentes** (restricción técnica 2): la lista es `CopyOnWriteArrayList`. `AlertaVolatilidad` se quita de la lista **mientras se está notificando**; con un `ArrayList` normal eso lanzaría `ConcurrentModificationException`. Es la demostración práctica.
- `actualizarPrecio` es `synchronized` para que dos actualizaciones simultáneas no mezclen precio anterior y nuevo.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E1/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
--- Nuevo precio: 102.0 ---
   [Grafico] ACME: 102.00
   [Resumen] ACME | actual 102.00 | var +2.00% | min 102.00 | max 102.00
--- Nuevo precio: 99.0 ---
   [Grafico] ACME: 102.00 -> 99.00
   [Resumen] ACME | actual 99.00 | var -2.94% | min 99.00 | max 102.00
--- Nuevo precio: 110.0 ---
   [Grafico] ACME: 102.00 -> 99.00 -> 110.00
   [Resumen] ACME | actual 110.00 | var +11.11% | min 99.00 | max 110.00
   [ALERTA] Volatilidad alta en ACME: +11.11% (umbral 5.0%). Alerta desactivada.
--- Nuevo precio: 108.0 ---
   [Grafico] ACME: 102.00 -> 99.00 -> 110.00 -> 108.00
   [Resumen] ACME | actual 108.00 | var -1.82% | min 99.00 | max 110.00
```
La alerta salta con +11.11% (supera el 5%) y en el siguiente precio **ya no aparece** porque se desvinculó sola.

## Requisitos cubiertos
- [x] Actualizar precio y calcular variación porcentual
- [x] Enviar el nuevo valor a todos los paneles de inmediato
- [x] Paneles reciben el objeto de cotización
- [x] Gestión de suscriptores segura ante modificaciones durante el envío
