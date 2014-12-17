package edu.upc.eetac.dsa.dsaqt1415g2.Car.api;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;


public class CarApplication extends ResourceConfig
{
	public CarApplication() 
	
	   {
		super();
		register(DeclarativeLinkingFeature.class);
		
		}
	

}
