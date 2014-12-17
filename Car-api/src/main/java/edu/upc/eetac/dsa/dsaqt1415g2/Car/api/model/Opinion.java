package edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model;

public class Opinion 
{

	private int idopinion;
	private String username;
	private int idposicion;
	private String content;
	private String precio;
	private long fecha;

	public int getIdopinion() {
		return idopinion;
	}

	public void setIdopinion(int idopinion) {
		this.idopinion = idopinion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdposicion() {
		return idposicion;
	}

	public void setIdposicion(int idposicion) {
		this.idposicion = idposicion;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public long getFecha() {
		return fecha;
	}

	public void setFecha(long fecha) {
		this.fecha = fecha;
	}
}
