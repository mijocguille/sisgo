package operaciones;

import javax.swing.table.TableModel;

import sistema.BaseDatos;

public class ControladorEquipoProyecto {
	
	private TablaEquipoProyecto tblEquipoProyecto;
	
	public ControladorEquipoProyecto(BaseDatos db) {
		tblEquipoProyecto = new TablaEquipoProyecto(db);
	}

	public TableModel listarEquipos(int numeroProyecto) {
		
		return null;
	}
	
	public boolean agregarEquipoProyecto(int numeroProyecto) {
		return true;
	}
	
	public boolean quitarEquipo(int numeroProyecto) {
		return true;
	}
}
