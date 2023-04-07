#Examen Backend-1 Especialización

## Para correr docker
```bash
$ cd docker
$ docker-compose up
```
---
## Microservicios
- configServer (puerto:8888)
- eurekaServer (puerto:8761)
- gateway (puerto:8081)
- api-movie (puerto dinámico)
- api-serie (puerto dinámico)
- api-catalog (puerto dinámico)

---
## Comunicación asincrónica: RabbitMQ
#### Productores de mensajes
- api-movie newMovie: cuando se genera una nueva película, se envía un mensaje de aviso a catalog, con toda las propiedades de la nueva película y este la persiste en un su base de datos
- api-serie newSerie: lo mismo que películas, pero ahora con una nueva serie y sus propiedades
#### Consumidor
- api-catalog

---
## Circuit break
api-catalog posee dos métodos para exponer películas y series por genéro
- método online: el catálogo se genera a través de las peticiones http a api-movie y a api-serie
- método offline: si eventualmente algún servicio está caído, el catálogo entra en el fallback del método online y genera un catálogo con películas y series almacenadas en su propia base de datos

--- 
## Test
Existe un test del ciclo completo en que se crea una serie y una película con un género inexistente en la base de datos, desde el controller de catalog y se prueban los métodos online y offline del nuevo género almacenado
A su vez, en api-catalo, se pueden visualizar los losg de los mensajes asincrónicos, tanto de nueva película y nueva serie, bajo el nuevo género
