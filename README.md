<h1 align="center">📦Microservicios Tienda de Electrodomésticos</h1>
<h2>📖Descripción del proyecto:</h2>
<h3>Este proyecto implementa una arquitectura de microservicios utilizando Java, Spring Boot y Spring Cloud para la gestión de una tienda de electrodomésticos.
El sistema se compone de tres servicios principales:</h3>
<h3>-Servicio de Productos: Gestiona la información de los electrodomésticos disponibles en la tienda, incluyendo codigo del producto, nombre, marca y precio.</h3>
<h3>-Servicio de Carritos: Permite a los usuarios agregar productos a su carrito de compras. Este servicio consume al servicio de productos para validar la existencia y 
  disponibilidad de los artículos.</h3>
<h3>-Servicio de Ventas: Encargado de procesar las ventas finales, consumiendo al servicio de carritos para obtener los productos seleccionados por el cliente y generar la transacción.</h3>
<h3>Los servicios se comunican entre sí mediante REST y están diseñados para ser escalables, independientes y de fácil mantenimiento, aprovechando las capacidades de Spring Cloud para la gestión de configuración, descubrimiento de servicios y balanceo de carga.</h3>

<h2>✨ Características Principales</h2>
<h3>✅ CRUD completo para productos.</h3>
<h3>✅ CRUD completo para carritos.</h3>
<h3>✅ Operaciones para creación y lectura ventas.</h3>
<h3>✅ Validación de datos con Spring Validation.</h3>
<h3>✅ Persistencia de datos con JPA e Hibernate.</h3>
<h3>✅ Configuración con Maven.</h3>
<h3>✅ Configuración con Maven.</h3>

<h2>📌 Endpoints de la API</h2>
<h3>📍 Clientes</h3>
<table>
  <tr>
    <th>Método</th>
    <th>Endpoint</th>
    <th>Descripción</th>
  </tr>
   <tr>
    <td>POST</td>
    <td>/cliente/crear</td>
    <td>Registrar un nuevo cliente</td>
  </tr>
   <tr>
    <td>GET</td>
    <td>/cliente/mostrar</td>
    <td>Obtener todos los clientes existentes</td>
  </tr>
   <tr>
    <td>GET</td>
    <td>/cliente/mostrar/{id}</td>
    <td>Obener un cliente por su id</td>
  </tr>
   <tr>
    <td>PUT</td>
    <td>/cliente/editar/{id}</td>
    <td>Modificar un cliente existente por su id</td>
  </tr>
   <tr>
    <td>DELETE</td>
    <td>/cliente/eliminar/{id}</td>
    <td>Eliminar un cliente por su id</td>
  </tr>
</table>

<h3>📍 Productos</h3>
<table>
  <tr>
    <th>Método</th>
    <th>Endpoint</th>
    <th>Descripción</th>
  </tr>
   <tr>
    <td>POST</td>
    <td>/producto/agregar</td>
    <td>Registrar un nuevo producto</td>
  </tr>
   <tr>
    <td>GET</td>
    <td>/producto/listar</td>
    <td>Obtener todos los productos existentes</td>
  </tr>
   <tr>
    <td>GET</td>
    <td>/producto/listar/{id}</td>
    <td>Obener un producto por su id</td>
  </tr>
   <tr>
    <td>PUT</td>
    <td>/producto/editar/{id}</td>
    <td>Modificar un producto existente por su id</td>
  </tr>
   <tr>
    <td>DELETE</td>
    <td>/producto/eliminar/{id}</td>
    <td>Eliminar un producto por su id</td>
  </tr>
   <tr>
    <td>GET</td>
    <td>/producto/existencias/{cantidad}</td>
    <td>Obtener productos con existencias menores a cantidad</td>
  </tr>
</table>
<h3>📍 Ventas</h3>
<table>
  <tr>
    <th>Método</th>
    <th>Endpoint</th>
    <th>Descripción</th>
  </tr>
   <tr>
    <td>POST</td>
    <td>/venta/crear</td>
    <td>Registrar una nueva venta</td>
  </tr>
   <tr>
    <td>GET</td>
    <td>/venta/mostrar</td>
    <td>Obtener todas las ventas existentes</td>
  </tr>
   <tr>
    <td>GET</td>
    <td>/venta/mostrar/{id}</td>
    <td>Obener una venta por su id</td>
  </tr>
   <tr>
    <td>PUT</td>
    <td>/venta/editar/{id}</td>
    <td>Modificar una venta existente por su id</td>
  </tr>
   <tr>
    <td>DELETE</td>
    <td>/venta/eliminar/{id}</td>
    <td>Eliminar una venta por su id</td>
  </tr>
   <tr>
    <td>GET</td>
    <td>/venta/productos/{id}</td>
    <td>Obtener los productos de una venta</td>
  </tr>
   <tr>
    <td>GET</td>
    <td>/venta/venta_mayor</td>
    <td>Obtener información sobre la venta con el monto mayor</td>
  </tr>
   <tr>
    <td>GET</td>
    <td>/venta/total/{fecha}</td>
    <td>Obtener información de las ventas de un día</td>
  </tr>
</table>

<h4>Podrás encontrar la colección de requests en el archivo bazar_requests_postman_collection.json,
solo es necesario importarlo en postman</h4>

<h2>✔🛠️Tecnologías utilizadas</h2>
<h3>- Java SE 17</h3>
<h3>- Spring Boot 3</h3>
<h3>- Base de Datos Relacional MySQL 8</h3>
<h3>- Acceso a datos Spring Data JPA Hibernate</h3>
<h3>- Spring Validation</h3>
<h3>- Maven</h3>
<h3>- IDE IntelliJ Idea</h3>

<h2>📌 Requisitos Previos</h2>
<h3>Antes de ejecutar el proyecto, asegúrate de tener instalado:</h3>

<h3>Java 17</h3>

<h3>Maven</h3>

<h3>MySQL</h3>

<h3>Postman o alguna herramienta similar para probar la API</h3>

<h2>🚀 Instalación y Configuración</h2>

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Badchiquipack/bazar_api.git

2. Configurar la base de datos en application.properties:
   ```bash
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=tu_basededatos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_password

3. Puedes reemplazar `tu_usuario`, `tu_password` y `tu_basededatos` con los valores correspondientes. 🚀

<h2>📩 Contacto</h2>
<h3>Si tienes preguntas o sugerencias, contáctame en miguel.arriaga.arellano@gmail.com</h3>

<h2>😃 Autor:</h2>
<h3>Miguel Angel Arriaga Arellano</h3>
