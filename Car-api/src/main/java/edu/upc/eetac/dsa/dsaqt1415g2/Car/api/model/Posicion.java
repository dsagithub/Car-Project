package edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model;
import java.util.List;
import javax.ws.rs.core.Link;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.MediaType;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.PosicionResource;



public class Posicion 
{
	
	@InjectLinks
	({
		@InjectLink(value ="/posicion/{idposicion}", style = Style.ABSOLUTE, rel = "self edit", title = "One Posicion", type = MediaType.CAR_API_POSICION, method = "Get posicion", bindings = @Binding(name = "idposicion", value = "${instance.idposicion}")) 
	})
	
	
	

	private List<Link> links;

	private int idposicion;
	private String username;
	private double coordenadaX;
	private double coordenadaY;
	private String descripcion;
	private long fecha;
	
	
	
	public int getIdposicion() 
	{
		return idposicion;
	}
	public void setIdposicion(int idposicion) 
	{
		this.idposicion = idposicion;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public double getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(double coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public double getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(double coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public String getDescripcion() 
	{
		return descripcion;
	}
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
	public long getFecha() {
		return fecha;
	}
	public void setFecha(long fecha) 
	{
		this.fecha = fecha;
	}
	
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}


}
