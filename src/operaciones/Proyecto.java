package operaciones;

import java.util.Date;

public class Proyecto {
	
	private int numeroProyecto;
	private Date fechaCreacion;
	private String nombreProyecto;
	private Date fechaEstimadaInicio;
	private Date fechaFin;
	private int idUsuario;
	
	public Proyecto () {
		
		this.numeroProyecto = 0;
		this.fechaCreacion = new Date();
		this.nombreProyecto = "";
		this.fechaEstimadaInicio = new Date();
		this.fechaFin = new Date();
		this.idUsuario = 0;
		
		
		
	}

	public int getNumeroProyecto() {
		return numeroProyecto;
	}

	public void setNumeroProyecto(int numeroProyecto) {
		this.numeroProyecto = numeroProyecto;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public Date getFechaEstimadaInicio() {
		return fechaEstimadaInicio;
	}

	public void setFechaEstimadaInicio(Date fechaEstimadaInicio) {
		this.fechaEstimadaInicio = fechaEstimadaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


}
