USE sigeba;

-- Consulta de la tabla agentes

SELECT *
FROM agente;

-- Consulta de tramites

SELECT *
FROM tramite_baja;

-- Consulta completa de información

SELECT
    a.apellido_nombre,
    t.fecha_baja,
    t.motivo,
    e.descripcion AS estado
FROM tramite_baja t
INNER JOIN agente a
    ON t.id_agente = a.id_agente
INNER JOIN estado_tramite e
    ON t.id_estado = e.id_estado;
