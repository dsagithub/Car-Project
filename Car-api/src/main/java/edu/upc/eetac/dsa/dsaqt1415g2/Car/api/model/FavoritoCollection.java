package edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.MediaType;

public class FavoritoCollection {
	@InjectLinks
	({
	   @InjectLink(value ="/favorito?username={username}",style = Style.ABSOLUTE, rel = "one user's favorite", title = "Get-favorito by username", type = MediaType.CAR_API_FAVORITO_COLLECTION,bindings = { @Binding(name = "username", value = "${instance.username}") })
	})
	private List<Link> links;
	private List<Favorito> favoritos;
	private String username;

	public FavoritoCollection() {
		super();
		favoritos = new ArrayList<>();
	}

	public List<Favorito> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<Favorito> favoritos) {
		this.favoritos = favoritos;
	}

	public void addFavoritos(Favorito favorito) {
		favoritos.add(favorito);
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
