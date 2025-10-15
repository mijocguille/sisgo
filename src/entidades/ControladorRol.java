package entidades;

import java.lang.reflect.Array;


public class ControladorRol {
	
	public ControladorRol() {
		
	}
	
	public Array listarRoles() {
		
		Array colRoles = null;
		
		return colRoles;
	}
	
	
	public boolean darAltaRol() {
		return true;
	}
	
    private boolean validarInformacion() {
    	return true;
    }
    
    private Rol cargarInformacion() {
    	return new Rol();
    }
    
    public boolean darBajaRol(int idRol) {
    	return true;
    }
    
    public boolean modificaRol(int idRol) {
    	return true;
    }
}
