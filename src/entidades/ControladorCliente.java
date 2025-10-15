package entidades;

import java.lang.reflect.Array;

public class ControladorCliente {
	
	public ControladorCliente() {
		
	}
	
	public Array listarClientes() {
		
		Array colClientes = null;
		
		return colClientes;
	}
	
	
	public Cliente crearCliente() {
		return new Cliente();
	}
	
    private boolean validarInformacion() {
    	return true;
    }
    
    private Cliente cargarInformacion() {
    	return new Cliente();
    }
    
    public boolean darBajaCliente(Cliente objCliente) {
    	return true;
    }
    
    public boolean modificarCliente(int idCliente) {
    	return true;
    }
}



