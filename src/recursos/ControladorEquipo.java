package recursos;

import java.lang.reflect.Array;

public class ControladorEquipo {
	public ControladorEquipo() {
		
	}
	
	public Equipo seleccionarEquipo() {
		
		
	    return new Equipo ();
	}
	
	public Array listarEquipos() {
		
		Array colEquipos = null;
		
		return colEquipos;
	}
	
	
	public Equipo darAltaEquipo() {
		return new Equipo();
	}
	
    private boolean validarInformacionEquipo() {
    	return true;
    }
    
    private Equipo cargarInformacionEquipo() {
    	return new Equipo();
    }
    
    public boolean darBajaEquipo(int idEquipo) {
    	return true;
    }
    
    public boolean modificarEquipo(int idEquipo) {
    	return true;
    }


}
