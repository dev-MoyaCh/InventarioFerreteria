# Inventario Ferretería — ORM con Hibernate + MySQL

Proyecto de ejemplo que usa **Hibernate** (ORM) para guardar productos
en una base de datos **MySQL**, sin escribir SQL a mano.

## Requisitos

- JDK 17 o superior
- Maven
- MySQL Server corriendo en `localhost:3306`
- Base de datos `ferreteria` ya creada (`CREATE DATABASE ferreteria;`)

## Configuración (IMPORTANTE: antes de ejecutar)

La contraseña de MySQL **no está en el código** por seguridad — se lee de una
variable de entorno. Antes de correr el programa, configúrala:

### En cmd (Windows):
```
set MYSQL_PASSWORD=tu_contraseña_de_mysql
```

### En PowerShell (Windows):
```
$env:MYSQL_PASSWORD="tu_contraseña_de_mysql"
```

Esto solo dura mientras esa ventana de terminal esté abierta. Si cierras la
terminal, hay que volver a escribirlo.

## Cómo ejecutar

```
mvn clean compile
mvn exec:java
```

La primera vez, Hibernate creará automáticamente la tabla `productos`
dentro de la base de datos `ferreteria`.

## Estructura

```
src/main/java/com/ferreteria/
├── modelo/Producto.java   ← Entity (clase Java <-> tabla SQL)
├── dao/ProductoDAO.java   ← guardar / buscar / listar / eliminar
└── app/AppORM.java        ← programa de prueba con menú en consola
```
