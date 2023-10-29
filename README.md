# Prueba Técnica - Microservicio Spring


<img src="https://theme.zdassets.com/theme_assets/16434824/904a13f8f5f162c8124eb4c2e88b81717ffddde9.png" alt="Taxis libres" height="85" style="margin-left:auto; margin-right: auto; display: block">


## Introducción

Este documento describe los requerimientos y pasos para completar la prueba técnica de Taxis Libres, que consiste en crear un microservicio utilizando el framework Spring.

## Ejecución

`Repositorio de GitHub:` https://github.com/nicrodriguezval/technical-test

Para ejecutar el microservicio tan solo es necesario los archivos `docker-compose.yml` y `Dockerfile` que se encuentran en la raíz del repositorio. Para ejecutar el microservicio se debe ejecutar el siguiente comando en la terminal:

```bash
  docker-compose up
```

Este se encargará de ejecutar el contenedor de la base de datos *PostgreSQL* que se expondrá sobre el puerto `5432` y construirá la imagen del contenedor del microservicio a partir de las instrucciones definidas dentro del Dockerfile, y su respectivo contenedor se ejecutará sobre el puerto `8080`.

En la configuración dentro del archivo `docker-compose.yaml` ya se encuentran definidas las variables de entorno tanto para la ejecución de la base de datos como para la conexión a la misma por parte del microservicio, por lo que no es necesario modificarlas.

En dado caso que tan solo se requiera ejecutar el microservicio a través del Dockerfile y conectarlo a una base de datos externa, se deben definir las siguientes ***variables de entorno*** para la correcta ejecución del contenedor:

- `DB_URL`: URL de conexión a la base de datos.
- `DB_USER`: Usuario de la base de datos.
- `DB_PASSWORD`: Contraseña del usuario de la base de datos.

## Requerimientos

### 1. API RESTful

La API sigue el diseño arquitectónico REST, y para cada uno de los **recursos** se definieron las siguientes **acciones**:

#### Users 🙎🏻‍♂️

- `POST /api/users`: Crear un usuario
- `GET /api/users`: Obtener todos los usuarios y sus facturas
- `GET /api/users/:id`: Obtener un usuario y sus facturas
- `PUT /api/users/:id`: Actualizar un usuario
- `DELETE /api/users/:id`: Borrar una factura de un usuario

#### Bills 🧾

- `POST /api/bills`: Crear una factura para un usuario
- `GET /api/bills`: Obtener todas las facturas
- `GET /api/bills/:id`: Obtener una factura
- `PUT /api/bills/:id`: Actualizar una factura
- `DELETE /api/bills/:id`: Borrar una factura

### 2. Estructura del Proyecto

La estructura del proyecto debe seguir la convención típica de paquetes en Spring:

- `models`: Contiene las entidades `USER` y `BILL`.
- `repositorys`: Debe contener los métodos de acceso a la capa de persistencia sin utilizar SQL directamente.
- `controllers`: Contiene los endpoints de la API los cuales siguen el diseño arquitectónico REST.
- `services`: Capa en donde se desarrolla toda la lógica de negocio la cual se conecta el controlador.

### 3. Modelos de Datos

En la capa de modelo (`models`), deben existir las siguientes entidades:

#### Entidad `USER`

- Atributos:
    - `id` (clave primaria, tipo `long`)
    - `name` (tipo `String`)
    - `age` (tipo `int`)
    - `email` (tipo `String`)

#### Entidad `BILL`

- Atributos:
    - `id` (clave primaria, tipo `long`)
    - `totalAmount` (tipo `double`)
    - `desc` (tipo `String`)
    - `id_user` (clave foránea que se relaciona con un usuario en una relación de uno a muchos)


