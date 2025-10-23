package operaciones;

import java.lang.reflect.Array;

import sistema.BaseDatos;


public class ControladorPedido {
	
	private TablaPedido tblPedido;
	
	
	public ControladorPedido(BaseDatos db) {
		tblPedido  = new TablaPedido(db);
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
	
    private boolean validarInformacion(Pedido objP) {
    	return true;
    }
    
    
    public boolean anularPedido(int numeroPedido) {
    	return true;
    }
    
    public boolean modificarPedido(Pedido objP) {
    	boolean resultado = false;
    	if(this.validarInformacion(objP)) {
    		resultado = tblPedido.modificarPedido(objP);
    	}
    	return resultado;
    }
}
