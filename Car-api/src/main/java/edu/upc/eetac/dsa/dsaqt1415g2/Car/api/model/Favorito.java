package edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model;

import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.MediaType;

public class Favorito {
	
	@InjectLinks({
		@InjectLink(value ="/favorito/{idfavorito}", style = Style.ABSOLUTE, rel = "self edit", title = "One Opinion", type = MediaType.CAR_API_FAVORITO, method = "Get favorito", bindings = @Binding(name = "idfavorito", value = "${instance.idfavorito}")) 
	})
	private List<Link> links;
	private int idfavorito;
	private int idposicion;
	private String username;
	private String descripcion;
	private long fecha;
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> link) {
		this.links = link;
	}

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

