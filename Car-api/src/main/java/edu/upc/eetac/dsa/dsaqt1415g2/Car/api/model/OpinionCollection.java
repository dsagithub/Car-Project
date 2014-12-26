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

public class OpinionCollection {
	
	@InjectLinks
	({
	   @InjectLink(value ="/opinion?username={username}",style = Style.ABSOLUTE, rel = "one user's opinion", title = "Get-opinion by username", type = MediaType.CAR_API_OPINION_COLLECTION,bindings = { @Binding(name = "username", value = "${instance.username}") })
	})
	private List<Link> links;
	private List<Opinion> opiniones;
	private String username;

	public OpinionCollection() {
		super();
		opiniones = new ArrayList<>();
	}

	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}

	public void addOpiniones(Opinion opinion) {
		opiniones.add(opinion);
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
}
