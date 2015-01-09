package edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Link;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.MediaType;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.PosicionResource;


public class PosicionCollection 
{
	@InjectLinks
	({
	   @InjectLink(value ="/posicion?username={username}",style = Style.ABSOLUTE, rel = "one-opinions", title = "Get-posicions by username", type = MediaType.CAR_API_POSICION_COLLECTION,bindings = { @Binding(name = "username", value = "${instance.username}") })
	})
	
	private List<Link> links;
	

	private String username;
	
	
	private List<Posicion> posiciones;
	
	
	public PosicionCollection()
	{
		super();
		posiciones=new ArrayList<>();
	}


	public List<Posicion> getPosiciones() {
		return posiciones;
	}


	public void setPosiciones(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}
	
	public void addPosicion(Posicion posicion)
	{
		posiciones.add(posicion);
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
