package operaciones;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import recursos.Insumo;
import recursos.TablaInsumo;
import sistema.BaseDatos;

public class ControladorInsumoProyecto {

	
	private TablaInsumoProyecto tblInsumoProyecto;
	private TablaInsumo tblInsumo;
	
	public ControladorInsumoProyecto(BaseDatos db) {
		tblInsumoProyecto = new TablaInsumoProyecto(db);
		tblInsumo = new TablaInsumo(db);
	}

	
	public TablaInsumoProyecto getTblInsumoProyecto() {
		return tblInsumoProyecto;
	}

	public TablaInsumo getTblInsumo() {
		return tblInsumo;
	}


	public TableModel listarInsumos(int numeroProyecto) {
		
		DefaultTableModel model = new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int i, int i1) {
		        return false;
		    }

		   };
		String[] encabezados = {"#", "Insumos", "Cantidad"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<InsumoProyecto> colInsumos = tblInsumoProyecto.obtenerInsumosProyecto(numeroProyecto); 

		for(InsumoProyecto ip : colInsumos) {	
			Insumo i = tblInsumo.obtenerInsumo(ip.getIdInsumo());
			String[] row = {String.valueOf(ip.getIdInsumo()),i.getDescripcionInsumo(), String.valueOf(ip.getCantidad())};
			model.addRow(row);
		}	
		
		return model;
	}
	
	public boolean agregarInsumoProyecto(InsumoProyecto ip) {
		boolean resultado = false;
		if(tblInsumo.obtenerStock(ip.getIdInsumo()) - ip.getCantidad() > 0 ) {
			resultado = tblInsumoProyecto.agregarInsumoProyecto(ip);
		} 
		
		return resultado;
	}
	
	public boolean quitarInsumo(InsumoProyecto ip) {
		return tblInsumoProyecto.quitarInsumoProyecto(ip);
	}
}
