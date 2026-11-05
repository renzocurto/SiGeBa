
USE sigeba;

CREATE TABLE area (
    id_area INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL
);

CREATE TABLE estado_tramite (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE sistema (
    id_sistema INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL
);

CREATE TABLE agente (
    id_agente INT AUTO_INCREMENT PRIMARY KEY,
    apellido_nombre VARCHAR(150) NOT NULL,
    ubicacion VARCHAR(150),
    usuario_gdeba VARCHAR(100),
    id_area INT,
    FOREIGN KEY (id_area) REFERENCES area(id_area)
);
-- Creamos las tablas
CREATE TABLE tramite_baja (
    id_tramite INT AUTO_INCREMENT PRIMARY KEY,
    id_agente INT NOT NULL UNIQUE,
    id_estado INT NOT NULL,
    fecha_baja DATE NOT NULL,
    motivo VARCHAR(200) NOT NULL,
    acto_administrativo VARCHAR(150),
    observaciones TEXT,
    ticket_asociado VARCHAR(100),
    FOREIGN KEY (id_agente) REFERENCES agente(id_agente),
    FOREIGN KEY (id_estado) REFERENCES estado_tramite(id_estado)
);

CREATE TABLE acceso_permiso (
    id_acceso INT AUTO_INCREMENT PRIMARY KEY,
    id_agente INT NOT NULL,
    id_sistema INT NOT NULL,
    id_tramite INT NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_agente) REFERENCES agente(id_agente),
    FOREIGN KEY (id_sistema) REFERENCES sistema(id_sistema),
    FOREIGN KEY (id_tramite) REFERENCES tramite_baja(id_tramite)
);