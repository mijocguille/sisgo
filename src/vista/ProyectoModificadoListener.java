package vista;

import java.util.Date;

public interface ProyectoModificadoListener {
	void onProyectoModificado(int numeroProyecto, String nombreProyecto, Date fechaEstimadaInicio, Date fechaFin, int numeroPedido);
}
