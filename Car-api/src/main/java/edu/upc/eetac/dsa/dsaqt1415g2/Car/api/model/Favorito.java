package edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model;

public class Favorito {
	private int idfavorito;
	private int idposicion;
	private String username;
	private String descripcion;
	private long fecha;

	public int getIdfavorito() {
		return idfavorito;
	}

	public void setIdfavorito(int idfavorito) {
		this.idfavorito = idfavorito;
	}

	public int getIdposicion() {
		return idposicion;
	}

	public void setIdposicion(int idposicion) {
		this.idposicion = idposicion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getFecha() {
		return fecha;
	}

	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

}

