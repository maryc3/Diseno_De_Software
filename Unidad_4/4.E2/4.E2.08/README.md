# 4.E.2 – Ejercicio 8: Refactorización de Código Acoplado hacia Bus de Eventos

**Objetivo:** refactorizar un diseño acoplado a un Observer basado en interfaces abstractas e inversión de control.

> El enunciado de este ejercicio es el mismo que el 8 de 4.E.1. Para que no sea una copia, esta versión usa un **bus de eventos genérico** (`BusEventos<E>`) como intermediario real, en vez de que el producto sea el sujeto directamente. Cumple los mismos requisitos.

## Archivos

**Carpeta `antes/` (código heredado):** `ProductoAcoplado`, `FacturacionAcoplada`, `EstadisticasAcopladas` — el producto crea y llama directamente a los módulos.

**Carpeta principal (refactorizado):**

| Archivo | Rol |
|---|---|
| `StockActualizado` | *Record*: el evento que viaja por el bus. |
| `ObservadorEvento<E>` | **Abstracción genérica**: cualquier módulo que escuche eventos de tipo `E`. |
| `BusEventos<E>` | **Bus de eventos genérico:** `suscribir`, `desuscribir`, `publicar`. |
| `Producto` | Solo gestiona su stock y **publica** en el bus que recibe por constructor. |
| `ModuloFacturacion`, `ModuloEstadisticas` | Observadores independientes. |
| `ModuloAlertas` | Observador **nuevo**, agregado después. |
| `Main` | Raíz de composición: arma bus, módulos y producto. |

## Por qué está hecho así
- **Inversión de control:** `Producto` recibe el bus **inyectado** (no hace `new`), y depende solo de `BusEventos<StockActualizado>`. Los módulos dependen solo de `ObservadorEvento`. Nadie depende de clases concretas (restricción técnica 2).
- **Responsabilidad única:** `Producto` gestiona su stock y publica un evento; **no sabe quién escucha** (restricción técnica 1).
- **Extensibilidad:** `ModuloAlertas` se suma en `Main` sin tocar `Producto` ni el bus.
- La interfaz es genérica (`<E>`): el mismo bus serviría para otros tipos de eventos.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E2/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
=========== ANTES (acoplado) ===========
(ANTES) Monitor: vendidas 2, stock 8
   [Facturacion] Se factura 2 x Monitor
   [Estadisticas] Se contabilizan 2 x Monitor

=========== DESPUES (bus de eventos) ===========
(DESPUES) Monitor: stock 10 -> 8
   [Facturacion] Se emite factura por 2 x Monitor
   [Estadisticas] Unidades vendidas en total: 2
(DESPUES) Monitor: stock 8 -> 5
   [Facturacion] Se emite factura por 3 x Monitor
   [Estadisticas] Unidades vendidas en total: 5
--- Se suma un modulo nuevo SIN modificar Producto ---
(DESPUES) Monitor: stock 5 -> 2
   [Facturacion] Se emite factura por 3 x Monitor
   [Estadisticas] Unidades vendidas en total: 8
   [Alertas] Stock critico de Monitor: quedan 2
```

## Requisitos cubiertos
- [x] Sin referencias directas ni llamadas fijas a servicios dentro de la lógica del inventario
- [x] Estructura de eventos: producto como sujeto observable, módulos como observadores independientes
- [x] Inventario solo gestiona su stock y notifica a una lista abstracta
- [x] Ninguna clase consumidora conoce los módulos concretos
