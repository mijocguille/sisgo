package operaciones;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import recursos.Empleado;
import recursos.TablaEmpleado;
import sistema.BaseDatos;

public class ControladorEmpleadoProyecto {
	
	private TablaEmpleadoProyecto tblEmpleadoProyecto;
	private TablaEmpleado tblEmpleado;
	
	public TablaEmpleadoProyecto getTblEmpleadoProyecto() {
		return tblEmpleadoProyecto;
	}

	public TablaEmpleado getTblEmpleado() {
		return tblEmpleado;
	}

	public ControladorEmpleadoProyecto(BaseDatos db) {
		tblEmpleadoProyecto = new TablaEmpleadoProyecto(db);
		tblEmpleado = new TablaEmpleado(db);
	}

	public TableModel listarPersonalAsignado(int numeroProyecto) {
		
		DefaultTableModel model = new DefaultTableModel();
		String[] encabezados = {"#", "Legajo", "Nombre y Apellido"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<EmpleadoProyecto> colEmpleados = tblEmpleadoProyecto.obtenerEmpleadosProyecto(numeroProyecto); 

		for(EmpleadoProyecto ep : colEmpleados) {	
			Empleado e = tblEmpleado.obtenerEmpleado(ep.getIdEmpleado());
			String[] row = {String.valueOf(ep.getIdEmpleado()),String.valueOf(e.getLegajo()),e.getNombreCompleto()};
			model.addRow(row);
		}	
		
		return model;
		
	}
	
	public boolean agregarEmpleado(EmpleadoProyecto ep) {
		
		boolean resultado = false;
		if(tblEmpleadoProyecto.verificarDisponible(ep)) {
			resultado = tblEmpleadoProyecto.agregarEmpleadoProyecto(ep);
		} 
		
		return resultado;
	}
	
	public boolean quitarEmpleado(EmpleadoProyecto ep) {
		return tblEmpleadoProyecto.quitarEmpleadoProyecto(ep);
	}
}
