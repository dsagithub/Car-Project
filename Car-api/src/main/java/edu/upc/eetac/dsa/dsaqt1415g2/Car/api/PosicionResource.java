package edu.upc.eetac.dsa.dsaqt1415g2.Car.api;
import java.sql.*;

import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.Posicion;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.PosicionCollection;



@Path("/posicion")
public class PosicionResource 
{
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	//
	//CREAR UNA POSICION NUEVA
	private String INSERT_POSICION_QUERY="insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values(?,?,?,?)";
	@POST
	@Consumes(MediaType.CAR_API_POSICION)
	@Produces(MediaType.CAR_API_POSICION)
	public Posicion createPosicion(Posicion posicion)
	{
		Connection conn=null;
		try
		{
			conn=ds.getConnection();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		PreparedStatement stmt=null;
		try
		{
			stmt=conn.prepareStatement(INSERT_POSICION_QUERY,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, posicion.getUsername());
			stmt.setDouble(2, posicion.getCoordenadaX());
			stmt.setDouble(3, posicion.getCoordenadaY());
			stmt.setString(4, posicion.getDescripcion());
			stmt.executeUpdate();
			ResultSet rs =stmt.getGeneratedKeys();
			
			if(rs.next())
			{
				int posicionid=rs.getInt(1);
				posicion=getPosicion(Integer.toString(posicionid));
			}
			else
			{
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (stmt != null)
				stmt.close();
				conn.close();
			} 
			catch (SQLException e) 
			{
				
			}
		}
		return posicion;
	}
	//
	//GET POSICION POR ID 
	private String GET_POSICION_BY_ID="select * from posiciones where idposicion=?";
	@GET
	@Path("/{idposicion}")
	@Produces(MediaType.CAR_API_POSICION)
	public Posicion getPosicion(@PathParam("idposicion") String idposicion)
	{
		Posicion posicion =new Posicion();
		Connection conn = null;
		try 
		{
		conn = ds.getConnection();
		} 
		catch (SQLException e) 
		{
		e.printStackTrace();
		}
		 
		PreparedStatement stmt = null; 
		try
		{
			stmt=conn.prepareStatement(GET_POSICION_BY_ID);
			stmt.setInt(1, Integer.valueOf(idposicion));
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				posicion.setIdposicion(rs.getInt("idposicion"));
				posicion.setUsername(rs.getString("username"));
				posicion.setCoordenadaX(rs.getDouble("coordenadaX"));
				posicion.setCoordenadaY(rs.getDouble("coordenadaY"));
				posicion.setDescripcion(rs.getString("descripcion"));
				posicion.setFecha(rs.getTimestamp("fecha").getTime());
			}
		}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		finally
		{
			try
			{
				if(stmt !=null)
			    stmt.close();
				
			}catch(SQLException e)
			{
				
			}
		}
		
		return posicion;
	}
	// GET TODAS LAS POSICIONES DE UN USUARIO POR SU USERNAME
		private String GET_POSICIONES_USERNAME_QUERY = "select * from posiciones where username=? order by idposicion desc";
		@GET
		@Produces(MediaType.CAR_API_POSICION_COLLECTION)
		public PosicionCollection getPosiciones(@QueryParam("username") String username) 
		{
			PosicionCollection posiciones = new PosicionCollection();

			Connection conn = null;
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(GET_POSICIONES_USERNAME_QUERY);
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				//String username1=null;
				while (rs.next()) 
				{
					Posicion posicion = new Posicion();
					posicion.setIdposicion(rs.getInt("idposicion"));
					//username1=rs.getString("username");
					posicion.setUsername(rs.getString("username"));
					posicion.setCoordenadaX(rs.getDouble("coordenadaX"));
					posicion.setCoordenadaY(rs.getDouble("coordenadaY"));
					posicion.setDescripcion(rs.getString("descripcion"));
					posicion.setFecha(rs.getTimestamp("fecha").getTime());
					posiciones.addPosicion(posicion);
				}
				posiciones.setUsername(username);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						stmt.close();
					conn.close();
				} catch (SQLException e) {
				}
			}

			return posiciones;
		}
		private String GET_LAST_POSICION_QUERY="select * from posiciones where username=? order by idposicion desc limit 1";
		
		@GET
		@Path("/last")
		@Produces(MediaType.CAR_API_POSICION)
		public Posicion getLastPosicion(@QueryParam("username") String username)
		{
			Posicion posicion = new Posicion ();
			Connection conn=null;
			try
			{
				conn=ds.getConnection();
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			PreparedStatement stmt=null;
			try
			{
				stmt=conn.prepareStatement(GET_LAST_POSICION_QUERY);
				stmt.setString(1, username);
				ResultSet rs=stmt.executeQuery();
				if(rs.next())
				{
					posicion.setIdposicion(rs.getInt("idposicion"));
					posicion.setCoordenadaX(rs.getDouble("coordenadaX"));
					posicion.setCoordenadaY(rs.getDouble("coordenadaY"));
					posicion.setDescripcion(rs.getString("descripcion"));
					posicion.setFecha(rs.getTimestamp("fecha").getTime());
					
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
	    finally 
	    {
		try 
		{
			   if (stmt != null)
			   stmt.close();

		} 
		catch (SQLException e) 
		{

		}
	    }
	         return posicion;

	 }
		
		
		private String DELETE_POSICION_QUERY="delete from posiciones where idposicion=?";
		
		
		@DELETE
		@Path("/{idposicion}")
		public void deletePosicion(@PathParam("idposicion") String idposicion)
		{
			Connection conn=null;
			try
			{
				conn=ds.getConnection();
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			PreparedStatement stmt=null;
			try
			{
				stmt=conn.prepareStatement(DELETE_POSICION_QUERY);
				stmt.setInt(1,Integer.valueOf(idposicion));
				int rows = stmt.executeUpdate();
				if(rows==0)
				{
					//deleteting inexisting posicion
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(stmt!=null)
					stmt.close();
					conn.close();
				}
				catch(SQLException e)
				{
					
				}
			}		
		}
		
		
		
		
		private String UPDATE_DESCRIPCION_QUERY="update posiciones set descripcion=ifnull(?,descripcion) where idposicion=?";
		@PUT
		@Path("/{idposicion}")
		@Consumes(MediaType.CAR_API_POSICION)
		@Produces(MediaType.CAR_API_POSICION)
		public Posicion updatePosicion(@PathParam("idposicion") String idposicion, Posicion posicion)
		{
			Connection conn =null;
			try
			{
				conn=ds.getConnection();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			PreparedStatement stmt=null;
			try
			{
				stmt=conn.prepareStatement(UPDATE_DESCRIPCION_QUERY);
				
				stmt.setString(1, posicion.getDescripcion());
				stmt.setInt(2, Integer.valueOf(idposicion));
				int rows=stmt.executeUpdate();
				if(rows==1)
			    posicion = getPosicion(idposicion);
				else
				{
					//Updateing inexisting posicion description
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(stmt !=null)
					stmt.close();
					conn.close();
				}
				catch(SQLException e)
				{
					
				}
			}
			
			return posicion;
			}
}
