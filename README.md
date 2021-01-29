# Roulette Api

Api rest que permite realizar apuestas sobre ruletas virtuales.

# Servicios

Crear ruletas

```sh
http://localhost:8080/api/v1/roulette/create
```

listar ruletas

```sh
http://localhost:8080/api/v1/roulette/list
```
Dar apertura a una ruleta para apostar

```sh
http://localhost:8080/api/v1/roulette/open
body peticion = {"id": ""} #id de la ruleta
```

Apostar a un numero en una ruleta

```sh
http://localhost:8080/api/v1/roulette/bet/number
body  {
    "rouletteId":"",
    "number":,    
    "money":
} # rouletteId de la ruleta #number numero entre 0-36 #money dinero apostar
header User-Id #id de un usuario
```

Apostar a un color en una ruleta
```sh
http://localhost:8080/api/v1/roulette/bet/color
body {
    "rouletteId":"",
    "color":"",   
    "money":
} # rouletteId de la ruleta #color se permite red o black #money dinero apostar
header User-Id #id de un usuario
```

Cerrar las apuestas de una ruleta y definir ganadores
```sh
http://localhost:8080/api/v1/roulette/close
body {"id":"6012267ccf929972cfe6bc12"} #id identificador de la ruleta
```


### Tecnologias que se utilizaron

* [Spring Boot](https://spring.io/projects/spring-boot) - 2.4.2
* [Spring Initializer](https://start.spring.io/)
* [Maven](https://maven.apache.org/) - 2.4.2
* [JDK](https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html) - 8
* [mongodb](https://www.mongodb.com/es) - 4.4.3


### Entorno
Configurar las variables de entorno para conexion a la bd 

```sh
USER_ROULETTE = Usuario de la bases de datos
BD_NAME_ROULETTE = nombre de la base de datos
PASS_ROULETE = contraseña de la base de datos
HOST_ROULETTE = host en donde corre el servicio de mongo
PORT_ROULETTE = puerto desde donde escucha el servicio
```
