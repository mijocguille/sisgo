/*consulta de baja de un cliente */
UPDATE cliente 
SET fechaBaja = NOW() /*El now lo reeemplazaria con una variable a lo igual que el id de cliente 1 */
WHERE idCliente = 1;

SELECT * FROM cliente WHERE idCliente = 1;