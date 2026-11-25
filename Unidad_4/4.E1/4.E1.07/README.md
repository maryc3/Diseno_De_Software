# 4.E.1 – Ejercicio 7: Sistema de Suscripción a Canales de Noticias Digitales

**Objetivo:** un Observer con suscripciones **filtradas y selectivas** bajo múltiples tópicos.

## Archivos

| Archivo | Rol |
|---|---|
| `Categoria` | Enum: `TECNOLOGIA`, `DEPORTES`, `ECONOMIA`. |
| `Noticia` | *Record* con los metadatos del artículo (categoría y título). |
| `ObservadorNoticias` | **Interfaz unificada** de los clientes. |
| `PortalNoticias` | **Sujeto / gestor de eventos.** Un `EnumMap<Categoria, lista de observadores>`. |
| `Lector` | Observador: guarda los avisos en su bandeja. |
| `Main` | Tres lectores con distintas suscripciones. |

## Por qué está hecho así
- Una lista por categoría: al publicar se recorre **solo** la lista de esa categoría, así que un lector nunca recibe lo que no pidió.
- `EnumMap` es la estructura natural para indexar por enum (rápida y ordenada).
- `suscribir` y `cancelar` reciben la categoría y el lector, cubriendo el requisito de alta/baja por categoría.

> **Nota sobre el enunciado:** en el PDF, el último punto de este ejercicio dice "gestor de eventos indexados por identificador de emisor" y "metadatos del video publicado" (parece texto copiado del ejercicio de video streaming). Lo interpreté como el equivalente para noticias: **indexado por categoría** y **metadatos del artículo**.

## Cómo ejecutarlo
Clic derecho sobre `src/unidad4/E1/Main.java` → **Run 'Main.main()'**.

## Qué tiene que verse
```
>> Redactor publica en TECNOLOGIA: Nuevo procesador de 2 nm
   Aviso para Ana: Nuevo procesador de 2 nm
   Aviso para Beto: Nuevo procesador de 2 nm
>> Redactor publica en DEPORTES: Final de la copa este domingo
   Aviso para Ana: Final de la copa este domingo
   Aviso para Carla: Final de la copa este domingo
>> Redactor publica en ECONOMIA: El dolar cierra estable
   Aviso para Beto: El dolar cierra estable
--- Ana cancela su suscripcion a TECNOLOGIA ---
>> Redactor publica en TECNOLOGIA: Lanzan un nuevo lenguaje de programacion
   Aviso para Beto: Lanzan un nuevo lenguaje de programacion
--- Bandejas finales ---
Bandeja de Ana (2 avisos): [[TECNOLOGIA] Nuevo procesador de 2 nm, [DEPORTES] Final de la copa este domingo]
Bandeja de Beto (3 avisos): [[TECNOLOGIA] Nuevo procesador de 2 nm, [ECONOMIA] El dolar cierra estable, [TECNOLOGIA] Lanzan un nuevo lenguaje de programacion]
Bandeja de Carla (1 avisos): [[DEPORTES] Final de la copa este domingo]
```
Cada noticia llega **solo** a los suscritos a su categoría, y tras cancelar, Ana ya no recibe tecnología.

## Requisitos cubiertos
- [x] Suscribirse / cancelar por categoría
- [x] Solo reciben la noticia los suscritos a esa categoría
- [x] Gestor de eventos indexado
- [x] Clientes con interfaz unificada
