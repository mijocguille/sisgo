package entidades;

import java.lang.reflect.Array;

public class ControladorUsuario {
	
	
	
	public ControladorUsuario() {
		
	}
	
	public Array listarUsuarios() {
		
		Array colUsuarios = null;
		
		return colUsuarios;
	}
	
	
	public Usuario darAltaUsuario() {
		return new Usuario();
	}
	
    private boolean validarInformacionUsuario() {
    	return true;
    }
    
    private Usuario cargarInformacionUsuario() {
    	return new Usuario();
    }
    
    public boolean darBajaUsuario(Usuario objUsuario) {
    	return true;
    }
    
    public boolean modificarUsuario(int idUsuario) {
    	return true;
    }
    
    public boolean login() {
    	return true;
    }
    
    public boolean logout() {
    	return true;
    }
}
