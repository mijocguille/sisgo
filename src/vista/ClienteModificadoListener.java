package vista;

public interface ClienteModificadoListener {
	void onClienteModificado(int idCliente, String razonSocial, String cuit, String direccion, String telefono);
}
