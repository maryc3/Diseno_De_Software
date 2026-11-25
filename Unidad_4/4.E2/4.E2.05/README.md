# 4.E.2 - Ejercicio 5: Sistema de Automatización según la Luz Solar

## Descripción

Este ejercicio consiste en desarrollar un sistema de automatización para una vivienda que responde a los cambios en la intensidad de la luz solar.

La vivienda cuenta con sensores ubicados en diferentes fachadas, que registran la cantidad de luz recibida. Cuando se realiza una nueva medición, los sensores notifican a los dispositivos vinculados para que ajusten su funcionamiento.

Entre los dispositivos se encuentran las persianas motorizadas, las luces interiores y las cortinas térmicas.

## Objetivo

Aplicar el patrón de diseño Observer para permitir que diferentes dispositivos reciban información de los sensores de luz solar y reaccionen automáticamente ante cada medición, sin que los sensores dependan directamente de los dispositivos.

## Aplicación del patrón Observer

En esta implementación, cada sensor de luz solar funciona como sujeto observable y mantiene una lista de los dispositivos interesados en recibir sus mediciones.

Los dispositivos implementan una interfaz común llamada `ObservadorLuz`. Cuando un sensor registra una nueva medición, crea un objeto con la información de la zona y la intensidad de luz en lux, y lo envía a todos sus observadores.

Esto permite que un mismo dispositivo pueda recibir información de varios sensores y que se puedan incorporar nuevos dispositivos sin modificar el código de los sensores.

## Estructura del proyecto

El proyecto está compuesto por las siguientes clases:

- **SensorLuzSolar:** representa el sensor ubicado en una fachada. Permite suscribir y desuscribir dispositivos, realizar mediciones y notificar los cambios.
- **ObservadorLuz:** define la interfaz que deben implementar los dispositivos que reciben las mediciones de luz solar.
- **LecturaLuz:** representa una medición y contiene la zona de la vivienda y la intensidad de luz registrada en lux.
- **PersianasMotorizadas:** ajusta el porcentaje de cierre de las persianas según la intensidad de luz recibida.
- **LucesInteriores:** calcula la intensidad de las luces interiores. Cuanta más luz solar ingresa, menor es la intensidad necesaria, con un mínimo del 10 %.
- **CortinasTermicas:** representa un dispositivo adicional que despliega las cortinas cuando la intensidad alcanza los 60.000 lux y las recoge cuando está por debajo de ese valor.
- **Main:** contiene el método principal y realiza una simulación del funcionamiento de los sensores y dispositivos.

## Funcionamiento

Al iniciar el programa, se crean dos sensores de luz solar: uno para la fachada norte y otro para la fachada sur.

Luego se crean las persianas motorizadas y las luces interiores. Ambos dispositivos se suscriben a los dos sensores, por lo que reciben las mediciones de ambas fachadas.

Durante la ejecución se realizan las siguientes pruebas:

1. **Luz moderada en la fachada norte:** el sensor registra 35.000 lux. Las persianas se cierran al 50 % y las luces interiores ajustan su intensidad al 65 %.
2. **Sol fuerte en la fachada sur:** el sensor registra 80.000 lux. Las persianas se cierran completamente y las luces interiores reducen su intensidad al 20 %.
3. **Incorporación de un nuevo dispositivo:** se agrega un observador de tipo `CortinasTermicas` al sensor de la fachada sur, sin modificar la clase del sensor ni los demás dispositivos.
4. **Nueva medición de sol fuerte:** el sensor registra 85.000 lux. Las persianas permanecen cerradas, las luces ajustan su intensidad y las cortinas térmicas se despliegan.
5. **Atardecer en la fachada sur:** el sensor registra 5.000 lux. Las persianas se abren, las luces interiores aumentan su intensidad y las cortinas térmicas se recogen.

## Resultado esperado

El programa muestra cómo los dispositivos reaccionan automáticamente ante las mediciones de los sensores.

También permite comprobar que un mismo dispositivo puede estar suscrito a varios sensores y que es posible incorporar nuevos observadores sin modificar el funcionamiento de los existentes.

## Captura de pantalla

![Resultado de la ejecución del ejercicio](Compilado.4.E2.05.png)