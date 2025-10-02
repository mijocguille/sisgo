package operaciones;

public class InsumoProyecto {
	
	private int idInsumo;
	private int numeroProyecto;
	private int cantidad;
	
	public InsumoProyecto () {
		this.idInsumo = 0;
		this.numeroProyecto = 0;
		this.cantidad = 0;
		
	}

	public int getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(int idInsumo) {
		this.idInsumo = idInsumo;
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
