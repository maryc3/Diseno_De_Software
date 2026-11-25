# 4.E.2 – Ejercicio 1: Notificador de Clic en Botón de Interfaz

**Objetivo:** estructura básica del Observer (uno-a-muchos entre sujeto y observadores).

## Archivos

| Archivo | Rol |
|---|---|
| `EventoClic` | *Record* con el id del botón y el número de clic. |
| `OyenteClic` | **Interfaz Observer**, marcada `@FunctionalInterface`. |
| `Boton` | **Sujeto.** `registrar`, `quitar` y `simularClic()`. |
| `Ventana` | **No implementa la interfaz**: tiene un método `alClicGuardar(EventoClic)` que se registra como *referencia a método*. |
| `RegistroClics` | Oyente como clase tradicional. |
| `Main` | Registra los tres tipos de oyente. |

## Por qué está hecho así
- El enunciado habla de "**registrar funciones o manejadores**": por eso la interfaz tiene **un solo método abstracto** y se pueden registrar clases, lambdas o `objeto::metodo`. Así funcionan los `ActionListener` de Swing/JavaFX.
- El botón **no conoce la ventana**: solo guarda objetos `OyenteClic`. La ventana se entera del clic sin que el botón sepa nada de ella (restricción técnica 1).
- Los oyentes se guardan en variables para poder **quitarlos** después (si se pasara una lambda "al vuelo" no habría referencia para quitarla).

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E2/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
--- Clic 1 (3 oyentes) ---
   [Ventana 'Editor de texto'] Boton 'btnGuardar' pulsado: guardando documento (guardado #1)
   [Lambda] Se detecto el clic #1
   [RegistroClics] Clic numero 1 en btnGuardar
--- Clic 2 (3 oyentes) ---
   [Ventana 'Editor de texto'] Boton 'btnGuardar' pulsado: guardando documento (guardado #2)
   [Lambda] Se detecto el clic #2
   [RegistroClics] Clic numero 2 en btnGuardar
--- Se quita el oyente lambda ---
--- Clic 3 (2 oyentes) ---
   [Ventana 'Editor de texto'] Boton 'btnGuardar' pulsado: guardando documento (guardado #3)
   [RegistroClics] Clic numero 3 en btnGuardar
```

## Requisitos cubiertos
- [x] Registrar funciones/manejadores interesados en el clic
- [x] Notificar a todos los oyentes en cada clic simulado
- [x] Colección interna genérica, sin acoplarse a ventanas concretas
- [x] Interfaz común con el método de recepción del evento
