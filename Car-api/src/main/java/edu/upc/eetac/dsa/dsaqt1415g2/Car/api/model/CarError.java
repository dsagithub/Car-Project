package edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model;

public class CarError 
{
	private int status;
	private String message;
	
	public CarError() 
	{
	super();
	}
	
	
	public CarError(int status, String message) 
	{
	super();
	this.status = status;
	this.message = message;
	}
	
	
	public int getStatus() 
	{
		return status;
	}


	public void setStatus(int status) 
	{
		this.status = status;
	}


	public String getMessage() 
	{
		return message;
	}


	public void setMessage(String message) 
	{
		this.message = message;
	}

}
