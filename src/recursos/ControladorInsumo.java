package recursos;

import java.lang.reflect.Array;



public class ControladorInsumo {
	
	public ControladorInsumo() {
		
	}
	
	public Insumo seleccionarInsumo() {
		
		
	    return new Insumo ();
	}
	
	public Array listarInsumos() {
		
		Array colInsumos = null;
		
		return colInsumos;
	}
	
	
	public Insumo darAltaInsumo() {
		return new Insumo();
	}
	
    private boolean validarInformacionInsumo() {
    	return true;
    }
    
    private Insumo cargarInformacionInsumo() {
    	return new Insumo();
    }
    
    public boolean darBajaInsumo(int idInsumo) {
    	return true;
    }
    
    public boolean modificarInsumo(int idInsumo) {
    	return true;
    }

}
