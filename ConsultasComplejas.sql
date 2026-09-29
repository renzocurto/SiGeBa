


-- Listado de tramites con agente y estado
SELECT
    t.id_tramite,
    a.apellido_nombre,
    t.fecha_baja,
    t.motivo,
    e.descripcion AS estado
FROM tramite_baja t
INNER JOIN agente a
    ON t.id_agente = a.id_agente
INNER JOIN estado_tramite e
    ON t.id_estado = e.id_estado;
    
 -- Tramites en proceso   
    SELECT
    t.id_tramite,
    a.apellido_nombre,
    t.fecha_baja,
    e.descripcion AS estado
FROM tramite_baja t
INNER JOIN agente a
    ON t.id_agente = a.id_agente
INNER JOIN estado_tramite e
    ON t.id_estado = e.id_estado
WHERE e.descripcion = 'En proceso';

-- Busqueda por nombre o apellido
SELECT
    t.id_tramite,
    a.apellido_nombre,
    t.fecha_baja,
    t.motivo,
    e.descripcion AS estado
FROM tramite_baja t
INNER JOIN agente a
    ON t.id_agente = a.id_agente
INNER JOIN estado_tramite e
    ON t.id_estado = e.id_estado
WHERE a.apellido_nombre LIKE '%Pérez%';

-- Accesos asociados al tramite id=1
SELECT
    a.apellido_nombre,
    s.nombre AS sistema,
    ap.descripcion AS acceso,
    ap.estado
FROM acceso_permiso ap
INNER JOIN agente a
    ON ap.id_agente = a.id_agente
INNER JOIN sistema s
    ON ap.id_sistema = s.id_sistema
WHERE ap.id_tramite = 1;

-- Tramites ordenados por fecha de baja
SELECT
    t.id_tramite,
    a.apellido_nombre,
    t.fecha_baja,
    e.descripcion AS estado
FROM tramite_baja t
INNER JOIN agente a
    ON t.id_agente = a.id_agente
INNER JOIN estado_tramite e
    ON t.id_estado = e.id_estado
ORDER BY t.fecha_baja ASC;



