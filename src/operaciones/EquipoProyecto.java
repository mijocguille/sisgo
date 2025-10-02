package operaciones;

public class EquipoProyecto {
	
	private int idEquipo;
	private int numeroProyecto;
	private int cantidad;
	
	public EquipoProyecto () {
		this.idEquipo = 0;
		this.numeroProyecto = 0;
		this.cantidad = 0;
		
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public int getNumeroProyecto() {
		return numeroProyecto;
	}

	public void setNumeroProyecto(int numeroProyecto) {
		this.numeroProyecto = numeroProyecto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	

}
