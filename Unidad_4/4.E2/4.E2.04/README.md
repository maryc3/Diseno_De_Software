# 4.E.2 – Ejercicio 4: Sistema de Alertas de Precio en Lista de Deseos

**Objetivo:** un Observer con estado interno dinámico, reglas condicionales y notificaciones selectivas.

## Archivos

| Archivo | Rol |
|---|---|
| `CambioPrecio` | *Record* con precio anterior y nuevo, y el método `porcentajeRebaja()`. |
| `ObservadorPrecio` | **Interfaz Observer.** |
| `Producto` | **Sujeto.** Precio privado; `actualizarPrecio(nuevo)` valida, guarda y decide si notifica. |
| `NotificadorCorreo` | Canal: correo electrónico simulado. |
| `NotificadorPanelWeb` | Canal: aviso en el panel; destaca como "OFERTA DESTACADA" si la rebaja es ≥ 20%. |
| `Main` | Dos productos con canales distintos. |

## Por qué está hecho así
- **Regla condicional:** solo se notifica cuando el precio **baja** (`nuevo < anterior`). Si sube, el catálogo se actualiza pero no se avisa a nadie.
- **Encapsulamiento:** el precio es `private`, sin setter (restricción técnica 1). La única vía de cambio es `actualizarPrecio`, que además rechaza precios ≤ 0.
- **Los observadores procesan según el porcentaje de rebaja** (restricción técnica 2): `CambioPrecio` calcula el porcentaje y el panel web decide si es oferta destacada; el correo lo muestra tal cual.
- **Selectivo:** cada `Producto` tiene **sus** canales. Los auriculares tienen correo + panel; el teclado solo el panel.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E2/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
--- El precio SUBE: no se notifica ---
Catalogo: Auriculares X200 pasa de 100.00 a 110.00
--- Rebaja pequena ---
Catalogo: Auriculares X200 pasa de 110.00 a 104.50
   [Correo a ana@ejemplo.com] 'Auriculares X200' bajo de 110.00 a 104.50 (5.0% de rebaja)
   [Panel web] Aviso de rebaja: Auriculares X200 -5.0%
--- Rebaja grande ---
Catalogo: Auriculares X200 pasa de 104.50 a 75.00
   [Correo a ana@ejemplo.com] 'Auriculares X200' bajo de 104.50 a 75.00 (28.2% de rebaja)
   [Panel web] OFERTA DESTACADA: Auriculares X200 -28.2%
--- Otro articulo: solo esta vinculado el panel web ---
Catalogo: Teclado mecanico pasa de 80.00 a 76.00
   [Panel web] Aviso de rebaja: Teclado mecanico -5.0%
```

## Requisitos cubiertos
- [x] Modificar el precio del producto en el catálogo
- [x] Notificar solo si el nuevo precio es inferior, y solo a los canales vinculados a ese artículo
- [x] Precio encapsulado y protegido
- [x] Observadores procesan según el porcentaje de rebaja
