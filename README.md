# SiGeBa – Sistema de Gestión de Bajas

## Descripción del proyecto

SiGeBa es un prototipo de aplicación de escritorio desarrollado en Java, destinado a centralizar el registro y la consulta de trámites de baja de agentes de un organismo.

Esta implementación sigue un enfoque incremental, incorporando las funcionalidades esenciales del sistema.

## Funcionalidades implementadas

El prototipo contempla dos casos de uso definidos durante el TP2:

**CU01 – Registrar trámite de baja**

Permite registrar los datos de un agente y su trámite de baja mediante un formulario gráfico. El sistema valida la información ingresada y almacena ambos registros en MySQL.

**CU02 – Consultar trámites de baja**

Permite visualizar los trámites registrados mediante una tabla, mostrando:

- Identificador del trámite.
- Apellido y nombre del agente.
- Fecha de baja.
- Motivo.
- Estado actual.

## Tecnologías utilizadas

- **Lenguaje:** Java 25.
- **Interfaz gráfica:** Java Swing.
- **Base de datos:** MySQL Server 8.0.
- **Conexión a base de datos:** JDBC con MySQL Connector/J 26.10.0
- **Entorno de desarrollo:** Visual Studio Code.
- **Control de versiones:** GitHub.

## Estructura del proyecto

```text
SiGeBa/
├── src/
│   ├── App.java
│   ├── Agente.java
│   ├── ConexionBD.java
│   ├── EstadoTramite.java
│   ├── FormularioRegistroBaja.java
│   ├── PantallaPrincipal.java
│   ├── RegistroBajaService.java
│   ├── TramiteBaja.java
│   └── TramiteBajaDAO.java
├── database/
│   ├── 01_crear_base.sql
│   ├── 02_crear_tablas.sql
│   └── 03_datos_prueba.sql
├── lib/
│   └── mysql-connector-j-26.10.0.jar
├── .gitignore
└── README.md
```
## Requisitos de ejecución

Para ejecutar SiGeBa se necesita:

1. Tener instalado **Java JDK 25**.
2. Contar con **MySQL Server 8.0** en funcionamiento.
3. Disponer del controlador MySQL Connector/J, incluido en la carpeta **lib**.
4. Crear la base de datos `sigeba` mediante los scripts SQL proporcionados.

### Preparación de la base de datos

Desde MySQL Workbench, ejecutar los scripts ubicados en `database/`, respetando el siguiente orden:

1. `01_crear_base.sql`: creación de la base de datos.
2. `02_crear_tablas.sql`: creación de tablas y relaciones.
3. `03_datos_prueba.sql`: incorporación de datos ficticios para probar el prototipo.

La contraseña se obtiene mediante la variable de entorno `SIGEBA_DB_PASSWORD`, evitando almacenar credenciales directamente en el código.

## Compilación y ejecución

Abrir una terminal en la carpeta principal del proyecto.

### Windows (PowerShell)

Configurar la contraseña local de MySQL:

```powershell
$env:SIGEBA_DB_PASSWORD = "CONTRASEÑA_LOCAL"
```

Compilar las clases:

```powershell
javac -cp "lib/*" -d bin src/*.java
```

Ejecutar SiGeBa:

```powershell
java -cp "bin;lib/*" App
```

### macOS

Configurar la contraseña local de MySQL:

```bash
export SIGEBA_DB_PASSWORD='CONTRASEÑA_LOCAL'
```

Compilar las clases:

```bash
javac -cp "lib/*" -d bin src/*.java
```

Ejecutar SiGeBa:

```bash
java -cp "bin:lib/*" App
```

## Repositorio

El código fuente y los archivos asociados se encuentran disponibles en:

https://github.com/renzocurto/SiGeBa




