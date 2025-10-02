/*Inserto la accion y su permiso al super admin*/
INSERT INTO accion (nombreAccion)
VALUES ('Listar Usuarios');

INSERT INTO permiso (idRol, idAccion)
VALUES (1,1);

SELECT * FROM permiso;

/*Suponiendo que deseo eliminar el permiso del super admin*/

DELETE FROM permiso WHERE idRol = 1 AND idAccion = 1; /*tanto el rol como la accion serían parametros*/


SELECT * FROM permiso;