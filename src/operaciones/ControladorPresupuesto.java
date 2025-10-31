package operaciones;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import sistema.BaseDatos;

public class ControladorPresupuesto {

	private TablaPresupuesto tblPresupuesto;
	
	public TablaPresupuesto getTblPresupuesto() {
		return tblPresupuesto;
	}


	public ControladorPresupuesto(BaseDatos db) {
		tblPresupuesto = new TablaPresupuesto(db);		
	}
	
	
	public TableModel listarPresupuestos(int numeroProyecto) {
		
		DefaultTableModel model = new DefaultTableModel();
		String[] encabezados = {"#", "Fecha Presupuesto", "D\u00EDas Validez", "Importe"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<Presupuesto> colPresupuestos = tblPresupuesto.obtenerPresupuestos(numeroProyecto); 

		for(Presupuesto p : colPresupuestos) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String fechaPresupuesto = formatter.format(p.getFechaPresupuesto());
	
			
			String[] row = {String.valueOf(p.getNumeroPresupuesto()),fechaPresupuesto,String.valueOf(p.getDiasValidez()),"$" + String.valueOf(p.getImportePresupuestado())};
			model.addRow(row);
		}	
		
		return model;
	}
	
	
	public boolean crearPresupuesto(Presupuesto objPresupuesto) {
		boolean resultado = false;
		
		if(validarInformacionPresupuesto(objPresupuesto)) {
			objPresupuesto.setNumeroPresupuesto(tblPresupuesto.obtenerProximoPresupuesto(objPresupuesto.getNumeroProyecto()));
			resultado = tblPresupuesto.altaPresupuesto(objPresupuesto);
		}
		return resultado;
	}
	
    private boolean validarInformacionPresupuesto(Presupuesto objPresupuesto) {
    	return true;
    }
    
    
    public boolean eliminarPresupuesto(int numeroProyecto, int idPresupuesto) {
    	return tblPresupuesto.eliminarPrespuesto(tblPresupuesto.obtenerPresupuesto(numeroProyecto, idPresupuesto));
    }
    
    public boolean modificarPresupuesto(Presupuesto objPresupuesto) {
    	boolean resultado = false;
		
    	if(validarInformacionPresupuesto(objPresupuesto)) {
			resultado = tblPresupuesto.modificarPresupuesto(objPresupuesto);
		}
    	return resultado;
    }
}
