USE sigeba;

INSERT INTO area (nombre)
VALUES
    ('Dirección de Informática'),
    ('Dirección de Recursos Humanos'),
    ('Dirección Administrativa');

INSERT INTO estado_tramite (descripcion)
VALUES
    ('Iniciado'),
    ('En proceso'),
    ('En espera'),
    ('Finalizado');

INSERT INTO sistema (nombre)
VALUES
    ('GDEBA'),
    ('Sistema interno de gestión'),
    ('Correo institucional'),
    ('Mesa de ayuda');
    
    
    
    INSERT INTO agente (
    apellido_nombre,
    ubicacion,
    usuario_gdeba,
    id_area
)
VALUES
    ('Juan Pérez', 'Dirección de Informática', 'jperez', 1),
    ('María Gómez', 'Dirección de Recursos Humanos', 'mgomez', 2),
    ('Carlos López', 'Dirección Administrativa', 'clopez', 3),
    ('Laura Fernández', 'Dirección de Informática', 'lfernandez', 1);
    
    
    
    INSERT INTO tramite_baja (
    id_agente,
    id_estado,
    fecha_baja,
    motivo,
    acto_administrativo,
    observaciones,
    ticket_asociado
)
VALUES
    (1, 2, '2026-09-05', 'Cese de funciones', 'RESO-2026-101', 'Pendiente revisión de accesos', 'TICKET-001'),
    (2, 1, '2026-09-10', 'Renuncia', 'RESO-2026-102', 'Trámite recientemente iniciado', 'TICKET-002'),
    (3, 3, '2026-09-15', 'Finalización de contrato', 'RESO-2026-103', 'En espera de confirmación del área', 'TICKET-003'),
    (4, 4, '2026-09-20', 'Cese de funciones', 'RESO-2026-104', 'Baja completada', 'TICKET-004');
    
    
    
    INSERT INTO acceso_permiso (
    id_agente,
    id_sistema,
    id_tramite,
    descripcion,
    estado
)
VALUES
    (1, 1, 1, 'Acceso a GDEBA', 'Pendiente'),
    (1, 3, 1, 'Cuenta de correo institucional', 'Pendiente'),
    (2, 1, 2, 'Acceso a GDEBA', 'Pendiente'),
    (2, 2, 2, 'Acceso al sistema interno', 'Pendiente'),
    (3, 2, 3, 'Acceso al sistema interno', 'En revisión'),
    (3, 4, 3, 'Acceso a mesa de ayuda', 'Pendiente'),
    (4, 1, 4, 'Acceso a GDEBA', 'Baja realizada'),
    (4, 3, 4, 'Cuenta de correo institucional', 'Baja realizada');
    
    
