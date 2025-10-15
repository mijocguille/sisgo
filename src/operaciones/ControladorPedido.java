package operaciones;

import java.lang.reflect.Array;


public class ControladorPedido {
	
	public ControladorPedido() {
		
	}
	
	public Pedido seleccionarPedido() {
		
		
	    return new Pedido ();
	}
	
	public Array listarPedidos() {
		
		Array colPedidos = null;
		
		return colPedidos;
	}
	
	
	public Pedido crearPedido() {
		return new Pedido();
	}
	
    private boolean validarInformacion() {
    	return true;
    }
    
    private Pedido cargarInformacion() {
    	return new Pedido();
    }
    
    public boolean anularPedido(int numeroPedido) {
    	return true;
    }
    
    public boolean modificarPedido(int numeroPedido) {
    	return true;
    }
}
