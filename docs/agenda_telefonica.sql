CREATE DATABASE IF NOT EXISTS agenda_telefonica;
USE agenda_telefonica;

CREATE TABLE IF NOT EXISTS contacto(id INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    movil INT NOT NULL,
    trabajo INT DEFAULT 0,
    correo VARCHAR(100),
    PRIMARY KEY(id)
);

-- Datos de pruebas
/*
INSERT INTO contacto(nombre, apellidos, movil, trabajo, correo) 
   VALUES ('Daniel', 'Gomez Gomez', 964538216, 964538216, 'info@example.cl');
INSERT INTO contacto(nombre, apellidos, movil, correo)    
    VALUES ('Daniel', 'Gomez Gomez', 964538216, 'prueba@example.cl');
*/

SELECT c.id, CONCAT_WS(' ', c.nombre, c.apellidos) AS nombre_contacto, c.movil, c.trabajo, c.correo
FROM contacto c;
