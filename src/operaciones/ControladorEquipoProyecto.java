package operaciones;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import recursos.Equipo;
import recursos.TablaEquipo;
import sistema.BaseDatos;

public class ControladorEquipoProyecto {
	
	private TablaEquipoProyecto tblEquipoProyecto;
	private TablaEquipo tblEquipo;
	
	public ControladorEquipoProyecto(BaseDatos db) {
		tblEquipoProyecto = new TablaEquipoProyecto(db);
		tblEquipo = new TablaEquipo(db);
	}

	public TableModel listarEquipos(int numeroProyecto) {
		
		DefaultTableModel model = new DefaultTableModel();
		String[] encabezados = {"#", "Equipo", "Cantidad"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<EquipoProyecto> colEquipos = tblEquipoProyecto.obtenerEquiposProyecto(numeroProyecto); 

		for(EquipoProyecto ep : colEquipos) {	
			Equipo e = tblEquipo.obtenerEquipo(ep.getIdEquipo());
			String[] row = {String.valueOf(ep.getIdEquipo()),e.getDescripcionEquipo(), String.valueOf(ep.getCantidad())};
			model.addRow(row);
		}	
		
		return model;
		
	}
	
	public boolean agregarEquipoProyecto(EquipoProyecto ep) {
		boolean resultado = false;
		if(tblEquipoProyecto.verificarCantidadDisponible(ep)) {
			resultado = tblEquipoProyecto.agregarEquipoProyecto(ep);
		} 
		
		return resultado;
	}
	
	public boolean quitarEquipo(EquipoProyecto ep) {
		return tblEquipoProyecto.quitarEquipoProyecto(ep);
	}

	public TablaEquipoProyecto getTblEquipoProyecto() {
		return tblEquipoProyecto;
	}

	public TablaEquipo getTblEquipo() {
		return tblEquipo;
	}
}
