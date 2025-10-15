package operaciones;

import java.lang.reflect.Array;


public class ControladorProyecto {

	public ControladorProyecto() {
		
	}
	
	public Array listarProyectos() {
		
		Array colProyectos = null;
		
		return colProyectos;
	}
		
	public Proyecto crearProyecto() {
		return new Proyecto();
	}
	
	private boolean validarInformacion() {
    	return true;
    }   
    
    private Proyecto cargarInformacion() {
    	return new Proyecto();
    }
    
    public boolean bajaProyecto(Proyecto objProyecto) {
    	return true;
    }
    
    public boolean modificaProyecto(int numeroProyecto) {
    	return true;
    }
}
