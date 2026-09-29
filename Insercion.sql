-- Insertamos datos para area, luego estados posibles, y dos sistemas

INSERT INTO area (nombre)
VALUES ('Dirección de Informática');

INSERT INTO estado_tramite (descripcion)
VALUES ('Iniciado');

INSERT INTO estado_tramite (descripcion)
VALUES ('En proceso');

INSERT INTO estado_tramite (descripcion)
VALUES ('Finalizado');

INSERT INTO sistema (nombre)
VALUES ('GDEBA');

INSERT INTO sistema (nombre)
VALUES ('Sistema de gestión interno');

-- Insertamos un agente

INSERT INTO agente (
    apellido_nombre,
    ubicacion,
    usuario_gdeba,
    id_area
)
VALUES (
    'Alvaro Jonas',
    'Dirección de Informática',
    'ajonas',
    1
);

-- Insertamos un tramite de baja

INSERT INTO tramite_baja (
    id_agente,
    id_estado,
    fecha_baja,
    motivo,
    acto_administrativo,
    observaciones,
    ticket_asociado
)
VALUES (
    1,
    1,
    '2026-09-28',
    'Cese de funciones',
    'RESO-2026-001',
    'Trámite iniciado',
    'TICKET-001'
);

-- Insertamos un acceso asociado al agente con id=1

INSERT INTO acceso_permiso (
    id_agente,
    id_sistema,
    id_tramite,
    descripcion,
    estado
)
VALUES (
    1,
    1,
    1,
    'Acceso al sistema GDEBA',
    'Pendiente de baja'
);