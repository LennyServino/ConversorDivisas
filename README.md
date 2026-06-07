La aplicación es un convertidor de divisas desarrollado de forma nativa para Android utilizando Kotlin y Jetpack Compose 
para la interfaz gráfica. El proyecto está estructurado bajo el patrón de diseño MVVM (Model-View-ViewModel) para separar 
la lógica de negocio de la vista.

El flujo de la aplicación funciona de la siguiente manera:

- El usuario interactúa con la interfaz ingresando un monto numérico en USD y seleccionando la moneda de destino
  (Quetzales, Lempiras, Córdobas o Pesos Mexicanos) mediante un menú desplegable.

- Al presionar el botón "Convertir", el CurrencyViewModel valida que el campo no esté vacío.

- Se lanza una corrutina (ejecución asíncrona) que utiliza la librería Retrofit para realizar una petición HTTP tipo
  GET a una API externa.

- La respuesta de la API, en formato JSON, es procesada por un convertidor Gson y mapeada automáticamente a una clase
  de datos en Kotlin (CurrencyResponse).

- El ViewModel extrae la tasa de cambio específica, realiza la operación matemática y actualiza las variables de estado (State).

- Jetpack Compose detecta el cambio de estado y recompone la interfaz automáticamente para mostrar la tarjeta con el resultado exacto,
  o en su defecto, un mensaje de error si no hay conexión a internet.


Nombre de la API utilizada:

ExchangeRate-API (Versión de acceso abierto / Open Access).
Enlace de consulta: https://open.er-api.com/v6/latest/USD
