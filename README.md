# ProgramacionProject

|Información|
|:-----|
|Proyecto para la gestión de los clientes de la base de datos classicmodels, propia de Sakila.
|Realizado con Java Swing, para subida de nota de la asignatura de  programación.
|

|Documentación|
|:-----|
|[GitHub Esorcle](https://github.com/Esorcle/ProgramacionProject)|

@autor Estefanía Ortiz clemente. 2020

# Descarga del proyecto
   
1. Terminal a usar:
* En Linux: Desde el propio terminal
* En Windows: Desde Git Bash. (prevía instalación)

2. En la ruta deseada escribir:
   >git clone https://github.com/Esorcle/ProgramacionProject.git

# Configuración de Intellij IDEA
1. Abrir el proyecto en Intellij Idea > File > Open > (ruta del proyecto)clientManager
2. Si no lo está, en File > Project Structure, configurar un JRE y JDK válidos y en Use classpath of module, asignar clientmanager
3. Si no lo está, en la vista de Maven > Reimport All Maven Projects

# Inicio de la app 
En el proyecto clientmanager > src > main > java > es.esorcle.swing > StarApp
   
   
# Manual de usuario   
   ## Pantalla de conexión
   * Al iniciar la aplicación aparecerá la pantalla inicial que nos conectará a la base de datos. 
   * Debemos rellenar todos los campos antes de poder continuar. 
   * Clic en conectar
   * Si algún cambio no es correcto o trascurridos unos segundos desde la acción del botón conectar, no se puede establecer conexión, nos aparecerá un mensaje de error.

   ## Pantalla de gestión de clientes
   * Una vez conectado a la base de datos, se nos presenta esta pantalla donde podemos realizar todas las acciones establecidas para la gestión del cliente. Encontramos:
   * El primer paso para empezar, es introducir un criterio de búsqueda para localizar al cliente
   
### Filtrar los clientes
   * Una vez introducido el criterio de búsqueda en el campo correspondiente
   * Clic en el botón buscar
   * Aparecerán los clientes que coincida cualquiera de sus datos con el criterio introducido, en la tabla de abajo

### Editar clientes
* Seleccionamos el cliente que queremos editar
* Clic en editar
* Se abrirá otra pantalla donde podremos modificar todos los datos del cliente, a excepción del número de cliente que es único e identifica a cada cliente insertado
* Una vez realizado los cambios, clic en el botón guardar
* Aparecerá un mensaje de aviso, tanto si se ha realizado la operación con éxito como si no.
* El botón atrás vuelve a la pantalla de gestión de clientes

### Eliminar clientes
* Seleccionamos el cliente que queremos eliminar
* Clic en eliminar 
* Nos aparece un mensaje de aviso, para asegurar la eliminación del cliente, con las opciones si y no.

### Pagos del cliente
* Selecinamos el cliente que queremos ver los pagos realizdos y su total 
* Clic en pagos 
* Aparecerá una ventana con el número de cliente selecionado y los pagos asociados a él, así como el total de los mmismos
* El botón atrás vuelve a la pantalla de gestión de clientes

### Visualizar cliente
* Seleccionamos el cliente que queremos visualizar
* Clic en visualizar
* Aparecerá una pantalla con los datos asociados a ese cliente sin posibilidad de modificación
* El botón atrás vuelve a la pantalla de gestión de clientes

### Añadir vendedor
* Seleccionamos el cliente que queremos añadir vendedor
* Clic en añadir vendedor
* Se nos abre una ventana de entrada de datos para escribir el número de empleado
* Aparecerá un mensaje de aviso, tanto si se ha realizado la operación con éxito como si no.

### Nuevo cliente
* Clic en nuevo cliente
* Se nos abrirá una nueva ventana con los posibles datos a rellenar de un cliente
* Si le damos al botón guardar, nos indicará los campos obligatorios o con un valor no válido mediante un borde rojo
* Una vez introducidos todos los datos, al menos los obligatorios, clic en guardar
* El botón atrás vuelve a la pantalla de gestión de clientes
  
### Salir de la aplicación
* Clic en salir para cerrar la conexión a la base de datos y cerrar la aplicación









