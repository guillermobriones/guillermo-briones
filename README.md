# Order Management — aplicación MVC con Swing

Aplicación Java 21 para buscar pedidos desde `orders.json`, mostrar sus artículos,
descuentos y totales en euros y convertir esos totales a dólares utilizando
el último tipo de cambio EUR/USD publicado por un servicio externo.

## Arquitectura

- **Modelo:** `com.uem.Model` (`Order`, `Article`), junto a los cálculos existentes.
- **Vista:** `com.uem.View.OrderView` (ventana Swing, búsqueda, resultados e icono).
- **Controlador:** `com.uem.Controller.OrderController` (localiza pedidos, solicita la conversión y actualiza la vista).
- **Servicio:** `com.uem.Service.ExchangeRateService` (consulta HTTP del tipo EUR/USD).
- **Recursos:** `src/main/resources/orders.json` y `app.png`.

## Tipo de cambio

Se utiliza [Frankfurter v2](https://frankfurter.dev/), endpoint:
`https://api.frankfurter.dev/v2/rate/eur/usd`.

- No necesita registro ni clave API.
- Al pulsar **Search** para un pedido existente se solicita de nuevo el tipo de cambio.
- Se muestra el valor recibido y la **fecha de publicación** de la cotización.
- No es un precio de mercado en streaming: son tasas de referencia publicadas periódicamente.
- La llamada utiliza `HttpClient.sendAsync` (la interfaz no se bloquea).
- Timeout de conexión de 5 segundos y de petición de 10 segundos.
- Si el servicio falla o no hay Internet, los totales en EUR permanecen visibles y
  se indica que el cálculo en USD no está disponible (no se inventa un tipo fijo).
- Se asume que los importes base contenidos en `orders.json` están expresados en EUR.

## Ejecutar en desarrollo

Se necesita **JDK 21** y **Maven**.

```bash
mvn clean verify
java -jar target/guillermo-2.0.0.jar
```

`mvn clean verify` compila y ejecuta las pruebas. `mvn package` produce un JAR
con las dependencias incluidas (Gson y SLF4J), manifest `Main-Class` y recursos.

## Ejecutable para Windows sin VS Code ni Java instalado

En **Releases**, descarga `OrderManagement-Windows.zip`, descomprímelo
(completo, incluida la carpeta `runtime`) y abre `OrderManagement.exe` con doble clic.
El paquete incorpora su propio entorno Java 21 mediante `jpackage` y utiliza
`packaging/app.ico` como icono. Necesita Internet únicamente para consultar USD.

También se publica `guillermo-2.0.0.jar` como alternativa para equipos con JDK/JRE 21.

## Publicar la versión 2.0.0

Tras subir los cambios a `main` y verificar que las pruebas pasan, crea y sube
la etiqueta `v2.0.0`:

```bash
git tag v2.0.0
git push origin v2.0.0
```

El workflow `.github/workflows/release.yml` genera en GitHub Actions (Windows)
la aplicación independiente, ejecuta pruebas y adjunta el ZIP y el JAR a la release.
La release no se crea hasta que se publique la etiqueta.

## Pruebas manuales recomendadas

1. Buscar `O001` y comprobar artículos, descuentos, totales EUR y USD.
2. Buscar `O005` y comprobar que la búsqueda sigue funcionando.
3. Buscar `O999` y verificar `Order not found.`.
4. Desconectar Internet temporalmente: deben seguir apareciendo los importes EUR,
   junto con un aviso de que no se pudo convertir a USD.
5. Abrir el archivo `OrderManagement.exe` extraído del ZIP en un PC Windows
   sin abrir VS Code ni ejecutar Maven.
