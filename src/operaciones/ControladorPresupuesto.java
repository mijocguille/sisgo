package operaciones;

import java.lang.reflect.Array;

public class ControladorPresupuesto {

	public ControladorPresupuesto() {
		
	}
	
	
	public Array listarPresupuestos(int numeroProyecto) {
		
		Array colPresupuestos = null;
		
		return colPresupuestos;
	}
	
	
	public Presupuesto crearPresupuesto(int numeroProyecto) {
		return new Presupuesto();
	}
	
    private boolean validarInformacionPresupuesto() {
    	return true;
    }
    
    private Presupuesto cargarInformacionPresupuesto() {
    	return new Presupuesto();
    }
    
    public boolean eliminarPresupuesto(int numeroProyecto, int idPresupuesto) {
    	return true;
    }
    
    public boolean modificarPresupuesto(int numeroProyecto, int idPresupuesto) {
    	return true;
    }
}
