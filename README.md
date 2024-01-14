# Album-test
`Dev. Jean Quero`

Este microservicio esta desarrollado con SpringBoot y tiene 3 endpoints.

Lista de endpoints:

## POST

`Se tiene una Api con dos endpoind (https://jsonplaceholder.typicode.com/albums y https://jsonplaceholder.typicode.com/photos), los cuales son usados para que enriquezca una base dato.` [/api/v1/album] <br/>

## GET

`Se tiene una Api con dos endpoind (https://jsonplaceholder.typicode.com/albums y https://jsonplaceholder.typicode.com/photos) los cuales son devueltos en la petición` [/api/v1/album/api] <br/>
`Se obtienes los álbumes de la base de datos en memoria H2 ` [/api/v1/album] <br/>

La aplicacion esta dockerizada, puede ejecutar la aplicacion siguiendo los siguientes pasos:

1. [x] 1: Posiciones en la carpeta raiz del proyecto
2. [x] 2: Ejecute el archivo run.sh

**Coverage 89%**

Primero Ejecute el comando
1. `mvn test`
2. `mvn clean install`

![img.png](img.png)

