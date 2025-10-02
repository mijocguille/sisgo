package recursos;

import java.util.Date;

public class Equipo {

	
	private int idEquipo;
	private String descripcionEquipo; 
	private int cantidadEquipos;
	private Date fechaAlta; 
	private Date fechaBaja; 
	private int idUsuario;
	
	public Equipo () {
		this.idEquipo = 0;
		this.descripcionEquipo = "";
		this.cantidadEquipos = 0;
		this.fechaAlta = new Date();
		this.fechaBaja = null;
		this.idUsuario = 0;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getDescripcionEquipo() {
		return descripcionEquipo;
	}

	public void setDescripcionEquipo(String descripcionEquipo) {
		this.descripcionEquipo = descripcionEquipo;
	}

	public int getCantidadEquipos() {
		return cantidadEquipos;
	}

	public void setCantidadEquipos(int cantidadEquipos) {
		this.cantidadEquipos = cantidadEquipos;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
