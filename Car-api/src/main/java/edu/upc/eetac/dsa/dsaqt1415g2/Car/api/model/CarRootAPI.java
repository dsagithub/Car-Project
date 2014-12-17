package edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model;
import java.util.List;
import javax.ws.rs.core.Link;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.CarRootAPIResource;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.MediaType;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.PosicionResource;


public class CarRootAPI 
{
	@InjectLinks
	({
     @InjectLink(resource = CarRootAPIResource.class, style = Style.ABSOLUTE, rel = "self home", title = "Car Root API", method = "getRootAPI"),
     @InjectLink(value = "/posicion?username=",style = Style.ABSOLUTE, rel = "one user's posicions", title = "Get-posicions by username", type = MediaType.CAR_API_POSICION_COLLECTION),
     @InjectLink(resource = PosicionResource.class, style = Style.ABSOLUTE, rel = "Post posicion", title = "Create-posicion", type = MediaType.CAR_API_POSICION),
    })
	
	private List<Link> links;
	 
    public List<Link> getLinks() 
    {
     return links;
    }
 
    public void setLinks(List<Link> links) 
    {
     this.links = links;
    } 

}

