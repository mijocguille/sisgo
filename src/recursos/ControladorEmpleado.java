package recursos;

import java.lang.reflect.Array;

public class ControladorEmpleado {
	
	public ControladorEmpleado() {
		
	}
	
	public Empleado seleccionarEmpleado() {
		
		
	    return new Empleado ();
	}
	
	public Array listarEmpleados() {
		
		Array colEmpleados = null;
		
		return colEmpleados;
	}
	
	public Empleado darAltaEmpleado() {
		return new Empleado();
	}
	
    private boolean validarInformacionEmpleado() {
    	return true;
    }
    
    private Empleado cargarInformacionEmpleado() {
    	return new Empleado();
    }
    
    public boolean bajaEmpleado(int idEmpleado) {
    	return true;
    }
    
    public boolean modificaEmpleado(int idEmpleado) {
    	return true;
    }


}
