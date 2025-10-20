package operaciones;

import java.util.Date;

public class Presupuesto {
	
	private int numeroProyecto;
	private int numeroPresupuesto;
	private Date fechaPresupuesto;
	private int diasValidez;
	private double importePresupuestado;
	private int idUsuario;
	
	public Presupuesto () {
		
		this.numeroProyecto = 0;
		this.numeroPresupuesto = 0;
		this.fechaPresupuesto = new Date();
		this.diasValidez = 0;
		this.importePresupuestado = 0;
		this.idUsuario = 0;
	}

	public int getNumeroProyecto() {
		return numeroProyecto;
	}

	public void setNumeroProyecto(int numeroProyecto) {
		this.numeroProyecto = numeroProyecto;
	}

	public int getNumeroPresupuesto() {
		return numeroPresupuesto;
	}

	public void setNumeroPresupuesto(int numeroPresupuesto) {
		this.numeroPresupuesto = numeroPresupuesto;
	}

	public Date getFechaPresupuesto() {
		return fechaPresupuesto;
	}

	public void setFechaPresupuesto(Date fechaPresupuesto) {
		this.fechaPresupuesto = fechaPresupuesto;
	}

	public int getDiasValidez() {
		return diasValidez;
	}

	public void setDiasValidez(int diasValidez) {
		this.diasValidez = diasValidez;
	}

	public double getImportePresupuestado() {
		return importePresupuestado;
	}

	public void setImportePresupuestado(double importePresupuestado) {
		this.importePresupuestado = importePresupuestado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	

}
