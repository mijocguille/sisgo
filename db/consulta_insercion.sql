/*Inserción del rol super admadministrador */
INSERT INTO rol (descripcionRol)
VALUES ('Super Admin');

/*Inserción del usuario administrador */
INSERT INTO usuario (nombreUsuario, claveUsuario, descripcionUsuario, fechaAlta , idRol)
VALUES ('gmijoc', '93132466106db336ab3749d86b9f8bf2f4eec0ce2f402163b66685b217232ab5', 'Guillermo Mijoc', NOW(), 1); /*Rol 1 super admin, clave cifrada con sha-256 */

/*Inserción de un nuevo cliente*/
INSERT INTO cliente (razonSocial, cuit, direccion, telefono, fechaAlta, idUsuario)
VALUES ('GUILLERMO MIJOC', 20259015767, 'Estacion Talleres 365', '29746668899', NOW(), 1); /** Usuario 1 super admin */
	
SELECT * FROM rol WHERE idRol = 1;

SELECT * FROM usuario WHERE idUsuario = 1;

SELECT * FROM cliente WHERE idCliente = 1;