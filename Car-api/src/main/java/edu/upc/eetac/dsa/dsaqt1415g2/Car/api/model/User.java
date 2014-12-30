package edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model;

import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.MediaType;

public class User 
{
	@InjectLinks
	({
		@InjectLink(value ="/user/{username}", style = Style.ABSOLUTE, rel = "self edit", title = "One user", type = MediaType.CAR_API_USER, method = "Get user", bindings = @Binding(name = "username", value = "${instance.username}")) 
	})
	private List<Link> links;
	private String username;
	private String userpass;
	private String email;
	private boolean loginSuccessful;
	
	
    public String getUsername() 
    {
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getUserpass() 
	{
		return userpass;
	}
	public void setUserpass(String userpass) 
	{
		this.userpass = userpass;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}

	public boolean isLoginSuccessful() {
		return loginSuccessful;
	}
	public void setLoginSuccessful(boolean loginSuccessful) {
		this.loginSuccessful = loginSuccessful;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	

}

