# Build Application running tests
./mvnw clean package

# Build Application without tests
./mvnw clean package -DskipTests

# Run tests
./mvnw test

# Run application(default profile) 
Ejecutar el siguiente comando en la ruta app-bootloader: ../mvnw spring-boot:run

# Swagger url
URL = http://localhost:8080/swagger-ui/index.html

# H2 Console
URL = http://localhost:8080/h2-console
USER = sa
PASSWORD = password

# Especificaciones
El modulo api-specs forma parte de api-infrastructure pero ,por preferencia, dado que autogenera las interfaces y los objetos de entrada/salida de los endpoints se ha mantenido separado.
El beneficio podria ser tambien reutilizar el modulo para generar clientes http para consumir dichos servicios basados en las especificaciones OpenApi.

He preferido subdividir la infraestructura en api y db, para entre otras cosas evitar la posibilidad del uso de objetos de entidad dentro de los adaptadores de api asi como usos directos de jpa repositories en la api.

Se han realizado tests de integracion con bbdd en db-infrastructure, tests unitarios en application y apì-infrastructure y los tests e2e se han generado en el modulo de app-bootloader ya que integra toda la infraestructura del proyecto.
Los tests e2e reutilizan el script data.sql ya que por defecto spring busca cualquier script asi nombrado en el classpath al usar bbdd en memoria.
Las clases XXXTestApplication generadas en las carpetas de test de los modulos de infrastructure han sido necesarias para indicarle a spring que clase base utilizar a la hora de inicializar el contexto , escanear beans etc. Fue una decision por simplicidad ya que de lo contrario tendria que haber especificado los component scans y demas configuraciones manualmente solo para testear.


