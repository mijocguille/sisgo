package vista;

import java.util.Date;

public interface ProyectoNuevoListener {
   void onProyectoCreado(String nombreProyecto, Date fechaEstimadaInicio, Date fechaFin, int numeroPedido);
}
