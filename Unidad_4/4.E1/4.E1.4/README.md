# 4.E.1 – Ejercicio 4: Sistema de Alertas de Stock Bajo para Comercio Electrónico

**Objetivo:** un Observer con estado interno dinámico, reglas de negocio condicionales y notificaciones selectivas.

## Archivos

| Archivo | Rol |
|---|---|
| `EventoStockBajo` | *Record* con producto, stock actual y umbral: los **datos** con los que cada observador decide qué hacer. |
| `ObservadorStock` | **Interfaz Observer.** |
| `Producto` | **Sujeto.** Stock y umbral privados; `vender(int)` es la única forma de bajar el stock. |
| `DepartamentoCompras` | Observador: genera una orden de reabastecimiento calculada con los datos del evento. |
| `SistemaMarketing` | Observador: si quedan unidades lanza liquidación; si el stock es 0 pausa campañas. |
| `Main` | Dos productos con distintos suscriptores. |

## Por qué está hecho así
- **Regla condicional:** `Producto` solo notifica si `stock < umbral` (después de cada venta).
- **Encapsulamiento:** el stock es `private`, sin `setStock`. Nadie puede "saltearse" la regla modificándolo desde afuera (restricción técnica 1). `getStock()` es solo lectura.
- **Los observadores deciden:** reciben datos, no órdenes. Compras calcula cuánto pedir; Marketing elige entre "liquidación" y "pausar" según el stock recibido (restricción técnica 2).
- **Notificación selectiva:** el *Notebook* tiene Compras + Marketing, el *Mouse* solo Compras; cada producto avisa únicamente a **sus** interesados.
- Si se intenta vender más de lo que hay se lanza una excepción (se captura en `Main`).

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E1/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
=== Notebook (umbral 5) ===
Venta de 3 x Notebook -> stock restante: 7
Venta de 3 x Notebook -> stock restante: 4
   [Compras] Orden de reabastecimiento: 6 unidades de Notebook
   [Marketing] Campana de liquidacion 'ultimas 4 unidades' de Notebook
Venta de 4 x Notebook -> stock restante: 0
   [Compras] Orden de reabastecimiento: 10 unidades de Notebook
   [Marketing] Notebook agotado: se pausan las campanas
Error controlado: Stock insuficiente de Notebook (hay 0, se pidieron 1)
=== Mouse (umbral 3, solo Compras) ===
Venta de 6 x Mouse -> stock restante: 2
   [Compras] Orden de reabastecimiento: 4 unidades de Mouse
```
Con stock 7 **no pasa nada** (7 ≥ 5). Con 4 y con 0 sí hay alertas. Con el Mouse, **solo** responde Compras.

## Requisitos cubiertos
- [x] Disminuir stock con ventas simuladas
- [x] Evaluar el umbral tras cada modificación y notificar solo a los interesados
- [x] Datos protegidos contra modificaciones externas
- [x] Observadores deciden la acción según los datos recibidos
