# 4.E.1 – Ejercicio 8: Refactorización de Código Acoplado hacia Bus de Eventos

**Objetivo:** refactorizar un diseño con dependencias directas hacia un Observer basado en interfaces abstractas e **inversión de control**.

## Archivos

**Carpeta `antes/` (el código heredado, mal diseñado):**

| Archivo | Rol |
|---|---|
| `ProductoAcoplado` | Crea con `new` y llama **directamente** a facturación y estadísticas. |
| `ModuloFacturacionAcoplado`, `ModuloEstadisticasAcoplado` | Módulos concretos de los que depende. |

**Carpeta principal (el código refactorizado):**

| Archivo | Rol |
|---|---|
| `CambioStock` | *Record* con producto, stock anterior y nuevo. |
| `ObservadorInventario` | **Abstracción** de la que dependen todos. |
| `Producto` | **Sujeto.** Solo gestiona su stock y notifica a una lista abstracta. |
| `ModuloFacturacion`, `ModuloEstadisticas` | Observadores independientes. |
| `Main` | Muestra el "antes" y el "después", y agrega un tercer módulo con una lambda. |

## Qué cambió y por qué
| Antes | Después |
|---|---|
| `Producto` hace `new ModuloFacturacion()` | `Producto` no conoce ningún módulo; solo `ObservadorInventario` |
| Llamadas fijas a cada módulo | Un bucle que notifica a la lista abstracta |
| Agregar un módulo = editar `Producto` | Agregar un módulo = una línea de `suscribir(...)` en `Main` |
| Cambiar un receptor puede romper `Producto` | Los receptores cambian sin tocar `Producto` |

- **Inversión de control (DIP):** antes, la clase de alto nivel (`Producto`) dependía de clases concretas. Ahora **ambos** dependen de una interfaz.
- `Main` es la **raíz de composición**: el único lugar que conoce las clases concretas y las conecta. Ninguna clase de negocio las conoce (restricción técnica 2).
- Como `ObservadorInventario` tiene un único método, se pudo agregar un módulo extra con una **lambda**, sin crear clase.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E1/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
=========== ANTES (acoplado) ===========
(ANTES) Venta de 2 x Teclado, stock: 18
   [Facturacion] Movimiento de 2 unidades de Teclado
   [Estadisticas] Se registra movimiento de 2 unidades de Teclado

=========== DESPUES (Observer) ===========
(DESPUES) Teclado: stock 20 -> 18
   [Facturacion] Se factura la venta de 2 x Teclado
   [Estadisticas] Total vendido acumulado: 2
(DESPUES) Teclado: stock 18 -> 15
   [Facturacion] Se factura la venta de 3 x Teclado
   [Estadisticas] Total vendido acumulado: 5
--- Se agrega un modulo nuevo SIN tocar la clase Producto ---
(DESPUES) Teclado: stock 15 -> 25
   [Facturacion] Ingreso de mercaderia: 10 x Teclado
   [Estadisticas] Total vendido acumulado: 5
   [Alertas (lambda)] Cambio detectado en Teclado
```

## Requisitos cubiertos
- [x] Eliminadas las referencias directas a servicios dentro del inventario
- [x] Producto como sujeto, módulos como observadores independientes
- [x] `Producto` solo gestiona su stock y notifica a una lista abstracta
- [x] Ninguna clase de negocio conoce los módulos concretos
