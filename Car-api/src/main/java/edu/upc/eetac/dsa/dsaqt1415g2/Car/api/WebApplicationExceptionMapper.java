package edu.upc.eetac.dsa.dsaqt1415g2.Car.api;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.MediaType;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.CarError;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException>
{
	@Override
	public Response toResponse(WebApplicationException exception) 
	{
		CarError error = new CarError(exception.getResponse().getStatus(), exception.getMessage());
		return Response.status(error.getStatus()).entity(error).type(MediaType.CAR_API_ERROR).build();
	}

}
