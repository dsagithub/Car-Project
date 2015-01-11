package edu.upc.eetac.dsa.dsaqt1415g2.Car.api;
import java.sql.*;
import java.util.Vector;

import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.Page;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.Posicion;
import edu.upc.eetac.dsa.dsaqt1415g2.Car.api.model.PosicionCollection;



@Path("/posicion")
public class PosicionResource 
{
	@Context
	private SecurityContext security;

	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private static int index=0;
	private static int j; //valor de offset
	private static int i; //guardo el valor de la pagina
	private static Vector<Integer> sp=new Vector<Integer>(1000,500);
	
	//
	//CREAR UNA POSICION NUEVA
	private String INSERT_POSICION_QUERY="insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values(?,?,?,?)";
	@POST
	@Consumes(MediaType.CAR_API_POSICION)
	@Produces(MediaType.CAR_API_POSICION)
	public Posicion createPosicion(Posicion posicion)
	{
		validatePosicion(posicion);
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
		private String GET_POSICIONES_USERNAME_QUERY = "select * from posiciones where username=? order by idposicion desc limit 5 offset ?";
		@GET
		@Produces(MediaType.CAR_API_POSICION_COLLECTION)
		public PosicionCollection getPosiciones(@QueryParam("username") String username,@QueryParam("pag") int pag)
		{
			int offset;
			int bpag;
			int b;
			
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
				if(pag !=0)
				{
					
					sp.add(index, pag);
					System.out.println("Guardamos la pagina"+pag);
					index=index+1;
					System.out.println("El valor del index dentro if"+index);
				}
				//i=pag;
				if(index==1)
				b=index-1;
				else
				b=index-2;
				
				System.out.println("El valor b"+b);
				bpag=sp.get(b);
				System.out.println("El valor de la pagina anterior"+bpag);
				System.out.println("La pagina anterior es"+bpag);
				if(pag-bpag>=0)
				{
					
					offset=CalculateNext(pag);
					System.out.println("El resultado del CalculateNext es"+offset);
					
				}
				else
				{ 
				  offset=CalculatePrevious(pag);
				  System.out.println("El resultado del CalculatePrevious es"+offset);
					
				}
				
				
				
	            stmt.setString(1, username);
	            stmt.setInt(2, offset);
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
				if(pag==1)
				{
					posiciones.setPrepag(pag);
					posiciones.setNextpag(pag+1);
					
				}
				
				else
				{
					posiciones.setPrepag(pag-1);
					posiciones.setNextpag(pag+1);
					
				}
				
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
			//validateUser(idposicion);
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
			//validateUser(idposicion);
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
		
		//PARA VALIDAR POSICIONES
		private void validatePosicion(Posicion posicion) {
			if (posicion.getDescripcion() == null)
			throw new BadRequestException("La descripcion no puede ser nula");
			}
		//PARA VALIDAR UN USUARIO
		private void validateUser(String idposicion)
		{
			Posicion posicion=getPosicionFromDatabase(idposicion);
			String username=posicion.getUsername();
			if (!security.getUserPrincipal().getName().equals(username)) 
			{
				throw new ForbiddenException("You are not allowed to modify this Description.");
			}
		}
		
		private Posicion getPosicionFromDatabase(String idposicion)
		{
			Posicion posicion=new Posicion();
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
		
		private int CalculateNext(int pag)
		{
			if(pag==1)
			{
				
				
				//stmt.setString(1, username);
				//stmt.setInt(2, i);
				/*i=i+5;
				Page page=new Page();
				page.setPage(i);
				j=page.getPage();*/
				System.out.println("En el caso de pag=1 es"+j);
				
				return 0;
				
			}
			else
			{
				
				Page page=new Page();
				System.out.println("El numero de offset es"+j);
				//stmt.setString(1, username);
				//stmt.setInt(2, j);
				j=j+5;
				//page.setPage(j);
				return j;
			}
		}
			
			private int CalculatePrevious(int pag)
			{
				if(pag==1)
				{
					int i=0;
					
					//stmt.setString(1, username);
					//stmt.setInt(2, i);
					i=i+5;
					Page page=new Page();
					page.setPage(i);
					j=page.getPage();
					System.out.println("El numero de j es"+j);
					
					return 0;
					
				}
				else
				{
					
					//Page page=new Page();
					
					//stmt.setString(1, username);
					//stmt.setInt(2, j);
					j=j-5;
					System.out.println("El numero de offset es"+j);
					//page.setPage(j);
					return j;
				}
			
			
			
		}
		
		

}
