# 2.E1.01 - Gestor de configuración (Singleton)

## Descripción

Este ejercicio implementa un gestor centralizado de configuración utilizando el patrón de diseño **Singleton**. El objetivo es garantizar que exista una única instancia de `ConfigurationManager` durante la ejecución del programa, de manera que distintos componentes puedan consultar los mismos parámetros de configuración.

La configuración se obtiene desde un archivo llamado `config.properties`. Si el archivo no existe, el programa lo crea automáticamente con valores predeterminados. Además, se comprueba que la instancia sea compartida incluso cuando se solicita desde varios hilos.

## Objetivos

- Comprender el propósito del patrón Singleton.
- Restringir la creación de objetos mediante un constructor privado.
- Proporcionar un punto de acceso global a una única instancia.
- Compartir información de configuración entre diferentes componentes.
- Evitar que el archivo de configuración se cargue más de una vez.
- Comprobar el comportamiento del Singleton en un escenario con varios hilos.

## Estructura del ejercicio

```text
2.E1.01/
│
├── README.md
│
└── src/
    ├── Ejercicio_1.java
    ├── ConfigurationManager.java
    ├── ComponenteBaseDatos.java
    └── ComponenteCorreo.java
```

## Descripción de las clases

### Ejercicio_1

Contiene el método `main`, desde donde se ejecutan las distintas pruebas del ejercicio. Solicita la instancia del gestor, carga la configuración y utiliza los componentes de base de datos y correo.

También realiza las siguientes verificaciones:

- Comprueba que dos solicitudes devuelvan la misma instancia.
- Intenta cargar nuevamente el archivo para verificar que la segunda carga sea ignorada.
- Consulta una clave inexistente, primero sin valor alternativo y luego con uno predeterminado.
- Crea diez hilos para comprobar que todos reciban la misma instancia.
- Explica que el constructor privado impide crear instancias desde otras clases.

### ConfigurationManager

Es la clase que implementa el patrón Singleton. Mantiene una única instancia mediante el atributo estático `instancia`, cuyo acceso se realiza a través del método `obtenerInstancia()`.

Su constructor es privado, por lo que otras clases no pueden crear objetos directamente con `new`. El método `cargar()` lee el archivo de propiedades una sola vez. Si el archivo no existe, lo crea con valores predeterminados.

Los métodos `obtener()` permiten consultar valores de configuración. Antes de devolverlos, se verifica que la configuración ya haya sido cargada.

### ComponenteBaseDatos

Representa un componente que necesita consultar los parámetros de conexión a una base de datos. Obtiene la instancia compartida de `ConfigurationManager` y consulta las claves `db.url` y `db.user`.

### ComponenteCorreo

Representa un componente que necesita conocer el servidor de correo. También utiliza la instancia única de `ConfigurationManager` y consulta la clave `mail.host`, indicando `localhost` como valor alternativo si no existe.

## Archivo de configuración

El programa utiliza el archivo `config.properties`. Si no lo encuentra en el directorio de trabajo desde el que se ejecuta, lo genera automáticamente con estos valores:

```properties
db.url=jdbc:mysql://localhost:3306/miapp
db.user=admin
mail.host=smtp.ejemplo.com
app.nombre=MiAplicacion
```

## Funcionamiento

Al ejecutar el programa se realizan las pruebas en este orden:

1. Se obtiene la instancia única y se carga el archivo de configuración.
2. Los componentes de base de datos y correo consultan los parámetros necesarios.
3. Se solicitan dos referencias al Singleton y se comprueba que sean el mismo objeto.
4. Se vuelve a llamar a `cargar()` para verificar que no se repita la lectura.
5. Se consulta una clave inexistente para observar el resultado con y sin valor predeterminado.
6. Se crean diez hilos y se comprueba que todos obtengan la misma instancia.
7. Se recuerda que el constructor privado impide instanciar la clase desde otras clases.

## Conceptos aplicados

- **Singleton:** patrón creacional que garantiza una única instancia de una clase y ofrece un punto de acceso global a ella.
- **Constructor privado:** restringe la creación directa de objetos desde otras clases.
- **Atributo estático:** permite conservar la referencia compartida a la instancia.
- **Sincronización:** los métodos sincronizados protegen la creación de la instancia y la carga del archivo ante accesos concurrentes.
- **Properties:** permite almacenar y consultar pares de clave y valor desde un archivo de configuración.
- **Hilos:** permiten comprobar que las solicitudes concurrentes reciben la misma instancia.

## Ejecución

El punto de entrada del programa es la clase `Ejercicio_1`. Se debe ejecutar su método `main) desde el entorno de desarrollo.

No es necesario crear manualmente `config.properties`: si no existe en el directorio de trabajo, el programa lo genera con los valores predeterminados.

## Resultado esperado

En la consola se muestran las etapas de prueba. Las dos referencias al gestor deben corresponder a la misma instancia y la prueba con diez hilos debe finalizar indicando:

```text
Resultado: CORRECTO (todos obtuvieron la misma instancia)
```

Los componentes también deben mostrar los parámetros de configuración utilizados para la base de datos y el servidor de correo.

## Conclusión

El ejercicio demuestra cómo el patrón Singleton permite centralizar la configuración de una aplicación y compartirla entre diferentes componentes sin crear múltiples gestores. La separación de las clases en archivos independientes facilita la lectura, el mantenimiento y la comprensión de las responsabilidades de cada una.
