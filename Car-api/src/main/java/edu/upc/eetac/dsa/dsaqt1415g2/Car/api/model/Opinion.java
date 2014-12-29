package edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.MediaType;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.OpinionResource;

public class Opinion 
{
	@InjectLinks
	({
		@InjectLink(value ="/opinion/{idopinion}", style = Style.ABSOLUTE, rel = "self-edit", title = "One Opinion", type = MediaType.CAR_API_OPINION, method = "Get opinion", bindings = @Binding(name = "idopinion", value = "${instance.idopinion}")) 
	})
	private List<Link> links;
	private int idopinion;
	private String username;
	private int idposicion;
	private String content;
	private String precio;
	private long fecha;

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> link) {
		this.links = link;
	}

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
