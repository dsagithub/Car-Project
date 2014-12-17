package edu.upc.eetac.dsa.dsaqt1415g2.Car.api;
import javax.ws.rs.GET;
import javax.ws.rs.Path; 
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.CarRootAPI;


@Path("/")
public class CarRootAPIResource 
{
	@GET
	public CarRootAPI getRootAPI()
	{
		CarRootAPI api = new CarRootAPI();
		return api;
	}

}
