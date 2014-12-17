package edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model;

public class User 
{
	private String username;
	private String password;
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
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
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

	

}

