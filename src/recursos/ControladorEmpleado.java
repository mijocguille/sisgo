package recursos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import sistema.BaseDatos;

public class ControladorEmpleado {
	
	private TablaEmpleado tblEmpleado;
	
	public TablaEmpleado getTblEmpleado() {
		return tblEmpleado;
	}


	public ControladorEmpleado(BaseDatos db) {
		tblEmpleado = new TablaEmpleado(db);
	}
	
	
	public TableModel listarEmpleados() {
		
		DefaultTableModel model = new DefaultTableModel();
		String[] encabezados = {"#", "Legajo", "Nombre y Apellido", "Fecha Alta", "Fecha Baja"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<Empleado> colEmpleados = tblEmpleado.obtenerEmpleados(); 

		for(Empleado e : colEmpleados) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
			String fechaAlta = formatter.format(e.getFechaAlta());
			String fechaBaja = formatter.format(e.getFechaBaja());
			
			String[] row = {String.valueOf(e.getIdEmpleado()),String.valueOf(e.getLegajo()),e.getNombreCompleto(),fechaAlta,fechaBaja};
			model.addRow(row);
		}	
		
		return model;
	}
	
	public int darAltaEmpleado(Empleado objE) {
		int idEmpleado = 0;
		if(this.validarInformacionEmpleado(objE)) {
			idEmpleado = tblEmpleado.altaEmpleado(objE);
		}		
		return idEmpleado; 
	}
	
    private boolean validarInformacionEmpleado(Empleado objE) {
    	return true;
    }
   
    
    public boolean bajaEmpleado(int idEmpleado) {
    	return tblEmpleado.bajaEmpleado(idEmpleado);
    }
    
    public boolean modificaEmpleado(Empleado objE) {
    	boolean resultado = false;
    	if(this.validarInformacionEmpleado(objE)) {
			resultado = tblEmpleado.modificarEmpleado(objE);
		}		
    	return resultado;
    }

}
